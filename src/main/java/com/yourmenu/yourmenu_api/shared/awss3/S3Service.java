package com.yourmenu.yourmenu_api.shared.awss3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String bucket;
    private final String region;

    public S3Service(S3Client s3Client,
                     @Value("${AWS_S3_BUCKET}") String bucket,
                     @Value("${AWS_REGION}") String region) {
        this.s3Client = s3Client;
        this.bucket = bucket;
        this.region = region;
    }

    public String uploadFile(MultipartFile file) {
        try {
            String key = UUID.randomUUID().toString() + file.getOriginalFilename();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(
                    putObjectRequest,
                    RequestBody.fromBytes(file.getBytes()));

            return String.format("https://%s.s3.%s.amazonaws.com/%s", bucket, region, key);
        } catch (IOException e) {
            throw new RuntimeException("Falha no upload S3: " + e.getMessage(), e);
        }
    }

    public void deleteFile(String imagemAntiga) {
        try {
            s3Client.deleteObject(DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(imagemAntiga)
                    .build());

        } catch (Exception e) {
            throw new RuntimeException("Falha ao deletar o arquivo S3: " + e.getMessage(), e);
        }
    }

    public String updateFile(MultipartFile file, Object object) {
//        deleteFile(object.getImagem());
//        return uploadFile(file);
        return "";
    }
}
