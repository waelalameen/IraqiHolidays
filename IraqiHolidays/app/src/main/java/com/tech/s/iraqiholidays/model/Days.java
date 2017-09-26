package com.tech.s.iraqiholidays.model;

public class Days {
    private String id, day, fw, pid, period;

    public Days(String id, String day, String fw, String pid, String period) {
        this.id = id;
        this.day = day;
        this.fw = fw;
        this.pid = pid;
        this.period = period;
    }

    public String getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getFw() {
        return fw;
    }

    public String getPid() {
        return pid;
    }

    public String getPeriod() {
        return period;
    }
}
