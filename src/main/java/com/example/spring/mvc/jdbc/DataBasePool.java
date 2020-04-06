package com.example.spring.mvc.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author:GQM
 * @Date:created in 21:03 2020/3/22
 * @Description:
 * @Modifyed_By:
 */
public class DataBasePool {

    private static HikariDataSource hikariDataSource;


    public static Connection getHikariDataSource() throws SQLException {
        if(null != hikariDataSource){
            return hikariDataSource.getConnection();
        }

        synchronized (DataBasePool.class){
//            String url="jdbc:mysql://127.0.0.1:3306/javaee?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
//            String drivername = "com.mysql.cj.jdbc.Driver";
//            HikariConfig hikariConfig = new HikariConfig();
//            hikariConfig.setUsername("root");
//            hikariConfig.setPassword("030016");
//            hikariConfig.setDriverClassName(drivername);
//            hikariConfig.setJdbcUrl(url);
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            hikariDataSource = context.getBean("dataSourceHikari",HikariDataSource.class);
            return hikariDataSource.getConnection();
        }
    }

    public static void main(String[] args) throws SQLException {
        int i = 3;
        while (i-->=0){
            getHikariDataSource();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
