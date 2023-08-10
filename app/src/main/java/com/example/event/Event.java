package com.example.event;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Event {
    private String firstname;
    private String lastname;
    private String emailaddress;
    private String website;
    private String biodata;
    private String github;
    private String twitter;
    private String Radiogroup;
    private String phone;

    public Event(String firstname, String lastname, String emailaddress, String website, String biodata, String github, String twitter, String radiogroup, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailaddress = emailaddress;
        this.website = website;
        this.biodata = biodata;
        this.github = github;
        this.twitter = twitter;
        Radiogroup = radiogroup;
        this.phone = phone;
    }

    public Event() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBiodata() {
        return biodata;
    }

    public void setBiodata(String biodata) {
        this.biodata = biodata;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getRadiogroup() {
        return Radiogroup;
    }

    public void setRadiogroup(String radiogroup) {
        Radiogroup = radiogroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}