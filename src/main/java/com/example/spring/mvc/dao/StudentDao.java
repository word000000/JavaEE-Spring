package com.example.spring.mvc.dao;

import com.example.spring.mvc.pojo.Student;

import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 11:16 2020/4/21
 * @Description:
 * @Modifyed_By:
 */
public interface StudentDao {

    /**
     * 查询学生列表
     * @return
     */
    List<Student> selectAllStudent();

    /**
     * 添加学生
     * @param newStudent
     * @return
     */
    boolean addStudent(Student newStudent);
}
