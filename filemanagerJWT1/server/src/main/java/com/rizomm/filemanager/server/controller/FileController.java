package com.rizomm.filemanager.server.controller;

import com.google.api.client.util.IOUtils;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Api(value = "Manage Files With Minio" ,description = "Allowed to upload file or a lot in AWS S3 with Minio")

@RestController
@RequestMapping("/api/minio")
public class FileController {

    private MinioClient minioClient;

    public FileController() throws InvalidPortException, InvalidEndpointException {
        minioClient = new MinioClient("https://play.min.io", "Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG");
    }

    @ApiOperation(value = "Upload A File - Minio", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
    })
    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("bucketName") String bucketName ) throws IOException, XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, InvalidArgumentException, InternalException, NoResponseException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException, InvalidResponseException, RegionConflictException {
        boolean isExist = minioClient.bucketExists(bucketName);
        if (isExist) {
            System.out.println("Bucket already exists.");
        } else {
            minioClient.makeBucket(bucketName);
        }
        minioClient.putObject(bucketName,  file.getOriginalFilename() , file.getInputStream(), file.getSize(), file.getContentType());
    }

    @ApiOperation(value = "Get A List All Files - Minio", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/getAll")
    public List<Item> getFiles(@RequestParam("bucketName") String bucketName) throws XmlPullParserException, InsufficientDataException, NoSuchAlgorithmException, IOException, NoResponseException, InvalidKeyException, InternalException, InvalidBucketNameException, ErrorResponseException {

        Iterable<Result<Item>> myObjects = minioClient.listObjects(bucketName);
        List<Item> items = new ArrayList<>();

        for (Result<Item> result : myObjects) {
            Item item = result.get();
            items.add(item);
        }
        return items;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 404, message = "File Not Found !"),
    })
    @DeleteMapping("/delete")
    public void deleteFile(@RequestParam("filename") String filename, @RequestParam("bucketName") String bucketName ) throws Exception {
            InputStream fileCopy = minioClient.getObject(bucketName, filename);
            if (!fileCopy.equals(null)){
                minioClient.removeObject(bucketName, filename);
            }else{
                throw new Exception("No file found.");
            }
    }

}
