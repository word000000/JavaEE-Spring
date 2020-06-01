package com.example.demo.db.mapper;

import com.example.demo.db.model.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.rmi.StubNotFoundException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 20:57 2020/5/27
 * @Description:
 * @Modifyed_By:
 */
public interface StudentMapper {

    @Select("SELECT * from student")
    @Results(id="student",value = {
        @Result(property = "studentId", column = "student_id"),
        @Result(property = "studentName",column = "student_name")
    })
    List<Student> selectAllStudent();


    @Insert("INSERT INTO student(student_id,student_name) VALUES (#{studentId},#{studentName})")
    int addStudent(Student student);


}
