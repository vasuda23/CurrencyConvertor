package com.example.myapplication;

import androidx.annotation.NonNull;

public class recyclermodel {

    private String Pri_cur;
    private String cur_value;
    private String conv_value;

    public recyclermodel(String pri_cur, String cur_value, String conv_value) {
        Pri_cur = pri_cur;
        this.cur_value = cur_value;
        this.conv_value = conv_value;
    }

    public recyclermodel() {
    }

    public String getPri_cur() {
        return Pri_cur;
    }

    public void setPri_cur(String pri_cur) {
        Pri_cur = pri_cur;
    }

    public String getCur_value() {
        return cur_value;
    }

    public void setCur_value(String cur_value) {
        this.cur_value = cur_value;
    }

    public String getConv_value() {
        return conv_value;
    }

    public void setConv_value(String conv_value) {
        this.conv_value = conv_value;
    }
}
