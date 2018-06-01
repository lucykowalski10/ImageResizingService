package com.image.service;

public class ImageSettingsBuilder {

    public ImageSettings buildImageSettings(String typeName) {

        if (typeName.equals("thumbnail")) {

            return new ImageSettings(200, 200, 90);
        }

        return new ImageSettings();
    }

    public void scaleImage(ImageSettings.ScaleType scaleType) {

        if (scaleType == ImageSettings.ScaleType.CROP) {

        } else if (scaleType == ImageSettings.ScaleType.FILL) {

        } else if (scaleType == ImageSettings.ScaleType.SKEW) {

        }
    }
}
