package cn.lonecloud.student.service;

import cn.lonecloud.student.pojo.User;

/**
 * @author lonecloud
 * @version v1.0
 * @date 下午10:26 2017/10/26
 */
public interface IUserService {

    User login(String username, String password);

    User register(User user);
}
