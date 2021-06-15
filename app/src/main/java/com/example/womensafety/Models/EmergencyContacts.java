package com.example.womensafety.Models;

public class EmergencyContacts {
    String service;
    String mobile_num;

    private boolean expandable;

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    /*
    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

     */

    public EmergencyContacts(String name, String mobile_number) {
        this.service = service;
        this.service = service;
    }


    public EmergencyContacts() {
    }

    public String getService() {
        return service;
    }

    public void setService(String name) {
        this.service = service;
    }

    public String getMobile_num() {
        return mobile_num;
    }

    public void setMobile_num(String mobile_num) {
        this.mobile_num = mobile_num;
    }
}
