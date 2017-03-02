package com.example.nathanielmicklewrig.parser;

/**
 * Created by Nathaniel Micklewright on 16/02/2017.
 */

public class Graphic {
    private int id;
    private int layer;
    private boolean visibility;
    private int startsequence;
    private int endsequence;
    private float duration;
    private String onclickaction;
    private String onclickinfo;
    private boolean aspectratiolock;
    private float aspectratio;
    private String linecolour;
    private String fillcolour;

    public Graphic(){
    }

    public void setID(int id){
        this.id = id;
    }

    public void setLayer(int layer){
        this.layer = layer;
    }

    public void setVisibility(boolean visability){
        this.visibility = visability;
    }

    public void setStartSequence(int sequence){
        this.startsequence = sequence;
    }

    public void setEndSequence(int sequence){
        this.endsequence = sequence;
    }

    public void setDuration(float duration){
        this.duration = duration;
    }

    public void setOnClickAction(String action){
        this.onclickaction = action;
    }

    public void setOnClickInfo(String info){
        this.onclickinfo = info;
    }

    public void setAspectRatioLock(boolean lock){
        this.aspectratiolock = lock;
    }

    public void setAspectRatio(float aspectRatio){
        this.aspectratio = aspectRatio;
    }

    public void setLineColour(String colour){
        this.linecolour = colour;
    }

    public void setFillColour(String colour){
        this.fillcolour = colour;
    }

    public int getID(){
        return this.id;
    }

    public int getLayer(){
        return this.layer;
    }

    public boolean getVisibility(){
        return this.visibility;
    }

    public int getStartSequence(){
        return this.startsequence;
    }

    public int getEndSequence(){
        return this.endsequence;
    }

    public float getDuration(){
        return this.duration;
    }

    public String getOnClickAction(){
        return this.onclickaction;
    }

    public String getOnClickInfo(){
        return this.onclickinfo;
    }

    public boolean getAspectRatioLock(){
        return this.aspectratiolock;
    }

    public float getAspectRatio(){
        return this.aspectratio;
    }

    public String getLineColour(){
        return this.linecolour;
    }

    public String getFillColour(){
        return this.fillcolour;
    }
}
