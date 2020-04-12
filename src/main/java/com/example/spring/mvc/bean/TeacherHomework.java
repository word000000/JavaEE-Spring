package com.example.spring.mvc.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author:GQM
 * @Date:created in 23:05 2020/3/7
 * @Description:
 * @Modifyed_By:
 */
@Configuration
public class TeacherHomework {
    private long homeworkId;
    private String homeworkTitle;

    public TeacherHomework(long homeworkId, String homeworkTitle) {
        this.homeworkId = homeworkId;
        this.homeworkTitle = homeworkTitle;
    }

    public TeacherHomework(){ }

    public long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
    }
}
