package cn.lonecloud.student.service.impl;

import cn.lonecloud.student.dao.UserMapper;
import cn.lonecloud.student.exception.BusinessException;
import cn.lonecloud.student.pojo.User;
import cn.lonecloud.student.service.IUserService;
import cn.lonecloud.student.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lonecloud
 * @version v1.0
 * @date 下午10:50 2017/10/26
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        checkUser(username,password);
        int count=userMapper.selectCountByUsername(username);
        if (count==0){
            throw new BusinessException("用户名不存在！");
        }
        String md5Pwd = MD5Util.MD5EncodeUtf8(password);
        User user=userMapper.selectUserByNameAndPwd(username,password);
        if (user==null){
            throw new BusinessException("用户名或者密码错误");
        }
        return user;
    }

    @Override
    public User register(User user) {
        checkUser(user.getUsername(),user.getPassword());
        int count=userMapper.selectCountByUsername(user.getUsername());
        if (count!=0){
            throw new BusinessException("用户名已存在！");
        }
        String md5Pwd=MD5Util.MD5EncodeUtf8(user.getPassword());
        int insert = userMapper.insert(user);
        if (insert==0){
            throw  new BusinessException("注册失败！");
        }
        return user;
    }

    private void checkUser(String username, String password) {
        //判断是不是username,password是不是为空
        if (StringUtils.isBlank(username)){
            throw new BusinessException("用户名不能为空！");
        }
        if (StringUtils.isBlank(password)){
            throw new BusinessException("密码不能为空");
        }
    }

}
