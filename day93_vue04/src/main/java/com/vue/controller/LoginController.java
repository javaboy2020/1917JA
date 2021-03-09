package com.vue.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.vue.pojo.User;
import com.vue.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping("dologin")
    @ResponseBody
    public Object doLogin(HttpServletRequest request) throws IOException {
        User user=JSON.parseObject(request.getInputStream(), User.class);
        System.out.println("userCode:"+user.getUserCode());
        System.out.println("userPassword:"+user.getUserPassword());

        User user1= userService.doLogin(user.getUserCode(),user.getUserPassword());
        boolean flag=false;
        Map<String,Object> map = new HashMap<String,Object>();
        if (user1!=null){
            flag=true;
            request.getSession().setAttribute("user",user1);
            map.put("user",user1);
        }else{
            map.put("message","用户名或密码错误!");
        }
        map.put("flag",flag);
        return JSONArray.toJSON(map);
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        System.out.println("注销");
        request.getSession().invalidate();
        return "redirect:/login.html";
    }
}
