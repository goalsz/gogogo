package xyz.zhanglin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import xyz.zhanglin.entity.Student;

@Mapper
public interface StudentDao {

    @Select("select * from student")
    public List<Student> findAll();

}
