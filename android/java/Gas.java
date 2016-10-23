package com.example.setu4.home_v4;

/**
 * Created by setu4 on 02-Jun-16.
 */
public class Gas {
    private String sr, date, time, value, wrlvl;

    public Gas(String sr, String date, String time, String value, String wrlvl)
    {
        this.setSr(sr);
        this.setDate(date);
        this.setTime(time);
        this.setValue(value);
        this.setWrlvl(wrlvl);
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getWrlvl() {
        return wrlvl;
    }

    public void setWrlvl(String wrlvl) {
        this.wrlvl = wrlvl;
    }
}
