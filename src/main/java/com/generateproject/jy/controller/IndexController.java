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
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @ResponseBody
    ModelAndView getTmp(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new ModelAndView("redirect:/html/index2.html");
        return mv;
    }
}
