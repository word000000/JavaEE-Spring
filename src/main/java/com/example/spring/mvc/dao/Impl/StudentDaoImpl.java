package com.example.spring.mvc.dao.Impl;

import com.example.spring.mvc.pojo.Student;
import com.example.spring.mvc.dao.StudentDao;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 22:15 2020/3/7
 * @Description:
 * @Modifyed_By:
 */

@Component
@Scope("singleton")
@ComponentScan("com.example.spring.mvc.*")
public class StudentDaoImpl implements StudentDao {

    @Autowired
    HikariDataSource hikariDataSource;

    @Override
    public  List<Student> selectAllStudent(){
        String sqlString = "select * from student ";
        List<Student>list=new ArrayList<>();
        try (Connection connection = hikariDataSource.getConnection()) {
            //通过连接获取statement
            try (Statement statement = connection.createStatement()) {
                //statement （增、删、改、查）
                try (ResultSet resultSet = statement.executeQuery(sqlString)) {
                    //获取执行结果
                    while (resultSet.next()) {
                        Student student = new Student();
                        student.setStudentId(resultSet.getLong("student_id"));
                        student.setStudentName(resultSet.getString("student_name"));
                        list.add(student);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public  boolean addStudent(Student newStudent){

        List<Student>list=new ArrayList<>();
        boolean isSuccess = true;
        try (Connection connection = hikariDataSource.getConnection()) {
            //通过连接获取statement
            //Preparestatement （增、删、改、查）
            String sqlString = "insert into student(student_id,student_name) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setLong(1,newStudent.getStudentId());
            ps.setString(2,newStudent.getStudentName());
            //成功返回false 失败返回true
            isSuccess = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //为了方便后面操作 返回相反的结果
        return !isSuccess;
    }
}
