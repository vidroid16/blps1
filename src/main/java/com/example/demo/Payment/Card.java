package com.example.demo.Payment;

import java.util.Date;

public class Card {
    private String firstName;
    private String lastName;
    private Date date;
    private String number;
    private int money;

    public Card(String firstName, String lastName, Date date, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.number = number;
        this.money = 0;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }

    public void withdrawMoney(int amount) {
        this.money -= amount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
