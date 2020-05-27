package com.example.demo.db.mapper;

import com.example.demo.db.model.StudentHomework;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 19:16 2020/5/27
 * @Description:
 * @Modifyed_By:
 */
public interface StudentHomeworkMapper {

    @Select("SELECT * FROM student_homework")
    @Results(id = "studentHomework",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "studentId",column = "student_id"),
            @Result(property = "homeworkId",column = "homework_id"),
            @Result(property = "homeworkTitle",column = "homework_title"),
            @Result(property = "homeworkContent",column = "homework_content"),
            @Result(property = "createTime",column = "create_time",javaType = Timestamp.class),
            @Result(property = "updateTime",column = "update_time",javaType = Timestamp.class)
    })
    List<StudentHomework> selectAllStudentHomework();

    @Insert("INSERTE INTO student_homework(id,student_id,homework_id,homework_title,homework_content,create_time) " +
            "VALUES(#{id},#{studentId},#{homeworkId},#{homeworkTitle},#{homeworkContent},#{createTime})")
    int addnStudentHomework(StudentHomework studentHomework);

}
