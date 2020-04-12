package com.example.spring.mvc.controller;

import com.example.spring.mvc.bean.Student;
import com.example.spring.mvc.bean.StudentHomework;
import com.example.spring.mvc.bean.TeacherHomework;
import com.example.spring.mvc.dao.StudentHomeworkJdbc;
import com.example.spring.mvc.dao.StudentJdbc;
import com.example.spring.mvc.dao.TeacherHomeworkJdbc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 8:38 2020/4/9
 * @Description:
 * @Modifyed_By:
 */

@Controller
@RequestMapping("/")
public class ApiController {
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
        try {
            try {
                resp.getWriter().println(StudentHomeworkJdbc.handHomework(nsh)+",3s后跳转");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        resp.setHeader("refresh","3;URL=index.jsp");
    }

    /**
     *跳转界面 发布作业
     */
    @RequestMapping("addHomework")
    private String addHomework(HttpServletRequest req,HttpServletResponse resp){
        List<TeacherHomework> teacherHomeworkList = null;
        try {
            teacherHomeworkList = TeacherHomeworkJdbc.selectAllTeacherHomework();
        } catch (ClassNotFoundException e) {
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
                                HttpServletResponse resp) throws IOException{
        TeacherHomework nth = new TeacherHomework();
        nth.setHomeworkId(homeworkId);
        nth.setHomeworkTitle(homeworkTitle);
        List<TeacherHomework> thList = null;
        try {
            thList = TeacherHomeworkJdbc.selectAllTeacherHomework();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean isExist = false;
        for(TeacherHomework th:thList){
            //使用equals方法
            if(nth.getHomeworkId()==th.getHomeworkId()){
                isExist = true;
                break;
            }
        }
        resp.setContentType("text/html;charset=UTF-8");
        if(isExist){
            //中文编码
            resp.getWriter().println("该id已被使用,3s后跳转");
            //延时跳转
        }else {
            try {
                if(nth.getHomeworkTitle().equals("")){
                    resp.getWriter().println("id不为空，请检查后再添加,3s后跳转");
                }else{
                    if(TeacherHomeworkJdbc.addHomework(nth)){
                        resp.getWriter().println("添加成功,3s后跳转");
                    }else {
                        resp.getWriter().println("添加失败，请检查后再添加,3s后跳转");
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //延时跳转
        }
        resp.setHeader("refresh","3;URL=index.jsp");
    }

    /**
     * 跳转界面 学生名单操作
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    @RequestMapping("searchstudent")
    private String searchStudent(HttpServletRequest req,HttpServletResponse resp)throws IOException{
        List<Student> list = null;
        try {
            list = StudentJdbc.selectAllStudent();
        } catch (ClassNotFoundException e) {
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
                            HttpServletResponse resp) throws IOException{
        Student newStudent = new Student();
        newStudent.setStudentId(studentId);
        newStudent.setStudentName(studentName);
        List<Student> studentList = null;
        try {
            studentList = StudentJdbc.selectAllStudent();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean isExist = false;
        for(Student student:studentList){
            //使用equals方法
            if( newStudent.getStudentId().equals(student.getStudentId())){
                isExist = true;
                break;
            }
        }
        resp.setContentType("text/html;charset=UTF-8");
        if(isExist){
            //中文编码
            resp.getWriter().println("该学号已被注册,3s后跳转");
            //延时跳转
        }else {

            try {
                if(newStudent.getStudentName().equals("")){

                    resp.getWriter().println("姓名不为空，请检查后再添加,3s后跳转");
                }else{
                    if(StudentJdbc.addStudent(newStudent)){
                        resp.getWriter().println("添加成功,3s后跳转");
                    }else {
                        resp.getWriter().println("添加失败，请检查后再添加,3s后跳转");
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //延时跳转
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
        try {
            studentHomeworkList = StudentHomeworkJdbc.selectAllStudentHomework();
//            teacherHomeworkList = TeacherHomeworkJdbc.selectAllTeacherHomework();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("studenthomeworklist",studentHomeworkList);
//        req.setAttribute("teacherhomeworklist",teacherHomeworkList);
        return "/search.jsp";
    }
}
