package com.example.spring.mvc.service.Impl;

import com.example.spring.mvc.dao.StudentHomeworkDao;
import com.example.spring.mvc.dao.TeacherHomeworkDao;
import com.example.spring.mvc.pojo.Student;
import com.example.spring.mvc.pojo.StudentHomework;
import com.example.spring.mvc.pojo.TeacherHomework;
import com.example.spring.mvc.service.StudentHomeworkService;
import com.example.spring.mvc.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 15:25 2020/4/21
 * @Description:
 * @Modifyed_By:
 */

@EnableAspectJAutoProxy
@Component
@ComponentScan("com.example.spring.mvc.*")

public  class StudentHomeworkServiceImpl implements StudentHomeworkService {


    @Autowired
    StudentDao studentDao;
    @Autowired
    StudentHomeworkDao studentHomeworkDao;
    @Autowired
    TeacherHomeworkDao teacherHomeworkDao;

    @Override
    public String addStudentHomework(StudentHomework nsh){
        String respone = "提交成功";
        List<Student> slist = null;
        List<StudentHomework> shlist = null;
        List<TeacherHomework> thlist = null;
        slist = studentDao.findAllBy();
        thlist = teacherHomeworkDao.findAllBy();
        shlist = studentHomeworkDao.findAllBy();
        boolean studentExist = false;
        boolean homeworkExist = false;

        //检查学生id
        for(Student s:slist){
            if(s.getStudentId().equals(nsh.getStudentId())){
                studentExist=true;
                break;
            }
        }
        if(!studentExist){
            return  "当前输入的学生id不存在";
        }
        //检查作业是否存在
        for(TeacherHomework th:thlist){
            if(th.getHomeworkId()==nsh.getHomeworkId()){
                homeworkExist=true;
                nsh.setHomeworkTitle(th.getHomeworkTitle());
                break;
            }
        }
        if(!homeworkExist){
            return  "未发现该作业";
        }

        if(nsh.getHomeworkContent().equals("")){
            return  "作业内容不为空";
        }
        int id = shlist.size()+1;
        nsh.setId((long)id);
        if(studentHomeworkDao.addStudentHomework(nsh.getId(),nsh.getStudentId(),nsh.getHomeworkId(),nsh.getHomeworkTitle(),nsh.getHomeworkContent(),nsh.getCreatTime())>0){
            return respone;
        }else {
            return "提交失败";
        }
    }

    @Override
    public List<StudentHomework> selectAllStudentHomework() {
        List<StudentHomework> list =null;

        list= studentHomeworkDao.findAllBy();

        return list;
    }
}

