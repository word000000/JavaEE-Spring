package com.example.spring.mvc.controller;

import com.example.spring.mvc.pojo.Student;
import com.example.spring.mvc.pojo.StudentHomework;
import com.example.spring.mvc.pojo.TeacherHomework;
import com.example.spring.mvc.dao.Impl.StudentHomeworkDaoImpl;
import com.example.spring.mvc.dao.Impl.StudentDaoImpl;
import com.example.spring.mvc.dao.Impl.TeacherHomeworkDaoImpl;
import com.example.spring.mvc.service.StudentHomeworkService;
import com.example.spring.mvc.service.StudentService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Author:GQM
 * @Date:created in 8:38 2020/4/9
 * @Description:
 * @Modifyed_By:
 */

@Controller
@RequestMapping("/")
@ComponentScan("com.example.spring.mvc.*")
public class ApiController {

    @Autowired
    StudentDaoImpl studentDao;
    @Autowired
    StudentHomeworkDaoImpl studentHomeworkDao;
    @Autowired
    TeacherHomeworkDaoImpl teacherHomeworkDao;
    @Autowired
    StudentHomeworkService studentHomeworkService;
    @Autowired
    StudentService studentService;




    @ResponseBody
    @RequestMapping(value = "/test")
    private  Map<String, Object>  get(){
        List<StudentHomework> studentHomeworkList = null;
        List<TeacherHomework> teacherHomeworkList = null;
        studentHomeworkList = studentHomeworkDao.selectAllStudentHomework();
        teacherHomeworkList = teacherHomeworkDao.selectAllTeacherHomework();
        Map<String,Object> map = new HashMap<>(10);
        Map<String,Object> map1 = new HashMap<>(10);
        Map<String,Object> map2 = new HashMap<>(10);
        Map<String,Object> map3 = new HashMap<>(10);
        map.put("sh",studentHomeworkList);
        return map;
    }
}
