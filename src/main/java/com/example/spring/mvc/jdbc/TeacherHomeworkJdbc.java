package com.example.spring.mvc.jdbc;
import com.example.spring.mvc.bean.TeacherHomework;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
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
public class TeacherHomeworkJdbc {

    private static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    //获取所有教师布置的作业
    public static List<TeacherHomework> selectAllTeacherHomework() throws ClassNotFoundException {
        String sqlString = "select * from teacher_homework ";
        List<TeacherHomework>list=new ArrayList<>();
        try (Connection connection = context.getBean("dataSourceHikari", HikariDataSource.class).getConnection()) {
            //通过连接获取statement
            try (Statement statement = connection.createStatement()) {
                //statement （增、删、改、查）
                try (ResultSet resultSet = statement.executeQuery(sqlString)) {
                    //获取执行结果
                    while (resultSet.next()) {
                        TeacherHomework th = (TeacherHomework) context.getBean("teacherHomework");
                        th.setHomeworkId(resultSet.getLong("homework_id"));
                        th.setHomeworkTitle(resultSet.getString("homework_title"));
                        list.add(th);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static boolean addHomework(TeacherHomework nth) throws ClassNotFoundException {

        boolean isSuccess = true;
        try (Connection connection = context.getBean("dataSourceHikari", HikariDataSource.class).getConnection()) {
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





    public static void main(String[] args) throws ClassNotFoundException {


        List<TeacherHomework> list1 = selectAllTeacherHomework();
        for(TeacherHomework th:list1){
            System.out.println(th.getHomeworkId());
        }
    }
}
