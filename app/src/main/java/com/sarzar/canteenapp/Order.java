package com.sarzar.canteenapp;

public class Order {

    // string variable for
    // storing employee name.
    private String nameCode;

    // string variable for storing
    // employee contact number
    private String FoodCat;

    // string variable for storing
    // employee address.
    private String FoodIt;

    // string variable for
    // storing employee name.
    private String PlateLeft;

    // string variable for storing
    // employee contact number
    private String PlatesPending;

    // string variable for storing
    // employee address.
    private String LunchTime;

    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public Order() {

    }

    // created getter and setter methods
    // for all our variables.
    public String getNameCode() {
        return nameCode;
    }
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public String getFoodCat() {
        return FoodCat;
    }
    public void setFoodCat(String FoodCat) {this.FoodCat = FoodCat;}

    public String getFoodIt() {
        return FoodIt;
    }
    public void setFoodIt(String FoodIt) {this.FoodIt = FoodIt;}

    public String getPlateLeft() {
        return PlateLeft;
    }
    public void setPlateLeft(String PlateLeft) {
        this.PlateLeft = PlateLeft;
    }

    public String getPlatesPending() {
        return PlatesPending;
    }
    public void setPlatesPending(String PlatesPending) {this.PlatesPending = PlatesPending;}

    public String getLunchTime() {
        return LunchTime;
    }
    public void setLunchTime(String LunchTime) {this.LunchTime = LunchTime;}
}
