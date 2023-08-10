package com.example.event;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {
    private int selectedPosition = -1;


    public CustomSpinnerAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.spinner_item);
        textView.setText(getItem(position));

        if (position == selectedPosition) {
            convertView.setBackgroundColor(Color.GRAY);
            textView.setTextColor(Color.WHITE);


        } else {
            convertView.setBackgroundColor(Color.WHITE);
            textView.setTextColor(Color.BLUE);

        }


        return convertView;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }


    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }


}
