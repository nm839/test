package com.example.nathanielmicklewrig.parser;

/**
 * Created by Pol Piella on 25/02/17.
 */

public class Image {
    private int id;
    private int layer;
    private boolean visibility;
    private int startsequence;
    private int endsequence;
    private float duration;
    private float xposition;
    private float yposition;
    private float xsize;
    private float ysize;
    private String path;
    private String onclickaction;
    private String onclickinfo;
    private float opacity;
    private boolean aspectratiolock;
    private float elementaspectratio;

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public int getStartSequence() {
        return startsequence;
    }

    public void setStartSequence(int startsequence) {
        this.startsequence = startsequence;
    }

    public int getEndSequence() {
        return endsequence;
    }

    public void setEndSequence(int endsequence) {
        this.endsequence = endsequence;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
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

    public float getXSize() {
        return xsize;
    }

    public void setXSize(float xsize) {
        this.xsize = xsize;
    }

    public float getYSize() {
        return ysize;
    }

    public void setYSize(float ysize) {
        this.ysize = ysize;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOnClickAction() {
        return onclickaction;
    }

    public void setOnClickAction(String onclickaction) {
        this.onclickaction = onclickaction;
    }

    public String getOnClickInfo() {
        return onclickinfo;
    }

    public void setOnClickInfo(String onclickinfo) {
        this.onclickinfo = onclickinfo;
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public boolean getAspectRatioLock() {
        return aspectratiolock;
    }

    public void setAspectRatioLock(boolean aspectratiolock) {
        this.aspectratiolock = aspectratiolock;
    }

    public float getAspectRatio() {
        return elementaspectratio;
    }

    public void setAspectRatio(float elementaspectratio) {
        this.elementaspectratio = elementaspectratio;
    }
}
