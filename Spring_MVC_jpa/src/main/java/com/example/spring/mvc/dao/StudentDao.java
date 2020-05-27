package com.example.spring.mvc.dao;

import com.example.spring.mvc.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.sql.SQLException;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 11:16 2020/4/21
 * @Description:
 * @Modifyed_By:
 */

@Repository
public interface StudentDao extends JpaRepository<Student,Long> {

    /**
     * 查询学生列表
     * @return
     * @throws SQLException
     */
    @Query(value = "select * from student", nativeQuery=true)
    List<Student> findAllBy();

    /**
     * 添加学生
     * @param
     * @return
     * @throws SQLException
     */
    @Query(value = "insert into student (student_id,student_name) values(:id,:name)",nativeQuery=true)
    int addStudent(@Param("id")long id,@Param("name")String studentName);
}
