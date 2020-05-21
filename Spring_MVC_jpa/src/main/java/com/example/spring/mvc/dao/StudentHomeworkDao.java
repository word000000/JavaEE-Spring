package com.example.spring.mvc.dao;

import com.example.spring.mvc.pojo.StudentHomework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 11:16 2020/4/21
 * @Description:
 * @Modifyed_By:
 */
@Repository
public interface StudentHomeworkDao extends JpaRepository<StudentHomework,Long> {
    /**
     * 学生提交作业
     * @param
     * @param id
     * @return 返回结果
     * @throws SQLException
     */
    @Modifying
    @Transactional
    @Query(value = "insert into student_homework(id,student_id,homework_id,homework_title,homework_content,create_time)values(:id,:sid,:hid,:title,:content,:time)",nativeQuery=true )
    public int addStudentHomework (@Param("id")long id, @Param("sid")long studentId, @Param("hid")long homeworkId, @Param("title")String homeworkTitle, @Param("content")String homeworkContent, @Param("time")Timestamp createTime);

    /**
     * 查询所有学生提交的作业记录
     * @return 返回作业提交记录
     * @throws SQLException
     */
    @Query(value = "select * from student_homework",nativeQuery=true)
    public List<StudentHomework> findAllBy();
}
