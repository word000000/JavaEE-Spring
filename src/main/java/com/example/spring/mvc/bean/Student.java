package com.example.spring.mvc.bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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

    public Student(){}

    public Student(Long studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

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
