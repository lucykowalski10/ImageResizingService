# ImageResizingService

Spring Boot application to resize images and upload them to an AWS S3 bucket.

The service uses a REST Controller to handle image requests. It checks if the optimized image exists in an AWS S3 bucket. If the file does not exist, the image will be resized based on its type name (e.g. thumbnail).

# Technologies 

Java 8

Maven 3.3.9

Spring Boot 2.0.2.RELEASE

Amazon S3

For building and running the application you need:

[JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

[Maven 3](https://maven.apache.org/)

# Run ImageResizingService locally

From the terminal you can run the application with the spring boot maven plugin:

```
mvn spring-boot:run
```

The image request url has two path variables, the typeName and the imageName. Example request url: 

```
curl http://localhost:8080/image/show/<typeName>/seo/<imageName>

curl http://localhost:8080/image/show/thumbnail/seo/c.jpg
```
This will check if an optimized image exists on S3:
```
https://s3.eu-west-2.amazonaws.com/yourBucketName/resources/resizedImages/typeName-imageName.jpg
```
If the optimized image does not exist, the service will check if a source image with that name exists on S3:
```
https://s3.eu-west-2.amazonaws.com/yourBucketName/resources/sourceImages/typeName-imageName.jpg
```

# ImageSettingsBuilder

The typeName 'thumbnail' will resize an image to 200x200 pixels.

The typeName 'detail' will resize an image to 400x400 pixels.



