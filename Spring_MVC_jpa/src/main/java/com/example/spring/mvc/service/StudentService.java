package com.example.spring.mvc.service;

import com.example.spring.mvc.bean.Student;

import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 15:23 2020/4/21
 * @Description:
 * @Modifyed_By:
 */
public interface StudentService {

    /**
     * 查找所有学生名单
     * @return
     */
    List<Student> selectAllStudent();

    /**
     * 新增学生
     * @param newStudent
     * @return
     */
    String addStudent(Student newStudent);
}
