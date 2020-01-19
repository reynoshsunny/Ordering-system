package com.vingcoz.orderadmin.model;

import com.google.gson.annotations.SerializedName;

public class PinCodes {
    @SerializedName("PINCODE")
    private String PinCode;

    @SerializedName("PLACE")
    private String Place;

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }
}
