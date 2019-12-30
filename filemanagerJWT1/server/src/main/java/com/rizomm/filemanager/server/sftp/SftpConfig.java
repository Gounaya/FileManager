package com.rizomm.filemanager.server.sftp;

import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpSession;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.time.LocalDateTime;

@Service
public class SftpConfig {

    FTPClient ftpClient;

    public void uploadFile(String serverAddress,
                           Integer serverPort,
                           String username,
                           String password,
                           MultipartFile file,
                           String destinationPath
    ) throws JSchException, SftpException, IOException {
        Session session = null;
        ChannelSftp channelSftp = null;
        try {


            JSch jsch = new JSch();
            session = jsch.getSession(username, serverAddress, serverPort);
            session.setPassword(password);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.cd(destinationPath);
            channelSftp.put(file.getInputStream(), file.getOriginalFilename(), ChannelSftp.OVERWRITE);

            channelSftp.disconnect();
            session.disconnect();
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }

            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }

    public void deleteFile(String serverAddress,
                           Integer serverPort,
                           String username,
                           String password,
                           String file,
                           String destinationPath
    ) throws JSchException, SftpException, IOException {
        ChannelSftp channelSftp = null;


        Session session = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, serverAddress, serverPort);
            session.setPassword(password);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.cd(destinationPath);
            channelSftp.rm(file);

            channelSftp.disconnect();
            session.disconnect();
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }

            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }
}
