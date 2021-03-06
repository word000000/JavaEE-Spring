package com.example.demo.controller;


import com.example.demo.core.response.DataResponse;
import com.example.demo.db.model.StudentHomework;
import com.example.demo.db.service.StudentHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * @Author:GQM
 * @Date:created in 18:01 2020/4/21
 * @Description:
 * @Modifyed_By:
 */

@RequestMapping("/")
@RestController
@ComponentScan("com.example.demo.*")
public class StudentHomeworkController {

    @Autowired
    StudentHomeworkService studentHomeworkService;
    /**
     提交作业界面
     */
//    @RequestMapping("submitHomework")
//    private String submitHomework(){
//        return  "/submitHomework.jsp";
//    }

    /**
     学生提交作业
     */
    @RequestMapping("submit")
    private DataResponse<String> submit(Long studentId, Long homeworkId, String homeworkContent){
        DataResponse<String> dataResponse = new DataResponse<>();
        dataResponse.setCode(1);
        dataResponse.setMsg("成功");
        StudentHomework nsh = new StudentHomework();
        nsh.setStudentId(studentId);
        nsh.setHomeworkId(homeworkId);
        nsh.setHomeworkContent(homeworkContent);
        Timestamp dateNow = new Timestamp(System.currentTimeMillis());
        nsh.setCreatTime(dateNow);
        String response = null;
        try {
            response = studentHomeworkService.addStudentHomework(nsh);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataResponse.setData(response);
        return dataResponse;
    }
}
