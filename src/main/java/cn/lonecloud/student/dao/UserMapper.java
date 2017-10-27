package cn.lonecloud.student.dao;

import cn.lonecloud.student.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 查询用户是不是存在
     * @param username
     * @param password
     * @return
     */
    User selectUserByNameAndPwd(@Param("username") String username, @Param("password") String password);

    int selectCountByUsername(String username);
}