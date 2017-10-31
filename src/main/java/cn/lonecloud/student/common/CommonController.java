package cn.lonecloud.student.common;

import cn.lonecloud.student.cts.Constants;
import cn.lonecloud.student.pojo.User;
import cn.lonecloud.student.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础控制层
 * @author lonecloud
 * @version v1.0
 * @date 上午9:18 2017/10/27
 */
public class CommonController {

    protected Logger logger= LoggerFactory.getLogger(getClass());

    protected User currentUser=null;
    /**
     * 获取user
     * @return
     */
    protected User getCurrentUser(){
        currentUser=(User)RequestUtils.getRequest().getSession().getAttribute(Constants.CURRENT_USER);
        return currentUser;
    }
}
