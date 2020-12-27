package com.esd.expeditioninfo.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("summary")
    private Summary summary;

    @SerializedName("detail")
    private Detail detail;

    @SerializedName("history")
    private List<HistoryItem> history;

    public Summary getSummary() {
        return summary;
    }

    public Detail getDetail() {
        return detail;
    }

    public List<HistoryItem> getHistory() {
        return history;
    }
}