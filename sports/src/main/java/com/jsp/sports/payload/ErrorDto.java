package com.jsp.sports.payload;

import java.util.Date;

public class ErrorDto {

    private String msg;
    private Date date;
    private String url;

    public ErrorDto(Date date, String msg, String url) {
        this.date = date;
        this.msg = msg;
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
