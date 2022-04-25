package com.example.androidparking;

public class P_view {
    private String id,user_name, name, v_model, v_no, mobile, time_slot, Amount;
    public P_view(){

    }

    public P_view(String id,String user_name, String name, String v_model, String v_no, String mobile, String time_slot, String Amount) {
        this.id = id;
        this.user_name = user_name;
        this.name = name;
        this.v_model = v_model;
        this.v_no = v_no;
        this.mobile = mobile;
        this.time_slot = time_slot;
        this.Amount = Amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getV_model() {
        return v_model;
    }

    public void setV_model(String v_model) {
        this.v_model = v_model;
    }

    public String getV_no() {
        return v_no;
    }

    public void setV_no(String v_no) {
        this.v_no = v_no;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
