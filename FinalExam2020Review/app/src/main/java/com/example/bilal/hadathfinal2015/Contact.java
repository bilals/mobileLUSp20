package com.example.bilal.hadathfinal2015;

import java.io.Serializable;

/**
 * Created by Bilal on 10/15/2015.
 */
public class Contact implements Serializable {

    private String name;
    private String phoneNumber;
    private boolean isFavorite;

    public Contact(String phoneNumber) {
        this("No name", phoneNumber);
    }

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        isFavorite = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
