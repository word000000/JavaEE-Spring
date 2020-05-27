package com.example.demo.db.mapper;

import com.example.demo.db.model.TeacherHomework;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 20:58 2020/5/27
 * @Description:
 * @Modifyed_By:
 */
public interface TeacherHomeworkMapper {
    @Insert("INSERT INTO teacher_homework(homework_id,homework_title) VALUES (#{homeworkId},#{homeworkTitle})")
    int addTeacherHomework(TeacherHomework teacherHomework);

    @Select("SELECt * from teacher_homework")
    @Results(id = "teacherhomework", value={
            @Result(property = "homeworkId",column = "homework_id",javaType = long.class),
            @Result(property =  "homeworkTitle",column = "homework_title",javaType=String.class)
    })
    List<TeacherHomework> selectAllTeacherHomework();
}
