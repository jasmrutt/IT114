package com.example.project_1;
import java.util.Calendar;
public class Watch {
    private String brand;
    //serial number
    private String serialN;
    private String movement;
    //water resistance
    private int waterR;
    private int year;
    private double price;
    private String image;
    private int threshold = 200;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSerialN() {
        return serialN;
    }

    public void setSerialN(String serialN) {
        this.serialN = serialN;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public int getWaterR() {
        return waterR;
    }

    public void setWaterR(int waterR) {
        this.waterR = waterR;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAge(){
        int yearC = Calendar.getInstance().get(Calendar.YEAR); // current year
        int age;
        age = yearC - this.year;
        return age;
    }

    public boolean isDiveWatch(){
        if(this.waterR >= threshold){
            return true;
        }
        return false;
    }


}
