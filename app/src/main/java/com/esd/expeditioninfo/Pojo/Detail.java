package com.esd.expeditioninfo.Pojo;

import com.google.gson.annotations.SerializedName;

public class Detail {

    @SerializedName("shipper")
    private String shipper;

    @SerializedName("receiver")
    private String receiver;

    @SerializedName("origin")
    private String origin;

    @SerializedName("destination")
    private String destination;

    public String getShipper() {
        return shipper;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }
}