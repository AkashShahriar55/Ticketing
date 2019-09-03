package com.example.kvittering;

import android.net.Uri;

import java.io.Serializable;

public class configuration implements Serializable {

    private String phoneNumber;
    private String profileName;
    private String amount;
    private String currentTime;
    private String imageIcon;
    private String type;

    public configuration(String phoneNumber, String profileName, String amount, String currentTime, String imageIcon, String type) {
        this.phoneNumber = phoneNumber;
        this.profileName = profileName;
        this.amount = amount;
        this.currentTime = currentTime;
        this.imageIcon = imageIcon;
        this.type = type;
    }

    public configuration() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
