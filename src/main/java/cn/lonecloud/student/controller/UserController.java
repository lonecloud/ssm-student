package cn.lonecloud.student.controller;

import cn.lonecloud.student.common.CommonController;
import cn.lonecloud.student.cts.Constants;
import cn.lonecloud.student.exception.BusinessException;
import cn.lonecloud.student.pojo.User;
import cn.lonecloud.student.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lonecloud
 * @version v1.0
 * @date 下午10:19 2017/10/26
 */
@Controller
@RequestMapping("/user")
public class UserController extends CommonController {



    @Autowired
    IUserService userService;

    /**
     * 登录
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute(Constants.CURRENT_USER,"");
        return "redirect:login";
    }

    /**
     * 登录认证
     * @param username
     * @param password
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("doLogin")
    public String doLogin(String username, String password, HttpSession session, RedirectAttributes attributes){
        try {
            User user=userService.login(username,password);
            session.setAttribute(Constants.CURRENT_USER,user);
            return "redirect:/student/list";
        }catch (Exception e){
            if (e instanceof BusinessException){
                logger.debug(e.getMessage());
                attributes.addAttribute("msg",e.getMessage());
            }
            logger.error(e.getStackTrace().toString(),e.getMessage());
        }
        return "redirect:login";
    }

    /**
     * 注册
     * @return
     */
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 注册认证
     * @param user
     * @param attributes
     * @return
     */
    @PostMapping("/doRegister")
    public String doRegister(User user,RedirectAttributes attributes){
        try {
            User registerUser=userService.register(user);
        }catch (Exception e){
            if (e instanceof BusinessException){
                attributes.addAttribute("msg",e.getMessage());
                return "redirect:register";
            }
        }
        return "redirect:login";
    }
}
