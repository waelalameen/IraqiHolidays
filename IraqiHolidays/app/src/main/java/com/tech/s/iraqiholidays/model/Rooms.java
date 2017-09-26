package com.tech.s.iraqiholidays.model;

import com.google.gson.annotations.SerializedName;

public class Rooms {
    @SerializedName("roomid")
    private String roomId;

    public Rooms(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }
}
