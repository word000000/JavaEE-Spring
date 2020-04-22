package com.example.spring.mvc.service;

import com.example.spring.mvc.pojo.StudentHomework;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 15:23 2020/4/21
 * @Description:
 * @Modifyed_By:
 */
public interface StudentHomeworkService {


    /**
     * 学生提交作业
     * @param nsh
     * @return
     */
    String addStudentHomework(StudentHomework nsh) throws Exception;


    /**
     * 查询所有学生提交的作业记录
     * @return
     */
    List<StudentHomework> selectAllStudentHomework() throws Exception;

}
