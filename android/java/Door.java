package com.example.setu4.home_v4;

/**
 * Created by setu4 on 02-Jun-16.
 */
public class Door {
    private String sr, date, time, userid, code, action;

    public Door(String sr, String date, String time, String userid, String code, String action)
    {
        this.setSr(sr);
        this.setDate(date);
        this.setTime(time);
        this.setUserid(userid);
        this.setCode(code);
        this.setAction(action);
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
