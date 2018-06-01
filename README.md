# ImageResizingService

Spring Boot application to resize and store images in an AWS S3 bucket.

The service receives a request for an optimized image. It checks if the optimized image exists in an AWS S3 bucket. If the file does not exist, the image will be resized based on its type name (e.g. thumbnail).

#Requirements

For building and running the application you need:

[JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

[Maven 3](https://maven.apache.org/)

# Run ImageResizingService locally

From the terminal you can run the application with the spring boot maven plugin:

```
mvn spring-boot:run
```

Image request example: 

```
http://localhost:8080/image/show/\<typeName>\/seo/\<imageName>

curl http://localhost:8080/image/show/thumbnail/seo/c.jpg
```
This will check if an optimized image exists in S3:

https://s3.eu-west-2.amazonaws.com/yourBucketName/resources/resizedImages/typeName-imageName.jpg

If the optimized image does not exist, the service will check if a source image with that name exists in S3:

https://s3.eu-west-2.amazonaws.com/yourBucketName/resources/sourceImages/typeName-imageName.jpg


# Image Settings Builder

The type name 'thumbnail' will resize an image to 200x200


