package com.image.service.controller;

import com.image.service.ImageResizer;
import com.image.service.ImageSettingsBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.logging.Logger;

@RestController
public class ImageController {

    private final static Logger logger = Logger.getLogger("log");
    private final static String SOURCE_IMG_DIR = "https://s3.eu-west-2.amazonaws.com/bucket-10/resources/sourceImages/";
    private final static String RESIZED_IMG_DIR = "https://s3.eu-west-2.amazonaws.com/bucket-10/resources/resizedImages/";

    /**
     * @param fileName - the name of the image file
     * @param typeName - the type of the image file (e.g. thumbnail)
     * @return ResponseEntity - the resized image
     */
    @RequestMapping(value = "/image/show/{typeName}/seo/{fileName}")
    public ResponseEntity<byte[]> requestImage(@PathVariable String fileName, @PathVariable String typeName) {

        ImageSettingsBuilder imageSettingsBuilder = new ImageSettingsBuilder();
        imageSettingsBuilder.buildImageSettings(typeName);
        byte[] resizedImage = this.getResizedImageFile(fileName, typeName);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resizedImage);
    }

    /**
     * Check if resized file already exists, otherwise resize it
     *
     * @param fileName - the name of the image file
     * @param typeName - the type of the image file (e.g. thumbnail)
     * @return image - the resized image
     */
    private byte[] getResizedImageFile(String fileName, String typeName) {

        try {
            URL url = new URL(RESIZED_IMG_DIR + typeName + "/" + typeName + "-" + fileName);
            BufferedImage resizedBufferedImage = ImageIO.read(url);

            if (resizedBufferedImage != null) {
                logger.info("Optimized image exists");
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(resizedBufferedImage, "jpg", os);

                return os.toByteArray();
            }
        } catch (IOException e) {
            logger.warning("The URL is not valid: " + e);
        }

        try {
            URL sourceImageUrl = new URL(SOURCE_IMG_DIR + fileName);
            BufferedImage sourceImage = ImageIO.read(sourceImageUrl);
            logger.info("Optimized image does not exists");
            ImageResizer imageResizer = new ImageResizer();

            return imageResizer.resizeImage(sourceImage, typeName);

        } catch (IOException e) {
            logger.warning("The original image does not exist: " + e);
        }

        return null;
    }
    
}
