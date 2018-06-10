package com.image.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UploadImageController {

    private final static String BUCKET_NAME = "bucket-10";

    @RequestMapping(value="/")
    public String uploadImage() {

        return "uploadfile";
    }
}
