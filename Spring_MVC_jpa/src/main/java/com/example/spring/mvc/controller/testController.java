package com.example.spring.mvc.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author:GQM
 * @Date:created in 9:32 2020/4/30
 * @Description:
 * @Modifyed_By:
 */
@Controller
@RequestMapping("/")
public class testController {

    @RequestMapping("download")
    public ResponseEntity<byte[]> fileDownLoad(HttpServletRequest request) throws Exception{
        String fileName="log_20200501_20200509.csv";
        InputStream in=new FileInputStream(new File("C:\\Users\\guoqi\\Desktop\\log_20200501_20200509.csv"));
        byte[] body=null;
        body=new byte[in.available()];
        in.read(body);
        fileName=new String(fileName.getBytes("gbk"),"iso8859-1");
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename="+fileName);
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
        return response;

    }
}
