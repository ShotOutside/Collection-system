package com.rj.bd.base;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @desc   使用@ExceptionHandler注解实现异常处理的基类
 * @author WYH
 * @time   2019-05-09
 */
public class BaseController {
	 /** 基于@ExceptionHandler异常处理 */
    @ExceptionHandler
    public ModelAndView exp(HttpServletRequest request, Exception exmsg) {
    	  //声明一个map用来存放异常信息的
    			Map<String, Object> model = new HashMap<String, Object>(); 
    		    model.put("exmsg", exmsg); 	
    		    //多路决策判断当前异常是哪一种异常，instanceof是java一个关键字，在此的意思为实例化加载
    		    if(exmsg instanceof SQLException)
    		      { 
    		    	System.out.println("当前异常为sql语句异常");
    		        return new ModelAndView("error-sql", model); 
    		      }
    		    else if(exmsg instanceof NullPointerException)
    		    { 
    		    	System.out.println("当前异常为空指针异常");
    		        return new ModelAndView("error2", model); 
    		      } 
    		    else 
    		    { 
    		    	System.out.println("当前异常为其他异常");
    		        return new ModelAndView("error", model); 
    		      }
    }
}
