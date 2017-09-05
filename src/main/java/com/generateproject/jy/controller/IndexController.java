package com.generateproject.jy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @file:IndexController.java
 * @author: 刘海洋
 * @date: 2017-09-05-9:15
 * @version: 1.0
 * @projectName: 微信银行
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @ResponseBody
    ModelAndView getTmp(HttpServletRequest request, HttpServletResponse response){
        System.out.print("调用成功");
        ModelAndView mv = new ModelAndView("redirect:/templates/error/404.html");
        return mv;
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    String geT2mp(HttpServletRequest request, HttpServletResponse response){
        System.out.print("调用成功");
        ModelAndView mv = new ModelAndView("/error/404.jsp");
        return "/error/405.jsp";
    }
}
