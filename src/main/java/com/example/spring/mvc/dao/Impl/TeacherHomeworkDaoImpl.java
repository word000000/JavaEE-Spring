package com.example.spring.mvc.dao.Impl;
import com.example.spring.mvc.pojo.TeacherHomework;
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
 * @Date:created in 18:10 2020/3/7
 * @Description:
 * @Modifyed_By:
 */

@ComponentScan("com.example.spring.mvc.*")
@Scope("singleton")
@Configuration
public class TeacherHomeworkDaoImpl implements TeacherHomeworkDao {

    @Autowired
    HikariDataSource hikariDataSource;
    //获取所有教师布置的作业


    public TeacherHomeworkDaoImpl() { }


    @Override
    public  List<TeacherHomework> selectAllTeacherHomework() {
        String sqlString = "select * from teacher_homework ";
        List<TeacherHomework>list=new ArrayList<>();
        try (Connection connection = hikariDataSource.getConnection()) {
            //通过连接获取statement
            try (Statement statement = connection.createStatement()) {
                //statement （增、删、改、查）
                try (ResultSet resultSet = statement.executeQuery(sqlString)) {
                    //获取执行结果
                    while (resultSet.next()) {
                        TeacherHomework teacherHomework = new TeacherHomework();
                        teacherHomework.setHomeworkId(resultSet.getLong("homework_id"));
                        teacherHomework.setHomeworkTitle(resultSet.getString("homework_title"));
                        list.add(teacherHomework);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public  boolean addHomework(TeacherHomework nth) {

        boolean isSuccess = true;
        try (Connection connection = hikariDataSource.getConnection()) {
            //通过连接获取statement

            //Preparestatement （增、删、改、查）
            String sqlString = "insert into teacher_homework(homework_id,homework_title) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setLong(1,nth.getHomeworkId());
            ps.setString(2,nth.getHomeworkTitle());
            //成功返回false 失败返回true
            isSuccess = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //为了方便后面操作 返回相反的结果
        return !isSuccess;
    }

}
