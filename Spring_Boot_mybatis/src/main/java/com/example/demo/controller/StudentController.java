package com.example.demo.controller;

import com.example.demo.core.response.DataResponse;
import com.example.demo.db.mapper.StudentMapper;
import com.example.demo.db.model.Student;
import com.example.demo.db.service.StudentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 18:05 2020/4/21
 * @Description:
 * @Modifyed_By:
 */

@RestController
@ComponentScan("com.example.demo.*")
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
    private DataResponse<List<Student>> searchStudent(HttpServletRequest req, HttpServletResponse resp){

        List<Student> list = null;
        DataResponse<List<Student>> dataResponse = new DataResponse<>();
        try {
            list = studentService.selectAllStudent();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        req.setAttribute("studentlist",list);
//        return "/student.jsp";
        dataResponse.setCode(0);
        dataResponse.setMsg("查询成功");
        dataResponse.setData(list);
        return dataResponse;
    }

    /**
     * 添加学生
     * @param studentId
     * @param studentName
     * @param resp
     * @throws IOException
     */
    @RequestMapping("addstudent")
    private DataResponse<String> addStudent(@RequestBody Student student) {
        DataResponse<String> dataResponse = new DataResponse<>();
        String response = "添加成功";
//        long id = Long.parseLong(studentId);
//        Student newStudent = new Student();
//        newStudent.setStudentId(id);
//        newStudent.setStudentName(studentName);
//        resp.setContentType("text/html;charset=UTF-8");
        try {
            response = studentService.addStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataResponse.setCode(1);
        dataResponse.setMsg("成功");
        dataResponse.setData(response);
        return dataResponse;
//        try {
//            resp.getWriter().println(response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        resp.setHeader("refresh","1;URL=index.jsp");
    }

}
