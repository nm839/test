package com.example.nathanielmicklewrig.parser;

/**
 * Created by Nathaniel Micklewrig on 26/02/2017.
 */

public class Area {
    private String name;
    private double longitude;
    private double latitude;
    private double[] polyX;
    private double[] polyY;

    public Area(){
    }

    public double[] getPolyY() {
        return polyY;
    }

    public void setPolyY(double[] polyY) {
        this.polyY = polyY;
    }

    public double[] getPolyX() {
        return polyX;
    }

    public void setPolyX(double[] polyX) {
        this.polyX = polyX;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
