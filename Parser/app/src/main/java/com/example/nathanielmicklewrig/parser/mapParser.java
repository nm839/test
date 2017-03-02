package com.example.nathanielmicklewrig.parser;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathaniel Micklewrig on 26/02/2017.
 */

public class mapParser {
    private static final String ns = null;

    public mapParser(){}

    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readMap(parser);
        } finally {
            in.close();
        }
    }

    private List readMap(XmlPullParser parser) throws XmlPullParserException, IOException {
        List areas = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "map");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("area")) {
                areas.add(readArea(parser));
            } else {
                skip(parser);
            }
        }
        return areas;
    }

    List readArea(XmlPullParser parser) throws XmlPullParserException, IOException{
        List areas = new ArrayList();
        Area area = new Area();

        parser.require(XmlPullParser.START_TAG, ns, "area");
        area.setName(parser.getAttributeValue(null, "name"));
        area.setLongitude(getLongitude(parser.getAttributeValue(null, "coordinate")));
        area.setLatitude(getLatitude(parser.getAttributeValue(null, "coordinate")));
        area.setPolyX(getXs(parser.getAttributeValue(null, "polygon")));
        area.setPolyY(getYs(parser.getAttributeValue(null, "polygon")));
        areas.add(area);

        List subAreas = new ArrayList();
        areas.add(subAreas);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("subArea")) {
                subAreas.add(readSubArea(parser));
            } else {
                skip(parser);
            }
        }
        return areas;
    }

    List readSubArea(XmlPullParser parser) throws XmlPullParserException, IOException{
        List areas = new ArrayList();
        Area area = new Area();

        parser.require(XmlPullParser.START_TAG, ns, "subArea");
        area.setName(parser.getAttributeValue(null, "name"));
        area.setLongitude(getLongitude(parser.getAttributeValue(null, "coordinate")));
        area.setLatitude(getLatitude(parser.getAttributeValue(null, "coordinate")));
        area.setPolyX(getXs(parser.getAttributeValue(null, "polygon")));
        area.setPolyY(getYs(parser.getAttributeValue(null, "polygon")));
        areas.add(area);

        List climbs = new ArrayList();
        areas.add(climbs);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("climb")) {
                climbs.add(readClimb(parser));
            } else {
                skip(parser);
            }
        }
        return areas;
    }

    Climb readClimb(XmlPullParser parser) throws XmlPullParserException, IOException{
        Climb climb = new Climb();

        parser.require(XmlPullParser.START_TAG, ns, "climb");
        climb.setName(parser.getAttributeValue(null, "name"));
        climb.setLongitude(getLongitude(parser.getAttributeValue(null, "coordinate")));
        climb.setLatitude(getLatitude(parser.getAttributeValue(null, "coordinate")));
        climb.setDifficulty(parser.getAttributeValue(null, "difficulty"));
        climb.setPopularity(Integer.parseInt(parser.getAttributeValue(null, "popularity")));

        return climb;
    }

    double getLongitude(String attribute){
        String[] split = attribute.split(", ");
        return Float.parseFloat(split[0]);
    }

    double getLatitude(String attribute){
        String[] split = attribute.split(", ");
        return Float.parseFloat(split[1]);
    }

    double[] getXs(String attribute){
        double holder;
        int i = 0;
        String[] split = attribute.split(", ");
        double[] doubles = new double[split.length];
        for(String s : split){
            holder = Double.parseDouble(s);
            doubles[i] = holder;
            i++;
        }

        int n = 0;
        double[] Xs = new double[(doubles.length / 2)];

        for (i = 0; i < Xs.length; i++){
            Xs[i] = doubles[n];
            n++;
            n++;
        }

        return Xs;
    }

    double[] getYs(String attribute){
        double holder;
        int i = 0;
        String[] split = attribute.split(", ");
        double[] doubles = new double[split.length];
        for(String s : split){
            holder = Double.parseDouble(s);
            doubles[i] = holder;
            i++;
        }

        int n = 1;
        double[] Ys = new double[(doubles.length / 2)];

        for (i = 0; i < Ys.length; i++){
            Ys[i] = doubles[n];
            n++;
            n++;
        }

        return Ys;
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
}
