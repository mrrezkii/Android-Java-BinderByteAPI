package com.esd.expeditioninfo.Pojo;

import com.google.gson.annotations.SerializedName;

public class HistoryItem {

    @SerializedName("date")
    private String date;

    @SerializedName("location")
    private String location;

    @SerializedName("desc")
    private String desc;

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getDesc() {
        return desc;
    }
}