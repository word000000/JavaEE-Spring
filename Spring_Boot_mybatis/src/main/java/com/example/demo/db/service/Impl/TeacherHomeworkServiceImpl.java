package com.example.demo.db.service.Impl;

import com.example.demo.db.mapper.TeacherHomeworkMapper;
import com.example.demo.db.model.TeacherHomework;
import com.example.demo.db.service.TeacherHomeworkService;
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


@ComponentScan("com.example.demo.*")
@Component
public class TeacherHomeworkServiceImpl implements TeacherHomeworkService {
    @Autowired
    TeacherHomeworkMapper teacherHomeworkMapper;


   @Override
   public String createHomework(TeacherHomework nth){
       String response = "";
       List<TeacherHomework> thList = null;
       thList = teacherHomeworkMapper.selectAllTeacherHomework();
       for(TeacherHomework th:thList){
           //使用equals方法
           if(nth.getHomeworkId()==th.getHomeworkId()){
               return "该id已被使用,3s后跳转";
           }
       }
       if(nth.getHomeworkTitle().equals("")){
           return "作业名不为空，请检查后再添加,3s后跳转";
       }else{
           if(teacherHomeworkMapper.addTeacherHomework(nth)>=0){
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
       list = teacherHomeworkMapper.selectAllTeacherHomework();
       return list;

   }
}
