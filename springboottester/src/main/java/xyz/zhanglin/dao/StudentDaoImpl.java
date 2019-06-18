package xyz.zhanglin.dao;


import org.springframework.stereotype.Component;
import xyz.zhanglin.entity.Student;

import java.util.List;

@Component("studentDao")
public class StudentDaoImpl implements StudentDao{
    @Override
    public List<Student> findAll() {

        return null;
    }
}
