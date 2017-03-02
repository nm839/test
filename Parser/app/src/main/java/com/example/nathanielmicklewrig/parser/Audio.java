package com.example.nathanielmicklewrig.parser;

/**
 * Created by Pol Piella on 25/02/17.
 */

public class Audio {    
    private int id;
    private int startsequence;
    private int endsequence;
    private float duration;
    private String path;
    private boolean loop;
    private boolean autoplay;
    private float startTime;
    private float endTime;

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean getLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public boolean getAutoPlay() {
        return autoplay;
    }

    public void setAutoPlay(boolean autoplay) {
        this.autoplay = autoplay;
    }

    public float getStartTime() {
        return startTime;
    }

    public void setStartTime(float startTime) {
        this.startTime = startTime;
    }

    public float getEndTime() {
        return endTime;
    }

    public void setEndTime(float endTime) {
        this.endTime = endTime;
    }

}
