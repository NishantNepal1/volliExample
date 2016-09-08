package com.hfad.voliexample.modal;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 9/8/2016.
 */
public class Phone {

    @SerializedName("mobile")
    String mobile;
    @SerializedName("home")
    String home;
    @SerializedName("office")
    String office;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
