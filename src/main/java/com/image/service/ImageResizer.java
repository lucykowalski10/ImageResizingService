package com.image.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageResizer {

    private int requestedWidth;
    private int requestedHeight;

    public byte[] resizeImage(BufferedImage sourceImage, String typeName) throws IOException {

        ImageSettingsBuilder imageSettingsBuilder = new ImageSettingsBuilder();
        ImageSettings imageSettings = imageSettingsBuilder.buildImageSettings(typeName);
        requestedWidth = imageSettings.getWidth();
        requestedHeight = imageSettings.getHeight();

        BufferedImage resizedImage = this.calculateAspectRatio(sourceImage);
        BufferedImage newImage = new BufferedImage(requestedWidth, requestedHeight, sourceImage.getType());

        Graphics2D g1 = resizedImage.createGraphics();
        g1.drawImage(sourceImage, 0, 0, requestedWidth, requestedHeight,null);
        g1.dispose();

        int imageSpaceWidth = (requestedWidth-resizedImage.getWidth())/2;
        int imageSpaceHeight = (requestedHeight-resizedImage.getHeight())/2;

        Graphics2D g2 = newImage.createGraphics();
        g2.drawImage(newImage, 0, 0, requestedWidth, requestedHeight, null);
        g2.drawImage(resizedImage, imageSpaceWidth, imageSpaceHeight,null);
        g2.dispose();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(newImage, "jpg", os);

        return os.toByteArray();
    }

    private BufferedImage calculateAspectRatio(BufferedImage sourceImage) {

        int originWidth = sourceImage.getWidth();
        int originHeight = sourceImage.getHeight();
        int newWidth = originWidth;
        int newHeight = originHeight;

        if(originWidth > requestedWidth) {

            newWidth = requestedWidth;
            newHeight = (requestedWidth * originHeight) / originWidth;
        }

        if(newHeight > requestedHeight) {

            newHeight = requestedHeight;
            newWidth = (newHeight * originWidth) / originHeight;
        }

        return new BufferedImage(newWidth, newHeight, sourceImage.getType());
    }
}
