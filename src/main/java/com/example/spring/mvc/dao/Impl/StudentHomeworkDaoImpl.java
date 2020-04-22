package com.example.spring.mvc.dao.Impl;

import com.example.spring.mvc.pojo.Student;
import com.example.spring.mvc.pojo.StudentHomework;
import com.example.spring.mvc.pojo.TeacherHomework;
import com.example.spring.mvc.dao.StudentDao;
import com.example.spring.mvc.dao.StudentHomeworkDao;
import com.example.spring.mvc.dao.TeacherHomeworkDao;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 23:41 2020/3/22
 * @Description:
 * @Modifyed_By:
 */
@ComponentScan("com.example.spring.mvc.*")
@Scope("singleton")
@Configuration
public class StudentHomeworkDaoImpl implements StudentHomeworkDao {

    @Autowired
    HikariDataSource hikariDataSource;
    @Autowired
    StudentHomeworkDao studentHomeworkDao;
    @Autowired
    TeacherHomeworkDao teacherHomeworkDao;
    @Autowired
    StudentDao studentDao;

    public StudentHomeworkDaoImpl(){ }


    @Override
    public String addStudentHomework(StudentHomework nsh) throws SQLException{
        String response = "提交成功";
//        try (Connection connection = hikariDataSource.getConnection()) {
            //通过连接获取statement
        Connection connection = hikariDataSource.getConnection();
        String sqlString = "insert into student_homework(id,student_id,homework_id,homework_title,homework_content,create_time)values(?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sqlString);
        ps.setLong(1,nsh.getId());
        ps.setLong(2,nsh.getStudentId());
        ps.setLong(3,nsh.getHomeworkId());
        ps.setString(4,nsh.getHomeworkTitle());
        ps.setString(5,nsh.getHomeworkContent());
        ps.setTimestamp(6, nsh.getCreatTime());
        //成功返回false 失败返回true
        if(ps.execute()){
            return "提交失败，请检查后再提交";
        }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        //为了方便后面操作 返回相反的结果
        return response;
    }

    //获取所有学生提交的作业
    @Override
    public List<StudentHomework> selectAllStudentHomework() throws SQLException{
        String sqlString = "select * from student_homework ";
        List<StudentHomework>list=new ArrayList<>();
        Connection connection = hikariDataSource.getConnection();
            //通过连接获取statement
        Statement statement = connection.createStatement();
            //statement （增、删、改、查）
        ResultSet resultSet = statement.executeQuery(sqlString);
            //获取执行结果
        while (resultSet.next()) {
            StudentHomework sh = new StudentHomework();
            sh.setId(resultSet.getLong("id"));
            sh.setStudentId(resultSet.getLong("student_id"));
            sh.setHomeworkId(resultSet.getLong("homework_id"));
            sh.setHomeworkTitle(resultSet.getString("homework_title"));
            sh.setHomeworkContent(resultSet.getString("homework_content"));
            sh.setCreatTime(resultSet.getTimestamp("create_time"));
            sh.setUpdateTime(resultSet.getTimestamp("update_time"));
            list.add(sh);
        }
        return list;
    }

}
