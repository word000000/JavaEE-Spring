package com.example.spring.mvc.service.Impl;

import com.example.spring.mvc.dao.TeacherHomeworkDao;
import com.example.spring.mvc.bean.TeacherHomework;
import com.example.spring.mvc.service.TeacherHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:GQM
 * @Date:created in 15:26 2020/4/21
 * @Description:
 * @Modifyed_By:
 */

@EnableAspectJAutoProxy
@ComponentScan("com.example.spring.mvc.*")
@Component
public class TeacherHomeworkServiceImpl implements TeacherHomeworkService {
    @Autowired
    TeacherHomeworkDao teacherHomeworkDao;


   @Override
   public String createHomework(TeacherHomework nth){
       String response = "";
       List<TeacherHomework> thList = null;
       thList = teacherHomeworkDao.findAllBy();
       for(TeacherHomework th:thList){
           //使用equals方法
           if(nth.getHomeworkId()==th.getHomeworkId()){
               return "该id已被使用,3s后跳转";
           }
       }
       if(nth.getHomeworkTitle().equals("")){
           return "作业名不为空，请检查后再添加,3s后跳转";
       }else{
           if(teacherHomeworkDao.addTeacherHomework(nth.getHomeworkId(),nth.getHomeworkTitle())>=0){
               response ="添加成功,3s后跳转";
           }else {
               response = "添加失败，请检查后再添加,3s后跳转";
           }
       }
       //延时跳转
        return response;
   }

   @Override
    public List<TeacherHomework> selectAllTeacherHomework(){

       List<TeacherHomework> list= new ArrayList<TeacherHomework>();
       list = teacherHomeworkDao.findAllBy();
       return list;

   }
}
