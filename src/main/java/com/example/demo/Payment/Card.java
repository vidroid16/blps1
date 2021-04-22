package com.example.demo.Payment;

import java.util.Date;

public class Card {
    private String number;
    private String cardholder;
    private Date date;
    private int cvc;

    public Card() { }

    public Card(String number, String cardholder, Date date, int cvc) {
        this.number = number;
        this.cardholder = cardholder;
        this.date = date;
        this.cvc = cvc;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }
}
