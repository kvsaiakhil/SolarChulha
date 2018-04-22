package com.example.akhil.solarchulha;

/**
 * Created by Akhil on 19-04-2018.
 */

public class Dish {
    private String dishName;
    private String temp;
    private String time;

    /*public Dish(String dishName, String temp, String time) {
        this.dishName = dishName;
        this.temp = temp;
        this.time = time;
    }*/

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
