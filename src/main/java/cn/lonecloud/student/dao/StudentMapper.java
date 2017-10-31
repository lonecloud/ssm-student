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

    /**
     * 按照页面数据进行筛选
     * @param offset
     * @param limit
     * @return
     */
    List<Student> selectByPage(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 统计用户的总数
     * @return
     */
    int selectCount();

    /**
     * 筛选所有的用户
     * @return
     */
    List<Student> seletAll();
}