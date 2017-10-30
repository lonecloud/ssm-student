package cn.lonecloud.student.dao;

import cn.lonecloud.student.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> selectByPage(@Param("offset") int offset, @Param("limit") int limit);

    int selectCount();

    List<Student> seletAll();
}