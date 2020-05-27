package com.example.spring.mvc.service;

import com.example.spring.mvc.bean.TeacherHomework;

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
    String createHomework(TeacherHomework teacherHomework);

    /**
     * 获取所有布置过的作业
     * @return
     */
    List<TeacherHomework> selectAllTeacherHomework();

}
