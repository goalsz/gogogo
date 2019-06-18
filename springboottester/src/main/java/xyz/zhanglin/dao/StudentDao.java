package xyz.zhanglin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.zhanglin.entity.Student;

import java.util.List;

@Mapper
public interface StudentDao {

    @Select("select * from student")
    public List<Student> findAll();

}
