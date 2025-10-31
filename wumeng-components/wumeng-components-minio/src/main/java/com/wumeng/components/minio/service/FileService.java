package com.wumeng.components.minio.service;

import com.wumeng.components.minio.exception.WmFileException;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {

    private final MinioClient minioClient;
    private final BucketService bucketService;

    /**
     * 基础的文件上传方法
     *
     * @param inputStream 输入流
     * @param size        文件大小
     * @param objectName    文件名
     * @param contentType 类型
     * @param bucketName  桶名称
     * @return
     */
    private boolean upload(InputStream inputStream,
                          String bucketName,
                          long size,
                          String objectName,
                          String contentType
    ) {
        boolean bucketExists = bucketService.isBucketExists(bucketName);
        if (!bucketExists) {
            boolean isSuccess = bucketService.createBucket(bucketName);
            if (!isSuccess) {
                throw new WmFileException("bucket创建失败，bucketName: " + bucketName);
            }
        }
        try {
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(inputStream, size, -1)
                    .contentType(contentType)
                    .build();
            minioClient.putObject(args);
            return true;
        } catch (Exception e) {
            log.error("upload file error, {}", e.getMessage(), e);
            throw new WmFileException("文件上传失败");
        }
    }

    /**
     * 基于文件流上传文件
     * @param file          文件对象
     * @param bucketName    桶名称
     * @return
     */
    public boolean upload(MultipartFile file, String bucketName, String objectName) {
        try {
            return this.upload(file.getInputStream(), bucketName, file.getSize(), objectName, file.getContentType());
        } catch (IOException e) {
            log.error("upload file error, {}", e.getMessage(), e);
            throw new WmFileException("文件上传失败");
        }
    }
}
