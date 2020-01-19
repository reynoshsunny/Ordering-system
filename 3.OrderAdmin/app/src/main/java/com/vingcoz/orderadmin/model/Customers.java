package com.vingcoz.orderadmin.model;

public class Customers {

    private String NAME;
    private String ADDRESS;
    private String PLACE;
    private String PINCODE;
    private String PHONE_NO;
    private String EMAIL_ID;
    private String X_CORD;
    private String Y_CORD;



    public Customers(String NAME, String ADDRESS, String PLACE, String PINCODE, String PHONE_NO, String EMAIL_ID, String X_CORD, String Y_CORD) {
        this.NAME = NAME;
        this.ADDRESS = ADDRESS;
        this.PLACE = PLACE;
        this.PINCODE = PINCODE;
        this.PHONE_NO = PHONE_NO;
        this.EMAIL_ID = EMAIL_ID;
        this.X_CORD = X_CORD;
        this.Y_CORD = Y_CORD;
    }

    public String getName() {
        return NAME;
    }

    public String getAddress() {
        return ADDRESS;
    }

    public String getPlace() {
        return PLACE;
    }

    public String getPincode() {
        return PINCODE;
    }

    public String getPhone_no() {
        return PHONE_NO;
    }

    public String getEmail_id() {
        return EMAIL_ID;
    }

    public String getX_code() {
        return X_CORD;
    }

    public String getBio() {
        return Y_CORD;
    }
}