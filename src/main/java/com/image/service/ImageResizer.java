package com.image.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageResizer {

    public byte[] resizeImage(BufferedImage sourceImage, String typeName) throws IOException {

        ImageSettingsBuilder imageSettingsBuilder = new ImageSettingsBuilder();
        ImageSettings imageSettings = imageSettingsBuilder.buildImageSettings(typeName);

        int width = imageSettings.getWidth();
        int height = imageSettings.getHeight();

        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();

        g.drawImage(sourceImage, 0, 0, width, height, null);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", os);

        return os.toByteArray();
    }
}
