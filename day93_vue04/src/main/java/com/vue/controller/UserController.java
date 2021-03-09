package com.vue.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.vue.pojo.User;
import com.vue.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("useradd")
    @ResponseBody
    public Object userAddSave(HttpServletRequest request) throws IOException {
        //只针对get有效,对post无效
        System.out.println("userCode:"+request.getParameter("userCode"));
        User user= new User();
        user= JSON.parseObject(request.getInputStream(),User.class);
      /*  user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userPassword);*/
        System.out.println("user:"+user);
        boolean flag=userService.userAdd(user);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("flag",flag);
        if (!flag){
            map.put("message","新增失败");
        }
        Object objJson=JSONArray.toJSON(map);
        System.out.println("objJson:"+objJson);
        return objJson;
    }


    @RequestMapping("{id}")
    @ResponseBody
    public Object getUserById(@PathVariable Integer id){
        System.out.println("id:"+id);
        User user= userService.getUserById(id);
        boolean flag=false;
        Map<String,Object> map=new HashMap<String,Object>();
        if (user !=null){
            flag=true;
            map.put("user",user);
        }else{
            map.put("message","根据id失败!");
        }
        map.put("flag",flag);
        Object jsonObj=JSONArray.toJSON(map);
        System.out.println("jsonObj:"+jsonObj);
        return jsonObj;
    }

    @RequestMapping("index")
    public String gotoUserlist(){
        return "redirect:/userlist.html";
    }

    @RequestMapping(value = "userlist" ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object userList(Model model, HttpServletResponse response){
        List<User> userList=userService.userList();
        Map map=new HashMap();
        map.put("userList",userList);
        Object jsonStr=  JSONArray.toJSON(map);
        System.out.println("jsonStr:"+jsonStr);
        return jsonStr;
    }


}
