package com.image.service;

import java.security.InvalidParameterException;

public class ImageSettingsBuilder {

    private final static String IMG_TYPE_THUMBNAIL = "thumbnail";
    private final static String IMG_TYPE_DETAIL = "detail";

    public ImageSettings buildImageSettings(String typeName) {

        if(typeName.equals(IMG_TYPE_THUMBNAIL)) {

            return new ImageSettings(200, 200, 90);
        }

        else if(typeName.equals(IMG_TYPE_DETAIL)) {

            return new ImageSettings(400, 400, 90);
        }

        else {
            throw new InvalidParameterException("The requested image type is not available. Valid types: thumbnail, detail ");
        }
    }

    public void scaleImage(ImageSettings.ScaleType scaleType) {

        if (scaleType == ImageSettings.ScaleType.CROP) {

        } else if (scaleType == ImageSettings.ScaleType.FILL) {

        } else if (scaleType == ImageSettings.ScaleType.SKEW) {

        }
    }
}
