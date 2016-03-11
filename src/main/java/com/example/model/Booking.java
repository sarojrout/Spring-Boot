package com.example.model;

import java.math.BigInteger;

/**
 * Created by saroj on 3/7/16.
 */
public class Booking {
    private BigInteger id;
    private String text;

    public Booking() {
    }

    public BigInteger getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
}
