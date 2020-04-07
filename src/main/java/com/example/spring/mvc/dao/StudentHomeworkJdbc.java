package com.example.spring.mvc.dao;

import com.example.spring.mvc.bean.Student;
import com.example.spring.mvc.bean.StudentHomework;
import com.example.spring.mvc.bean.TeacherHomework;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
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
public class StudentHomeworkJdbc {
    private static  ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static String handHomework(StudentHomework nsh) throws ClassNotFoundException {
//        String url="jdbc:mysql://127.0.0.1:3306/javaee?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
//        String drivername = "com.mysql.cj.jdbc.Driver";
//        Class.forName(drivername);//可以省略
        String respone = "提交成功";

        List<StudentHomework> shlist = StudentHomeworkJdbc.selectAllStudentHomework();
        List<Student> slist = StudentJdbc.selectAllStudent();
        List<TeacherHomework> thlist = TeacherHomeworkJdbc.selectAllTeacherHomework();
        boolean studentExist = false;
        boolean homeworkExist = false;
        //检查学生id

        for(Student s:slist){
            if(s.getStudentId().equals(nsh.getStudentId())){
                studentExist=true;
                break;
            }
        }
        if(!studentExist){
            return  "当前输入的学生id不存在";
        }
        //检查作业是否存在
        for(TeacherHomework th:thlist){
            if(th.getHomeworkId()==nsh.getHomeworkId()){
                homeworkExist=true;
                nsh.setHomeworkTitle(th.getHomeworkTitle());
                break;
            }
        }
        if(!homeworkExist){
            return  "未发现该作业";
        }
        try (Connection connection = context.getBean("dataSourceHikari", HikariDataSource.class).getConnection()) {
            //通过连接获取statement
            String sqlString = "insert into student_homework(id,student_id,homework_id,homework_title,homework_content,create_time)values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setLong(1,shlist.size()+1);
            ps.setLong(2,nsh.getStudentId());
            ps.setLong(3,nsh.getHomeworkId());
            ps.setString(4,nsh.getHomeworkTitle());
            ps.setString(5,nsh.getHomeworkContent());
            ps.setTimestamp(6, nsh.getCreatTime());
            //成功返回false 失败返回true
            if(ps.execute()){
                return "提交失败，请检查后再提交";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //为了方便后面操作 返回相反的结果
        return respone;
    }

    //获取所有学生提交的作业
    public static List<StudentHomework> selectAllStudentHomework() throws ClassNotFoundException {
        String sqlString = "select * from student_homework ";
        List<StudentHomework>list=new ArrayList<>();

        try (Connection connection = context.getBean("dataSourceHikari", HikariDataSource.class).getConnection()) {
            //通过连接获取statement
            try (Statement statement = connection.createStatement()) {
                //statement （增、删、改、查）
                try (ResultSet resultSet = statement.executeQuery(sqlString)) {
                    //获取执行结果
                    while (resultSet.next()) {
                        StudentHomework sh = (StudentHomework) context.getBean("studentHomework");
//                        StudentHomework sh = new StudentHomework();
                        sh.setId(resultSet.getLong("id"));
                        sh.setStudentId(resultSet.getLong("student_id"));
                        sh.setHomeworkId(resultSet.getLong("homework_id"));
                        sh.setHomeworkTitle(resultSet.getString("homework_title"));
                        sh.setHomeworkContent(resultSet.getString("homework_content"));
                        sh.setCreatTime(resultSet.getTimestamp("create_time"));
                        sh.setUpdateTime(resultSet.getTimestamp("update_time"));
                        list.add(sh);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static void main(String[] args) throws ClassNotFoundException {

        List<StudentHomework> list= selectAllStudentHomework();
        for(StudentHomework th:list){
            System.out.println(th.getHomeworkId());
        }
    }
}
