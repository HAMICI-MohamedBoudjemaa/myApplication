package com.boudjemaa.hamicimohamed.myapplication;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Hamici Mohamed on 30/12/2017.
 */



public class Day implements Serializable {

    String name;
    int steps;
    int calories;
    double weight;
    Date date;

    public Day() {

    }

    public Day(String name, int steps, int calories, double weight, long date) {
        this.name = name;
        this.steps = steps;
        this.calories = calories;
        this.weight = weight;
        this.date = new Date(date);
    }

    public Day(String name, int steps, int calories, double weight, Date date) {
        this.name = name;
        this.steps = steps;
        this.calories = calories;
        this.weight = weight;
        this.date = date;
    }





    // Name
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // steps
    public int getSteps() {
        return this.steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    // calories
    public int getCalories() {
        return this.calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    // weight
    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Date
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void setDate(long date) {this.date = new Date(date);}
    public void setDate(String date) {this.date = new Date(date);}
}
