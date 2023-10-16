package com.example.soft7035project1;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;

// A class to parse XML data for use in creating the RecyclerView feed
//
// Based on: https://developer.android.com/training/basics/network-ops/xml#java

public class XmlParser {
    private static final String ns = null;

    public List<Entry> parseFile(String filePath, Context context) throws XmlPullParserException, IOException {

//        InputStream in = new FileInputStream(filePath);

        AssetManager assetManager = context.getAssets();
        InputStream in = context.getAssets().open(filePath);

        Log.d("mydebug", in.toString());


        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries = new ArrayList<>();


        Log.d("myDebug", "preparser.require");

        parser.require(XmlPullParser.START_TAG, ns, "entries");
        Log.d("myDebug", "postparser.require");


        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            if (name.equals("entry")) {
                Log.d("myDebug", "Entry Found");
                entries.add(readEntry(parser));
            } else {
                Log.d("myDebug", "Entry Not Found");
                skip(parser);
            }
        }
        return entries;
    }

    public static class Entry {
        public final String model;
        public final String price;
        public final String year;

        private Entry(String model, String year, String price) {
            this.model = model;
            this.year = year;
            this.price = price;
        }
    }

    // Parses the contents of an entry. If it encounters a model, year, or price tag, hands them off
// to their respective "read" methods for processing. Otherwise, skips the tag.
    private Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
//        parser.require(XmlPullParser.START_TAG, ns, "entry");
        String model = null;
        String year = null;
        String price = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            switch (name) {
                case "model":
                    model = readModel(parser);
                    Log.d("MyDebug", model.toString());
                    break;
                case "year":
                    year = readYear(parser);
                    Log.d("MyDebug", year.toString());
                    break;
                case "price":
                    price = readPrice(parser);
                    Log.d("MyDebug", price.toString());
                    break;
                default:
                    skip(parser);
                    break;
            }
        }
        return new Entry(model, year, price);
    }

    // Processes model tags in the feed.
    private String readModel(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "model");
        String model = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "model");
        return model;
    }

    // Processes price tags in the feed.
    private String readPrice(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "price");
        String price = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "price");
        return price;

    }

    // Processes year tags in the feed.
    private String readYear(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "year");
        String year = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "year");
        return year;
    }

    // For the tags model and year, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }


    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
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

