package com.mybatis.controller;


import com.mybatis.pojo.User;
import com.mybatis.util.sqlsesionUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;


/**
 * /**
 *
 * @ClassName test
 * @Description TODO
 * @Author 陈明伟
 * @Date 2022/7/21 10:23
 * @Version 1.0
 */
@Controller
public class UserController {

    @RequestMapping("/")
    public String test() {

        return "index";

    }

    @RequestMapping("/hello")
    public String index2(Model model) throws IOException {
        //根据id获取对象
        User user = sqlsesionUtil.getUserById(1);
        System.out.println("这是我查询的一个sql对象:" + user);
        model.addAttribute("msg", user);
        return "index";
    }


}
