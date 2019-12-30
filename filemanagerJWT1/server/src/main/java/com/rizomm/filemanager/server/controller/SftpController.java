package com.rizomm.filemanager.server.controller;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.rizomm.filemanager.server.sftp.SftpConfig;
import io.minio.errors.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(value = "Sftp to upload file" ,description = "Allowed to upload file in a server SFTP")
@RestController
@RequestMapping("/api/sftp")
public class SftpController {

    private SftpConfig sftpService;

    @Autowired
    public SftpController(SftpConfig sftpService) {
        this.sftpService = sftpService;
    }

    @ApiOperation(value = "Upload A File - SFTP", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
    })
    @PostMapping("/upload")
    public void uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("address") String sshServerAddress,
            @RequestParam("port") Integer sshServerPort,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("path") String remoteDestinationPath) throws JSchException, SftpException, IOException {

        sftpService.uploadFile(
                sshServerAddress,
                sshServerPort,
                username,
                password,
                file,
                remoteDestinationPath
        );
    }

    @DeleteMapping("/delete")
    public void deleteFile(
            @RequestParam("file") String file,
            @RequestParam("address") String sshServerAddress,
            @RequestParam("port") Integer sshServerPort,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("path") String remoteDestinationPath) throws JSchException, SftpException, IOException {

        sftpService.deleteFile(
                sshServerAddress,
                sshServerPort,
                username,
                password,
                file,
                remoteDestinationPath
        );
    }
}
