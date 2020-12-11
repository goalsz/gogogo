package xyz.zhanglin.dao;


import java.util.List;

import org.springframework.stereotype.Component;

import xyz.zhanglin.entity.Student;

@Component("studentDao")
public class StudentDaoImpl implements StudentDao{
    @Override
    public List<Student> findAll() {

        return null;
    }
}
