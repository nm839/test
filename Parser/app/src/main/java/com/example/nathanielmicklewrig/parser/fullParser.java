package com.example.nathanielmicklewrig.parser;

/**
 * Created by Nathaniel Micklewright, Pol Piella & Nicholas Menzel on 01/03/2017.
 */

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class fullParser{
    private XmlPullParser parser;
    private static final String ns = null;

    public fullParser(){}

    public List<Object> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            List<Object> fullParsedFile;
            parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in,null);
            parser.nextTag();
            fullParsedFile = readFeed(parser);
            return fullParsedFile;
        } finally {
            in.close();
        }
    }


    private List<Object> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Object> document = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "document");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("slideshow")) {
                document = readSlideshow(parser);
            } else {
                skip(parser);
            }
        }
        return document;
    }


    List readSlideshow(XmlPullParser parser) throws XmlPullParserException, IOException{
        List slides = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "slideshow");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("slide")) {
                slides.add(readSlide(parser));
            } else {
                skip(parser);
            }
        }
        return slides;
    }


    List<Object> readSlide(XmlPullParser parser) throws XmlPullParserException, IOException{
        List<Object> content = new ArrayList();
        parser.require(XmlPullParser.START_TAG, ns, "slide");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            if (name.equals("graphic")) {
                content.add(readGraphicsClass(parser));
            } else if (name.equals("image")){
                content.add(readImageClass(parser));
            } else if (name.equals("audio")){
                content.add(readAudioClass(parser));
            } else if (name.equals("video")){
                content.add(readVideoClass(parser));
            } else if (name.equals("text")){
                content.add(readTextClass(parser));
            }  else {
                skip(parser);
            }
        }
        return content;
    }

    Text readTextClass (XmlPullParser parser) throws XmlPullParserException, IOException{
        Text text = new Text();
        int id = 0;
        int layer = 0;
        boolean visibility = true;
        int startsequence = 0;
        int endsequence = 0;
        float duration = 0;
        String textcontent = null;
        float xposition = 0;
        float yposition = 0;
        float xsize = 0;
        float ysize= 0;
        String font = null;
        int fontsize = 0;
        String fontcolour = null;
        String bgcolour = null;
        String bordercolour = null;
        String onclickaction = null;
        String onclickinfo = null;
        boolean aspectratiolock = true;
        float elementaspectratio = 0;

        parser.require(XmlPullParser.START_TAG, ns, "text");
        id = Integer.parseInt(parser.getAttributeValue(null, "elementid"));
        layer = Integer.parseInt(parser.getAttributeValue(null, "layer"));
        visibility = Boolean.parseBoolean(parser.getAttributeValue(null, "visibility"));
        startsequence = Integer.parseInt(parser.getAttributeValue(null, "startsequence"));
        try{
            endsequence = Integer.parseInt(parser.getAttributeValue(null, "endsequence"));
        } catch(Exception e){}
        try{
            duration = Float.parseFloat(parser.getAttributeValue(null, "duration"));
        } catch(Exception e){}

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("textcontent")) {
                textcontent = readTextContent(parser);
            } else if (name.equals("xposition")) {
                xposition = readFloat(parser);
            } else if (name.equals("yposition")) {
                yposition = readFloat(parser);
            } else if (name.equals("xsize")) {
                xsize = readFloat(parser);
            } else if (name.equals("ysize")) {
                ysize = readFloat(parser);
            } else if (name.equals("font")) {
                font = readText(parser);
            } else if (name.equals("fontsize")) {
                fontsize = Integer.parseInt(readText(parser));
            } else if (name.equals("fontcolour")) {
                fontcolour = readText(parser);
            } else if (name.equals("bgcolour")) {
                bgcolour = readText(parser);
            } else if (name.equals("bordercolour")) {
                bordercolour = readText(parser);
            } else if (name.equals("onclickaction")) {
                onclickaction = readText(parser);
            } else if (name.equals("onclickinfo")) {
                onclickinfo = readText(parser);
            } else if (name.equals("aspectratiolock")) {
                aspectratiolock = readBool(parser);
            } else if (name.equals("endtime")) {
                elementaspectratio = readFloat(parser);
            }  else {
                skip(parser);
            }
        }

        text.setID(id);
        text.setLayer(layer);
        text.setVisibility(visibility);
        text.setStartSequence(startsequence);
        text.setEndSequence(endsequence);
        text.setDuration(duration);
        text.setTextContent(textcontent);
        text.setXPosition(xposition);
        text.setYPosition(yposition);
        text.setXSize(xsize);
        text.setYSize(ysize);
        text.setFont(font);
        text.setFontSize(fontsize);
        text.setFontColour(fontcolour);
        text.setBgColour(bgcolour);
        text.setBorderColour(bordercolour);
        text.setOnClickAction(onclickaction);
        text.setOnClickInfo(onclickinfo);
        text.setAspectRatioLock(aspectratiolock);
        text.setAspectRatio(elementaspectratio);
        return text;
    }

    private String readTextContent (XmlPullParser parser) throws XmlPullParserException, IOException{
        String textContent = null;
        parser.require(XmlPullParser.START_TAG, ns, "textcontent");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            textContent.concat("<" + name + "> " + readText(parser) + " </" + name + ">");
        }

        return textContent;
    }

    Video readVideoClass (XmlPullParser parser) throws XmlPullParserException, IOException{
        Video video = new Video();
        int id = 0;
        int layer = 0;
        boolean visibility = true;
        int startsequence = 0;
        int endsequence = 0;
        float duration = 0;
        float xposition = 0;
        float yposition = 0;
        float xsize = 0;
        float ysize = 0;
        String path = null;
        String onclickaction = null;
        String onclickinfo = null;
        boolean loop = true;
        boolean aspectratiolock = true;
        float elementaspectratio = 0;
        boolean autoplay = true;
        float starttime = 0;
        float endtime = 0;

        parser.require(XmlPullParser.START_TAG, ns, "video");
        id = Integer.parseInt(parser.getAttributeValue(null, "elementid"));
        layer = Integer.parseInt(parser.getAttributeValue(null, "layer"));
        visibility = Boolean.parseBoolean(parser.getAttributeValue(null, "visibility"));
        startsequence = Integer.parseInt(parser.getAttributeValue(null, "startsequence"));
        try{
            endsequence = Integer.parseInt(parser.getAttributeValue(null, "endsequence"));
        } catch(Exception e){}
        try{
            duration = Float.parseFloat(parser.getAttributeValue(null, "duration"));
        } catch(Exception e){}

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("xposition")) {
                xposition = readFloat(parser);
            } else if (name.equals("yposition")) {
                yposition = readFloat(parser);
            } else if (name.equals("xsize")) {
                xsize = readFloat(parser);
            } else if (name.equals("ysize")) {
                ysize = readFloat(parser);
            } else if (name.equals("path")) {
                path = readText(parser);
            } else if (name.equals("onclickaction")) {
                onclickaction = readText(parser);
            } else if (name.equals("onclickinfo")) {
                onclickinfo = readText(parser);
            } else if (name.equals("loop")) {
                loop = readBool(parser);
            } else if (name.equals("aspectratiolock")) {
                aspectratiolock = readBool(parser);
            } else if (name.equals("elementaspectratio")) {
                elementaspectratio = readFloat(parser);
            } else if (name.equals("autoplay")) {
                autoplay = readBool(parser);
            } else if (name.equals("starttime")) {
                starttime = readFloat(parser);
            } else if (name.equals("endtime")) {
                endtime = readFloat(parser);
            }  else {
                skip(parser);
            }
        }

        video.setID(id);
        video.setLayer(layer);
        video.setVisibility(visibility);
        video.setStartSequence(startsequence);
        video.setEndSequence(endsequence);
        video.setDuration(duration);
        video.setXPosition(xposition);
        video.setYPosition(yposition);
        video.setXSize(xsize);
        video.setYSize(ysize);
        video.setPath(path);
        video.setOnClickAction(onclickaction);
        video.setOnClickInfo(onclickinfo);
        video.setLoop(loop);
        video.setAspectRatioLock(aspectratiolock);
        video.setAspectRatio(elementaspectratio);
        video.setAutoPlay(autoplay);
        video.setStartTime(starttime);
        video.setEndTime(endtime);
        return video;
    }




    Image readImageClass (XmlPullParser parser) throws XmlPullParserException, IOException{
        Image image = new Image();
        int id = 0;
        int layer = 0;
        boolean visibility = true;
        int startsequence = 0;
        int endsequence = 0;
        float duration = 0;
        float xposition = 0;
        float yposition = 0;
        float xsize = 0;
        float ysize = 0;
        String path = null;
        String onclickaction = null;
        String onclickinfo = null;
        float opacity = 0;
        boolean aspectratiolock = true;
        float elementaspectratio = 0;

        parser.require(XmlPullParser.START_TAG, ns, "image");
        id = Integer.parseInt(parser.getAttributeValue(null, "elementid"));
        layer = Integer.parseInt(parser.getAttributeValue(null, "layer"));
        visibility = Boolean.parseBoolean(parser.getAttributeValue(null, "visibility"));
        startsequence = Integer.parseInt(parser.getAttributeValue(null, "startsequence"));
        try{
            endsequence = Integer.parseInt(parser.getAttributeValue(null, "endsequence"));
        } catch(Exception e){}
        try{
            duration = Float.parseFloat(parser.getAttributeValue(null, "duration"));
        } catch(Exception e){}

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("xposition")) {
                xposition = readFloat(parser);
            } else if (name.equals("yposition")) {
                yposition = readFloat(parser);
            } else if (name.equals("xsize")) {
                xsize = readFloat(parser);
            } else if (name.equals("ysize")) {
                ysize = readFloat(parser);
            } else if (name.equals("path")) {
                path = readText(parser);
            } else if (name.equals("onclickaction")) {
                onclickaction = readText(parser);
            } else if (name.equals("onclickinfo")) {
                onclickinfo = readText(parser);
            } else if (name.equals("opacity")) {
                opacity = readFloat(parser);
            } else if (name.equals("aspectratiolock")) {
                aspectratiolock = readBool(parser);
            } else if (name.equals("elementaspectratio")) {
                elementaspectratio = readFloat(parser);
            } else {
                skip(parser);
            }
        }

        image.setID(id);
        image.setLayer(layer);
        image.setVisibility(visibility);
        image.setStartSequence(startsequence);
        image.setEndSequence(endsequence);
        image.setDuration(duration);
        image.setXPosition(xposition);
        image.setYPosition(yposition);
        image.setXSize(xsize);
        image.setYSize(ysize);
        image.setPath(path);
        image.setOnClickAction(onclickaction);
        image.setOnClickInfo(onclickinfo);
        image.setOpacity(opacity);
        image.setAspectRatioLock(aspectratiolock);
        image.setAspectRatio(elementaspectratio);

        return image;
    }

    Audio readAudioClass (XmlPullParser parser) throws XmlPullParserException, IOException{
        Audio audio = new Audio();
        int id = 0;
        int startsequence = 0;
        int endsequence = 0;
        float duration = 0;
        String path = null;
        boolean loop = true;
        boolean autoplay = true;
        float startTime = 0;
        float endTime = 0;

        parser.require(XmlPullParser.START_TAG, ns, "audio");
        id = Integer.parseInt(parser.getAttributeValue(null,"elementid"));
        startsequence = Integer.parseInt(parser.getAttributeValue(null, "startsequence"));
        try{
            endsequence = Integer.parseInt(parser.getAttributeValue(null, "endsequence"));
        } catch(Exception e){}
        try{
            duration = Float.parseFloat(parser.getAttributeValue(null, "duration"));
        } catch(Exception e){}

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("path")) {
                path = readText(parser);
            } else if (name.equals("loop")) {
                loop = readBool(parser);
            } else if (name.equals("autoplay")) {
                autoplay = readBool(parser);
            } else if (name.equals("starttime")) {
                startTime = readFloat(parser);
            } else if (name.equals("endtime")) {
                endTime = readFloat(parser);
            }else {
                skip(parser);
            }
        }

        audio.setID(id);
        audio.setStartSequence(startsequence);
        audio.setEndSequence(endsequence);
        audio.setDuration(duration);
        audio.setPath(path);
        audio.setAutoPlay(autoplay);
        audio.setLoop(loop);
        audio.setStartTime(startTime);
        audio.setEndTime(endTime);


        return audio;
    }



    Graphic readGraphicsClass(XmlPullParser parser) throws XmlPullParserException, IOException {
        Graphic graphic = new Graphic();
        int id = 0;
        int layer = 0;
        boolean visibility = true;
        int startsequence = 0;
        int endsequence = 0;
        float duration = 0;
        String onclickaction = null;
        String onclickinfo = null;
        boolean aspectratiolock = true;
        float aspectratio = 0;
        String linecolour = null;
        String fillcolour = null;

        parser.require(XmlPullParser.START_TAG, ns, "graphic");
        id = Integer.parseInt(parser.getAttributeValue(null, "elementid"));
        layer = Integer.parseInt(parser.getAttributeValue(null, "layer"));
        visibility = Boolean.parseBoolean(parser.getAttributeValue(null, "visibility"));
        startsequence = Integer.parseInt(parser.getAttributeValue(null, "startsequence"));
        try{
            endsequence = Integer.parseInt(parser.getAttributeValue(null, "endsequence"));
        } catch(Exception e){}
        try{
            duration = Float.parseFloat(parser.getAttributeValue(null, "duration"));
        } catch(Exception e){}
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("onclickaction")) {
                onclickaction = readText(parser);
            } else if (name.equals("onclickinfo")) {
                onclickinfo = readText(parser);
            } else if (name.equals("aspectratiolock")) {
                aspectratiolock = readBool(parser);
            } else if (name.equals("elementaspectratio")) {
                aspectratio = readFloat(parser);
            } else if (name.equals("linecolour")) {
                linecolour = readText(parser);
            } else if (name.equals("fillcolour")) {
                fillcolour = readText(parser);
            } else if (name.equals("polygon")) {
                Polygon polygon = new Polygon();
                graphic = readPolygon(polygon, parser);
            } else if (name.equals("oval")) {
                Oval oval = new Oval();
                graphic = readOval(oval, parser);
            } else {
                skip(parser);
            }
        }

        graphic.setID(id);
        graphic.setLayer(layer);
        graphic.setVisibility(visibility);
        graphic.setStartSequence(startsequence);
        graphic.setEndSequence(endsequence);
        graphic.setDuration(duration);
        graphic.setOnClickAction(onclickaction);
        graphic.setOnClickInfo(onclickinfo);
        graphic.setAspectRatioLock(aspectratiolock);
        graphic.setAspectRatio(aspectratio);
        graphic.setLineColour(linecolour);
        graphic.setFillColour(fillcolour);

        return graphic;
    }

    void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    boolean readBool(XmlPullParser parser) throws IOException, XmlPullParserException {
        boolean result = true;
        if (parser.next() == XmlPullParser.TEXT) {
            result = Boolean.parseBoolean(parser.getText());
            parser.nextTag();
        }
        return result;
    }

    float readFloat(XmlPullParser parser) throws IOException, XmlPullParserException {
        float result = 0;
        if (parser.next() == XmlPullParser.TEXT) {
            result = Float.parseFloat(parser.getText());
            parser.nextTag();
        }
        return result;
    }

    String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        } else{

        }
        return result;
    }

    Polygon readPolygon(Polygon graphic, XmlPullParser parser)throws XmlPullParserException, IOException{
        float[] xpositions = {};
        float[] ypositions = {};
        boolean isclosed = true;

        parser.require(XmlPullParser.START_TAG, ns, "polygon");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("xposition")) {
                xpositions = getFloatsArrayFromString(readText(parser));
            } else if (name.equals("yposition")) {
                ypositions = getFloatsArrayFromString(readText(parser));
            } else if (name.equals("isclosed")) {
                isclosed = readBool(parser);
            } else {
                skip(parser);
            }
        }

        graphic.setXPositions(xpositions);
        graphic.setYPositions(ypositions);
        graphic.setIsClosed(isclosed);
        return graphic;
    }

    Oval readOval(Oval graphic, XmlPullParser parser)throws XmlPullParserException, IOException{
        float xposition = 0;
        float yposition = 0;
        float[] radii = new float[2];
        float rotation = 0;

        parser.require(XmlPullParser.START_TAG, ns, "oval");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("xposition")) {
                xposition = readFloat(parser);
            } else if (name.equals("yposition")) {
                yposition = readFloat(parser);
            } else if (name.equals("rvertical")) {
                radii[0] = readFloat(parser);
            } else if (name.equals("rhorizontal")) {
                radii[1] = readFloat(parser);
            } else if (name.equals("rotation")) {
                rotation = readFloat(parser);
            } else {
                skip(parser);
            }
        }

        graphic.setXPosition(xposition);
        graphic.setYPosition(yposition);
        graphic.setRadii(radii);
        graphic.setRotation(rotation);
        return graphic;
    }

    float[] getFloatsArrayFromString(String text){
        float holder;
        int i = 0;
        String noBrackets  = text.substring(1, text.length() - 1);
        String[] floatsStrings = noBrackets.split(", ");
        float[] floats = new float[floatsStrings.length];
        for(String s : floatsStrings){
            holder = Float.parseFloat(s);
            floats[i] = holder;
            i++;
        }

        return floats;
    }
}
