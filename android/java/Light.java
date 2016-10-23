package com.example.setu4.home_v4;

/**
 * Created by setu4 on 02-Jun-16.
 */
public class Light {
    private String sr, date, time, id, vl;

    public Light(String sr, String date, String time, String id, String vl)
    {
        this.setSr(sr);
        this.setDate(date);
        this.setTime(time);
        this.setId(id);
        this.setVl(vl);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVl() {
        return vl;
    }

    public void setVl(String vl) {
        this.vl = vl;
    }

}
