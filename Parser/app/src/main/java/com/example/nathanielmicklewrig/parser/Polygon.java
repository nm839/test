package com.example.nathanielmicklewrig.parser;

/**
 * Created by Nathaniel Micklewright on 16/02/2017.
 */

public class Polygon extends Graphic {
    private float[] xpositions;
    private float[] ypositions;
    private boolean isclosed;

    public Polygon(){
        this.xpositions= new float[0];
        this.ypositions= new float[0];
    }

    public float[] getXPositions() {
        return xpositions;
    }

    public void setXPositions(float[] xpositions) {
        this.xpositions = xpositions;
    }

    public float[] getYPositions() {
        return ypositions;
    }

    public void setYPositions(float[] ypositions) {
        this.ypositions = ypositions;
    }

    public void setIsClosed(boolean isClosed){
        this.isclosed = isClosed;
    }

    public boolean getIsClosed() {
        return isclosed;
    }
}
