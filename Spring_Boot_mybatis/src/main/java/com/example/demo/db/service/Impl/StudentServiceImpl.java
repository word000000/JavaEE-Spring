package com.example.demo.db.service.Impl;


import com.example.demo.db.mapper.StudentMapper;
import com.example.demo.db.model.Student;
import com.example.demo.db.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 15:26 2020/4/21
 * @Description:
 * @Modifyed_By:
 */

@Component
@ComponentScan("com.example.demo.*")
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<Student> selectAllStudent(){
        List<Student> list = null;
        list = studentMapper.selectAllStudent();
        return list;
    }

    @Override
    public String addStudent(Student newStudent) {
        List<Student> studentList = null;
        String response ="";

        studentList = studentMapper.selectAllStudent();

        for(Student student:studentList){
            //使用equals方法
            if( newStudent.getStudentId().equals(student.getStudentId())){
                return "该学号已被注册,3s后跳转";
            }
        }
        int s = studentMapper.addStudent(newStudent);
        if(studentMapper.addStudent(newStudent)>0){
            response ="添加成功,3s后跳转";
        }else {
            response = "添加失败，请检查后再添加,3s后跳转";
        }

        return response;
    }
}
