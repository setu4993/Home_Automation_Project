package com.example.setu4.home_v4;

/**
 * Created by setu4 on 30-May-16.
 */
public class Active {
    private String sr, date, time, device, devid, status;

    public Active(String sr, String date, String time, String device, String devid, String status)
    {
        this.setSr(sr);
        this.setDate(date);
        this.setTime(time);
        this.setDevice(device);
        this.setDevid(devid);
        this.setStatus(status);
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

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}