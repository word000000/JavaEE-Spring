package com.example.spring.mvc.service.Impl;

import com.example.spring.mvc.dao.StudentDao;
import com.example.spring.mvc.pojo.Student;
import com.example.spring.mvc.service.StudentService;
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
@EnableAspectJAutoProxy
@Component
@ComponentScan("com.example.spring.mvc.*")
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;

    @Override
    public List<Student> selectAllStudent(){
        return studentDao.selectAllStudent();
    }

    @Override
    public String addStudent(Student newStudent){
        List<Student> studentList = null;
        studentList = studentDao.selectAllStudent();
        boolean isExist = false;
        for(Student student:studentList){
            //使用equals方法
            if( newStudent.getStudentId().equals(student.getStudentId())){
                return "该学号已被注册,3s后跳转";
            }
        }
        if(studentDao.addStudent(newStudent)){
            return "添加成功,3s后跳转";
        }else {
            return "添加失败，请检查后再添加,3s后跳转";
        }
    }
}
