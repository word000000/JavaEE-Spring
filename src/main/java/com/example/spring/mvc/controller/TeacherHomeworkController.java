package com.example.spring.mvc.controller;

import com.example.spring.mvc.pojo.StudentHomework;
import com.example.spring.mvc.pojo.TeacherHomework;
import com.example.spring.mvc.service.StudentHomeworkService;
import com.example.spring.mvc.service.StudentService;
import com.example.spring.mvc.service.TeacherHomeworkService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 18:03 2020/4/21
 * @Description:
 * @Modifyed_By:
 */
@RequestMapping("/")
@Controller
@ComponentScan("com.example.spring.mvc.*")
public class TeacherHomeworkController {

    @Autowired
    TeacherHomework teacherHomework;
    @Autowired
    TeacherHomeworkService teacherHomeworkService;
    @Autowired
    StudentHomeworkService studentHomeworkService;
    /**
     *跳转界面 发布作业
     */
    @RequestMapping("addHomework")
    private String addHomework(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        List<TeacherHomework> teacherHomeworkList = null;
        try {
            teacherHomeworkList = teacherHomeworkService.selectAllTeacherHomework();
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("teacherhomeworklist",teacherHomeworkList);
        return "/addhomework.jsp";
    }




    /**
     * 教师发布作业
     * @param homeworkId
     * @param homeworkTitle
     * @param resp
     * @throws IOException
     */
    @RequestMapping("createHomework")
    private void createHomework(@RequestParam(value = "homeworkid")Long homeworkId,
                                @RequestParam(value = "homeworktitle")String homeworkTitle,
                                HttpServletResponse resp) {
        TeacherHomework nth = new TeacherHomework();
        nth.setHomeworkId(homeworkId);
        nth.setHomeworkTitle(homeworkTitle);
        resp.setContentType("text/html;charset=UTF-8");
        try {
            resp.getWriter().println(teacherHomeworkService.createHomework(nth));
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.setHeader("refresh","3;URL=index.jsp");
    }



    /**
     * 跳转界面 查看作业记录
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("searchallhomework")
    private String searchAllHomework(HttpServletRequest req,HttpServletResponse resp){
        List<StudentHomework> studentHomeworkList = null;
        List<TeacherHomework> teacherHomeworkList = null;
        resp.setContentType("application/json;charset=utf-8");
        try {
            PrintWriter out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            studentHomeworkList = studentHomeworkService.selectAllStudentHomework();
            teacherHomeworkList = teacherHomeworkService.selectAllTeacherHomework();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("studenthomeworklist",studentHomeworkList);
            jsonObject.put("teacherhomeworklist",teacherHomeworkList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        req.setAttribute("json",jsonObject);
        return "/search.jsp";
    }
}
