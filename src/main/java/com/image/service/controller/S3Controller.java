package com.image.service.controller;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@RestController
public class S3Controller {

    private final static String SOURCE_IMG_BUCKET = "bucket-10/resources/sourceImages";
    private final static String RESIZED_IMG_BUCKET = "bucket-10/resources/resizedImages";
    private final static String ACCESS_KEY = ""; //put your AWS access key here
    private final static String SECRET_KEY = ""; //put your AWS secret key here
    private final static String S3_REGION = "eu-west-2";

    @PostMapping("/upload")
    public String uploadImageFileToS3(@RequestParam("file") MultipartFile file) {

        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);

        AmazonS3 s3client = AmazonS3Client.builder()
                .withRegion(S3_REGION)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        try {
            File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + file.getOriginalFilename());
            file.transferTo(tmpFile);

            //save on s3 with public read access
            s3client.putObject(new PutObjectRequest(SOURCE_IMG_BUCKET, file.getOriginalFilename(), tmpFile).withCannedAcl(CannedAccessControlList.PublicRead));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "file uploaded to S3";
    }
}
