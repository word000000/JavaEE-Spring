package com.example.demo.controller;


import com.example.demo.db.model.StudentHomework;
import com.example.demo.db.service.StudentHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@Controller
@ComponentScan("com.example.demo.*")
public class StudentHomeworkController {

    @Autowired
    StudentHomeworkService studentHomeworkService;
    /**
     提交作业界面
     */
    @RequestMapping("submitHomework")
    private String submitHomework(){
        return  "/submitHomework.jsp";
    }

    /**
     学生提交作业
     */
    @RequestMapping("submit")
    private void submit(@RequestParam(value = "studentid")Long studentId,
                        @RequestParam(value = "homeworkid")Long homeworkId,
                        @RequestParam(value = "homeworkcontent")String homeworkContent,
                        HttpServletResponse resp){
        StudentHomework nsh = new StudentHomework();
        nsh.setStudentId(studentId);
        nsh.setHomeworkId(homeworkId);
        nsh.setHomeworkContent(homeworkContent);
        Timestamp dateNow = new Timestamp(System.currentTimeMillis());
        nsh.setCreatTime(dateNow);
        resp.setContentType("text/html;charset=UTF-8");
        String response = null;
        try {
            response = studentHomeworkService.addStudentHomework(nsh);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            resp.getWriter().println(response+",3s后跳转");
        } catch (IOException e) {
            e.printStackTrace();
        }

        resp.setHeader("refresh","3;URL=index.jsp");
    }
}
