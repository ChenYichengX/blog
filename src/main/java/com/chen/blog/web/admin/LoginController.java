package com.chen.blog.web.admin;

import com.chen.blog.aspect.SystemLog;
import com.chen.blog.entity.User;
import com.chen.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Name;
import javax.servlet.http.HttpSession;

/**
 * @author 陈奕成
 * @create 2020 05 10 13:54
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @SystemLog(serviceName = "blog服务", module = "后台管理", action = "登录接口")
    @PostMapping("/login")
    public String login(@RequestParam(name = "userName") String userName,
                        @RequestParam(name = "passWord") String passWord,
                        HttpSession session,
                        RedirectAttributes redirectAttributes){
        User user = userService.checkUser(userName,passWord);
        if(user != null){
            user.setPassWord(null);
            session.setAttribute("user",user);
            return "admin/index";
        } else {
            redirectAttributes.addFlashAttribute("message","用户名和密码不正确");
            return "redirect:/admin";
        }
    }

    @SystemLog(serviceName = "blog服务", module = "后台管理", action = "退出登录")
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
