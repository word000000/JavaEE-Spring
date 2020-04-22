package com.example.spring.mvc.service;

import com.example.spring.mvc.pojo.TeacherHomework;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 15:24 2020/4/21
 * @Description:
 * @Modifyed_By:
 */
public interface TeacherHomeworkService {
    /**
     * 教师发布一个作业
     * @param teacherHomework
     * @return
     */
    String createHomework(TeacherHomework teacherHomework) throws Exception;

    /**
     * 获取所有布置过的作业
     * @return
     */
    List<TeacherHomework> selectAllTeacherHomework() throws Exception;

}
