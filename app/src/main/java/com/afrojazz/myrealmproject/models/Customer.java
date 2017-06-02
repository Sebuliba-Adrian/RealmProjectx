package com.afrojazz.myrealmproject.models;

import io.realm.RealmObject;

/**
 * Created by ADRIAN on 6/1/2017.
 */

public class Customer extends RealmObject {
    private String customer_name;
    private int customer_number;

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(int customer_number) {
        this.customer_number = customer_number;
    }
}
