package com.example.spring.mvc.dao;

import com.example.spring.mvc.pojo.TeacherHomework;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 11:17 2020/4/21
 * @Description:
 * @Modifyed_By:
 */
public interface TeacherHomeworkDao {

    /**
     * 教师发布作业
     * @param nth
     * @return
     * @throws SQLException
     */
    boolean addHomework(TeacherHomework nth) throws SQLException;

    /**
     * 查询所有教师作业的列表
     * @return
     * @throws SQLException
     */
    List<TeacherHomework> selectAllTeacherHomework() throws SQLException;

}
