package com.example.spring.mvc.dao;

import com.example.spring.mvc.pojo.TeacherHomework;

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
     */
    boolean addHomework(TeacherHomework nth);

    /**
     * 查询所有教师作业的列表
     * @return
     */
    List<TeacherHomework> selectAllTeacherHomework();

}
