package com.example.spring.mvc.controller;

import com.example.spring.mvc.pojo.Student;
import com.example.spring.mvc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 18:05 2020/4/21
 * @Description:
 * @Modifyed_By:
 */

@Controller
@ComponentScan("com.example.spring.mvc.*")
@RequestMapping("/")
public class StudentController {


    @Autowired
    StudentService studentService;
    /**
     * 跳转界面 学生名单操作
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    @RequestMapping("searchstudent")
    private String searchStudent(HttpServletRequest req, HttpServletResponse resp){

        List<Student> list = null;

        try {
            list = studentService.selectAllStudent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("studentlist",list);
        return "/student.jsp";
    }

    /**
     * 添加学生
     * @param studentId
     * @param studentName
     * @param resp
     * @throws IOException
     */
    @RequestMapping("addstudent")
    private void addStudent(@RequestParam(value = "studentid")Long studentId,
                            @RequestParam(value = "studentname")String studentName,
                            HttpServletResponse resp) {

        String response = "添加成功";
        Student newStudent = new Student();
        newStudent.setStudentId(studentId);
        newStudent.setStudentName(studentName);
        resp.setContentType("text/html;charset=UTF-8");
        try {
            response = studentService.addStudent(newStudent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            resp.getWriter().println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        resp.setHeader("refresh","1;URL=index.jsp");
    }

}
