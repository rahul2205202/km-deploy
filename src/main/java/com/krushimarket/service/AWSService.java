package com.krushimarket.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class AWSService {

    private final AmazonS3 s3Client;
    private final String bucketName = "krushimarket";
    AWSCredentials credentials = new BasicAWSCredentials("", "");
    public AWSService() {
        s3Client = AmazonS3ClientBuilder.standard()
        		.withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("eu-north-1")
                .build();
    }

    public String uploadImage(MultipartFile image) throws IOException {
        String fileName = generateUniqueFileName(image.getOriginalFilename());
        File tempFile = File.createTempFile("image", getFileExtension(image.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(image.getBytes());
        }
        PutObjectRequest request = new PutObjectRequest(bucketName, fileName, tempFile);
        s3Client.putObject(request);
        String imageUrl = s3Client.getUrl(bucketName, fileName).toString();
        tempFile.delete();
        return imageUrl;
    }


    public String generatePresignedUrl(String fileName, String contentType) {
        Date expirationTime = new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)); // 1-hour expiration

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, fileName)
                        .withMethod(HttpMethod.PUT)
                        .withContentType(contentType)
                        .withExpiration(expirationTime);

        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

        return url.toString();
    }

    private String generateUniqueFileName(String originalFilename) {
        return System.currentTimeMillis() + "-" + originalFilename;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null) {
            return "";
        }
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex);
        }
        return "";
    }

}
