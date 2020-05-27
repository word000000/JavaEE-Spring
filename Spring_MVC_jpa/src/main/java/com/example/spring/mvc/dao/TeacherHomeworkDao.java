package com.example.spring.mvc.dao;

import com.example.spring.mvc.bean.TeacherHomework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 11:17 2020/4/21
 * @Description:
 * @Modifyed_By:
 */

@Repository
public interface TeacherHomeworkDao extends JpaRepository<TeacherHomework,Long> {

    /**
     * 教师发布作业
     * @param
     * @return
     * @throws SQLException
     */
    @Modifying
    @Transactional
    @Query(value = "insert into teacher_homework(homeworkId,homeworkTitle) values (:id,:title)",nativeQuery = true)
    public int addTeacherHomework(@Param("id") Long id, @Param("title") String homeworkTitle);

    /**
     * 查询所有教师作业的列表
     * @return
     * @throws SQLException
     */
    @Query(value = "select * from teacher_homework",nativeQuery = true)
    public List<TeacherHomework> findAllBy();

}
