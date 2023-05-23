package com.MahmmoudOsama.inheretance;

public class Person {
    String fraction;
    double amount;
    int image;
    String name;

    public Person(String name, String fraction, double amount,int image) {
        this.name = name;
        this.fraction = fraction;
        this.amount = amount;
        this.image=image;
    }
    public Person(String name){
        this.name=name;
        fraction=null;
        amount=-1;
        image= R.drawable.defult;


    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
