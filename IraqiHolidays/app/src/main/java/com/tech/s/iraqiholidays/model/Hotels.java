package com.tech.s.iraqiholidays.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hotels {
    @SerializedName("rooms")
    private List<Rooms> rooms;

    public Hotels(List<Rooms> rooms) {
        this.rooms = rooms;
    }

    public List<Rooms> getRooms() {
        return rooms;
    }
}
