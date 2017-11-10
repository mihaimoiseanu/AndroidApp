package com.example.androidapp.commons.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BadgeCounts {

    @SerializedName("bronze")
    @Expose
    private long bronze;
    @SerializedName("silver")
    @Expose
    private long silver;
    @SerializedName("gold")
    @Expose
    private long gold;

    public long getBronze() {
        return bronze;
    }

    public void setBronze(long bronze) {
        this.bronze = bronze;
    }

    public BadgeCounts withBronze(long bronze) {
        this.bronze = bronze;
        return this;
    }

    public long getSilver() {
        return silver;
    }

    public void setSilver(long silver) {
        this.silver = silver;
    }

    public BadgeCounts withSilver(long silver) {
        this.silver = silver;
        return this;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public BadgeCounts withGold(long gold) {
        this.gold = gold;
        return this;
    }
}