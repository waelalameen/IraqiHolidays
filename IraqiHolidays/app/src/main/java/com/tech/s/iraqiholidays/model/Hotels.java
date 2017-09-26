package com.tech.s.iraqiholidays.model;

public class Hotels {
    private String hotelId, hotelName, hotelCity, fw, pid, status, rank, extraBed, childBed, childNoBed, infant;

    public Hotels(String hotelId, String hotelName, String hotelCity, String fw, String pid, String status, String rank, String extraBed,
                  String childBed, String childNoBed, String infant) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelCity = hotelCity;
        this.fw = fw;
        this.pid = pid;
        this.status = status;
        this.rank = rank;
        this.extraBed = extraBed;
        this.childBed = childBed;
        this.childNoBed = childNoBed;
        this.infant = infant;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public String getFw() {
        return fw;
    }

    public String getPid() {
        return pid;
    }

    public String getStatus() {
        return status;
    }

    public String getRank() {
        return rank;
    }

    public String getExtraBed() {
        return extraBed;
    }

    public String getChildBed() {
        return childBed;
    }

    public String getChildNoBed() {
        return childNoBed;
    }

    public String getInfant() {
        return infant;
    }
}
