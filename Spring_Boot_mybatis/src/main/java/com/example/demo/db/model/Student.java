package com.example.demo.db.model;

import org.springframework.context.annotation.Configuration;

/**
 * @Author:GQM
 * @Date:created in 21:32 2020/3/7
 * @Description:
 * @Modifyed_By:
 */


@Configuration
public class Student {


    private Long studentId;
    private String studentName;



    public Student(){ }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
