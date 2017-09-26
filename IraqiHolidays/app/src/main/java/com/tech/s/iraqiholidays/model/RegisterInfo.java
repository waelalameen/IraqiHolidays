package com.tech.s.iraqiholidays.model;

import com.google.gson.annotations.SerializedName;

public class RegisterInfo {
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("agree")
    private int agree;
    @SerializedName("is_company")
    private int isCompany;
    @SerializedName("gender")
    private int gender;
    @SerializedName("phone")
    private String phoneNumber;
    @SerializedName("result")
    private String result;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getAgree() {
        return agree;
    }

    public int getIsCompany() {
        return isCompany;
    }

    public int getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getResult() {
        return result;
    }
}
