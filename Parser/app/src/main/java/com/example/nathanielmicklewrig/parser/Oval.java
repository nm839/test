package com.example.nathanielmicklewrig.parser;

/**
 * Created by Nathaniel Micklewright on 16/02/2017.
 */

public class Oval extends Graphic {
    private float xposition;
    private float yposition;
    private float[] radii;
    private float rotation;

    public Oval(){
    }

    public float getXPosition() {
        return xposition;
    }

    public void setXPosition(float xposition) {
        this.xposition = xposition;
    }

    public float getYPosition() {
        return yposition;
    }

    public void setYPosition(float yposition) {
        this.yposition = yposition;
    }

    public void setRadii(float[] radii) {
        this.radii = radii;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float[] getRadii() {
        return radii;
    }

    public float getRotation() {
        return rotation;
    }
}
