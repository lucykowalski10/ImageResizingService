package com.image.service;

public class ImageSettings {

    private int width;
    private int height;
    private int quality;

    protected enum ScaleType {CROP, FILL, SKEW}

    private String fillColor;

    protected enum ImageType {JPG, PNG}

    private String sourceName;

    public ImageSettings(int width, int height, int quality) {
        this.width = width;
        this.height = height;
        this.quality = quality;
    }

    public ImageSettings() {
        this.width = 250;
        this.height = 250;
        this.quality = 90;
        this.fillColor = "#ffffff";
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}
