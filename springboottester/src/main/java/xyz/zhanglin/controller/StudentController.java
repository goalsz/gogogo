package xyz.zhanglin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.zhanglin.dao.StudentDao;
import xyz.zhanglin.entity.Student;

import java.util.List;

@RequestMapping("student")
@RestController
public class StudentController {

    @Autowired
    private StudentDao studentDao;


    @RequestMapping("list")
    public List<Student> list(){

        return studentDao.findAll();
    }



}
