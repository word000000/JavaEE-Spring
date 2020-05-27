package com.example.demo.db.service.Impl;

import com.example.demo.db.mapper.StudentHomeworkMapper;
import com.example.demo.db.mapper.StudentMapper;
import com.example.demo.db.mapper.TeacherHomeworkMapper;
import com.example.demo.db.model.Student;
import com.example.demo.db.model.StudentHomework;
import com.example.demo.db.model.TeacherHomework;
import com.example.demo.db.service.StudentHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 15:25 2020/4/21
 * @Description:
 * @Modifyed_By:
 */


@Component
@ComponentScan("com.example.demo.*")

public  class StudentHomeworkServiceImpl implements StudentHomeworkService {


    @Autowired
    StudentHomeworkMapper studentHomeworkMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherHomeworkMapper teacherHomeworkMapper;

    @Override
    public String addStudentHomework(StudentHomework nsh){
        String respone = "提交成功";
        List<Student> slist = null;
        List<StudentHomework> shlist = null;
        List<TeacherHomework> thlist = null;
        slist = studentMapper.selectAllStudent();
        thlist = teacherHomeworkMapper.selectAllTeacherHomework();
        shlist = studentHomeworkMapper.selectAllStudentHomework();
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
        if(studentHomeworkMapper.addnStudentHomework(nsh)>0){
            return respone;
        }else {
            return "提交失败";
        }
    }

    @Override
    public List<StudentHomework> selectAllStudentHomework() {
        List<StudentHomework> list =null;

        list= studentHomeworkMapper.selectAllStudentHomework();

        return list;
    }
}

