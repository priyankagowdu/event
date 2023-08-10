package com.example.event;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spinner, spinner1, spinner2;
    EditText nameEditText1, nameEditText2, emailEditText, websitetext, biotext, githubtext, twittertext, phonetext;
    RadioGroup radioGroup;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference eventRef = database.getReference("Event1");
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference eventRef1 = database1.getReference("Passes");
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        DatabaseReference eventRef2 = database2.getReference("Event's Known by");
        List<String> itemList = new ArrayList<>();
        itemList.add("-------Select One");
        itemList.add("Ui-Ux design");
        itemList.add("full Stack development");
        itemList.add("videography");
        itemList.add("Photography");
        itemList.add("others");
        List<String> itemList1 = new ArrayList<>();
        itemList1.add("-------Select One");
        itemList1.add("Main Event Pass");
        itemList1.add("Full day Pass");
        itemList1.add("Vip Pass");
        itemList1.add("others");
        List<String> itemList2 = new ArrayList<>();
        itemList2.add("-------Select One");
        itemList2.add("Family");
        itemList2.add("Friends");
        itemList2.add("Teachers");
        itemList2.add("others");
        CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(this, R.layout.spinner_item, itemList);
        Spinner spinner = findViewById(R.id.promospinner);
        spinner.setAdapter(spinnerAdapter);


        CustomSpinnerAdapter1 spinnerAdapter1 = new CustomSpinnerAdapter1(this, R.layout.spinner_item, itemList1);
        Spinner spinner1 = findViewById(R.id.promospinner1);
        spinner1.setAdapter(spinnerAdapter1);

        CustomSpinnerAdapter2 spinnerAdapter2 = new CustomSpinnerAdapter2(this, R.layout.spinner_item, itemList2);
        Spinner spinner2 = findViewById(R.id.promospinner2);
        spinner2.setAdapter(spinnerAdapter2);
        Button saveButton = findViewById(R.id.Register);
        nameEditText1 = findViewById(R.id.fristname);
        nameEditText2 = findViewById(R.id.lastname);
        emailEditText = findViewById(R.id.email);
        websitetext = findViewById(R.id.website);
        biotext = findViewById(R.id.bio);
        githubtext = findViewById(R.id.username);
        twittertext = findViewById(R.id.username1);
        radioGroup = findViewById(R.id.radiogroup);
        phonetext = findViewById(R.id.phone);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedValue = itemList.get(position);

                DatabaseReference candidatesRef = eventRef.child("Interested candidates");
                candidatesRef.push().setValue(selectedValue);
                Toast.makeText(MainActivity.this, "Selected: " + selectedValue, Toast.LENGTH_SHORT).show();
                spinnerAdapter.setSelectedPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedValue1 = itemList1.get(position);
                DatabaseReference candidatesRef1 = eventRef1.child("Passes");
                candidatesRef1.push().setValue(selectedValue1);
                Toast.makeText(MainActivity.this, "Selected: " + selectedValue1, Toast.LENGTH_SHORT).show();
                spinnerAdapter1.setSelectedPosition1(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedValue2 = itemList2.get(position);
                DatabaseReference candidatesRef2 = eventRef2.child("Event's Known by");
                candidatesRef2.push().setValue(selectedValue2);
                Toast.makeText(MainActivity.this, "Selected: " + selectedValue2, Toast.LENGTH_SHORT).show();
                spinnerAdapter2.setSelectedPosition1(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

                    } else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }
                }





                String firstname = nameEditText1.getText().toString();
                String lastname = nameEditText2.getText().toString();
                String emailaddress = emailEditText.getText().toString();
                String website = websitetext.getText().toString();
                String biodata = biotext.getText().toString();
                String github = githubtext.getText().toString();
                String twitter = twittertext.getText().toString();
                String Phone = phonetext.getText().toString();
                String selectedValue = itemList.get(spinner.getSelectedItemPosition());
                String selectedValue1 = itemList1.get(spinner1.getSelectedItemPosition());
                String selectedValue2= itemList2.get(spinner2.getSelectedItemPosition());

                sendSMS(firstname, lastname, selectedValue,selectedValue1,selectedValue2);
                boolean installed = appInstalledOrNot("com.whatsapp");
                if (installed) {
                    sendWhatsAppMessage(firstname,lastname, selectedValue, selectedValue1,selectedValue2,Phone);
                } else {
                    Toast.makeText(MainActivity.this, "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show();
                }
               /* boolean installed = appInstalledOrNot("com.whatsapp");
                if (installed){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+Phone + "&text="+fristname+"!Thankyou for registering The evnt have A Good Day"));
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Whats app not installed on your device", Toast.LENGTH_SHORT).show();
                }*/

                String Radiogroup = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();

                Event event = new Event(firstname, lastname, emailaddress, website, biodata, github, twitter, Radiogroup, Phone);
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference().child("users");
                reference.push().setValue(event);
                Toast.makeText(MainActivity.this, "values are Added successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendWhatsAppMessage(String firstname, String lastname, String selectedValue, String selectedValue1, String selectedValue2, String phone) {
        String message = "Hello " + firstname  + lastname + "\n "+"Your Details "+"\n "+ selectedValue +"\n "+selectedValue1+ "\n "+selectedValue2+"\n "+ "! Thank you for The Event Have! A Good Day!!: " ;

        try {
            Uri uri = Uri.parse("http://api.whatsapp.com/send?phone=" + "+91" + phone + "&text=" + message);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "WhatsApp is not installed on your device.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    private void sendSMS(String firstname, String lastname, String selectedValue, String selectedValue1, String selectedValue2) {
         String phone = phonetext.getText().toString().trim();

        String message = "Hello " + firstname  + lastname + "\n "+"Your Details "+"\n "+ selectedValue +"\n "+selectedValue1+ "\n "+selectedValue2+"\n "+ "! Thank you for The Event Have! A Good Day!!: " ;

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, message, null, null);
            Toast.makeText(this, "Message sent successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to send message", Toast.LENGTH_SHORT).show();
        }
    }


   /* private void sendSMS() {
        String phone = phonetext.getText().toString().trim();
        String firstname = nameEditText1.getText().toString();
        String lastname = nameEditText2.getText().toString();



        String message = "Hello " + firstname + " " + lastname + "! Thank you for selecting: " ;

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, message, null, null);
            Toast.makeText(this, "Message sent successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to send message", Toast.LENGTH_SHORT).show();
        }
    }*/
    private boolean appInstalledOrNot(String url) {

        PackageManager packageManager =getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }catch (PackageManager.NameNotFoundException e){
            app_installed = false;
        }
        return app_installed;
    }



       /* private void sendWhatsAppMessage() {
            String phone = phonetext.getText().toString();
            String firstname = nameEditText1.getText().toString();
            String message = "Hello " + firstname + "! Thank you for registering for the event.";

            try {
                Uri uri = Uri.parse("smsto:" + phone);
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.setPackage("com.whatsapp");
                intent.putExtra("hello", message);

                startActivity(intent);
            } catch (Exception e) {
                // An exception might occur if WhatsApp is not installed
                Toast.makeText(MainActivity.this, "WhatsApp is not installed on your device.", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }*/
}





