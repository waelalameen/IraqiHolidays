package com.tech.s.iraqiholidays.model;

import com.google.gson.annotations.SerializedName;

public class CheckLoginInfo {
    @SerializedName("sid")
    private String sid;
    @SerializedName("uid")
    private String uid;

    public CheckLoginInfo(String sid, String uid) {
        this.sid = sid;
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public String getUid() {
        return uid;
    }
}
