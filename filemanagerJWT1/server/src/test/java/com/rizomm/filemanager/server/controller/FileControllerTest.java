package com.rizomm.filemanager.server.controller;

import io.minio.MinioClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {FileController.class})
@RunWith(SpringRunner.class)
public class FileControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MinioClient minioClient;

    @Test
    public void createBucketIfDoesntExist() throws  Exception {

    }

    @Test
    public void uploadFile() throws Exception {

    }

    @Test
    public void uploadFiles() throws Exception {

    }

    @Test
    public void getFiles() throws Exception {

    }
}
