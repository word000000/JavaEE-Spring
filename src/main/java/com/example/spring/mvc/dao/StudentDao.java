package com.example.spring.mvc.dao;

import com.example.spring.mvc.pojo.Student;

import java.sql.SQLException;
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
     * @throws SQLException
     */
    List<Student> selectAllStudent() throws SQLException;

    /**
     * 添加学生
     * @param newStudent
     * @return
     * @throws SQLException
     */
    boolean addStudent(Student newStudent) throws SQLException;
}
