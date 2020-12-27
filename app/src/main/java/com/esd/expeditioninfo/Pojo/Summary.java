package com.esd.expeditioninfo.Pojo;

import com.google.gson.annotations.SerializedName;

public class Summary {

    @SerializedName("date")
    private String date;

    @SerializedName("amount")
    private String amount;

    @SerializedName("courier")
    private String courier;

    @SerializedName("service")
    private String service;

    @SerializedName("weight")
    private String weight;

    @SerializedName("awb")
    private String awb;

    @SerializedName("status")
    private String status;

    @SerializedName("desc")
    private String desc;

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public String getCourier() {
        return courier;
    }

    public String getService() {
        return service;
    }

    public String getWeight() {
        return weight;
    }

    public String getAwb() {
        return awb;
    }

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}