package com.example.spring.mvc.pojo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;

/**
 * @Author:GQM
 * @Date:created in 23:05 2020/3/7
 * @Description:
 * @Modifyed_By:
 */
@Entity
@Table(name = "teacher_homework")
@Configuration
public class TeacherHomework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "homework_id")
    private long homeworkId;

    @Column(name = "homework_title")
    private String homeworkTitle;


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
