package com.example.spring.mvc.bean;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

/**
 * @Author:GQM
 * @Date:created in 21:32 2020/3/7
 * @Description:
 * @Modifyed_By:
 */

@Entity
@Table(name = "student")
@Configuration
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="student_id")
    private Long studentId;

    @Column(name = "student_name")
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
