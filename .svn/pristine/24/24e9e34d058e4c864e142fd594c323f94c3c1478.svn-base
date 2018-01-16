package com.boc.common.controller;

import com.boc.common.web.springmvc.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandelController extends BaseController{
    private static final String ERROR_PATH = "/error/404.ak";

    @RequestMapping(value=ERROR_PATH)
    public String handleError(){
        return "error/404";
    }

    @RequestMapping(value="/error/500.ak")
    public String handle500Error(){
        return "error/500";
    }
}
