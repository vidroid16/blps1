package com.example.demo.DataBase;

import java.util.ArrayList;

public interface DataBaseManageble<T> {
    public void add(T object);
    public ArrayList<T> getByName(String name);
}