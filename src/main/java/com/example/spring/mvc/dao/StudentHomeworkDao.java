package com.example.spring.mvc.dao;

import com.example.spring.mvc.pojo.StudentHomework;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 11:16 2020/4/21
 * @Description:
 * @Modifyed_By:
 */
public interface StudentHomeworkDao {
    /**
     * 学生提交作业
     * @param nsh
     * @param id
     * @return 返回结果
     * @throws SQLException
     */
    String addStudentHomework(StudentHomework nsh) throws SQLException;

    /**
     * 查询所有学生提交的作业记录
     * @return 返回作业提交记录
     * @throws SQLException
     */
    List<StudentHomework> selectAllStudentHomework() throws SQLException;
}
