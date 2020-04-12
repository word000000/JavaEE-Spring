package com.example.spring.mvc.bean;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @Author:GQM
 * @Date:created in 18:03 2020/3/7
 * @Description:
 * @Modifyed_By:
 */

@Configuration
@Scope("prototype")
public class StudentHomework {
    private Long id;
    private Long studentId;
    private Long homeworkId;
    private String homeworkTitle;
    private String homeworkContent;
    private Timestamp creatTime;
    private Timestamp updateTime;


    public StudentHomework(){}

    public StudentHomework(Long id, Long studentId, Long homeworkId, String homeworkTitle, String homeworkContent, Timestamp creatTime, Timestamp updateTime) {
        this.id = id;
        this.studentId = studentId;
        this.homeworkId = homeworkId;
        this.homeworkTitle = homeworkTitle;
        this.homeworkContent = homeworkContent;
        this.creatTime = creatTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }

    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
