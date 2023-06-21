package com.itheima.web.controller.system;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    protected  void service(HttpServletRequest request, HttpServletResponse response){
        String operation =request.getParameter("operation");
        Class<? extends BaseServlet>clazz =this.getClass();
        try {

            Method method=clazz.getDeclaredMethod(operation,HttpServletRequest.class,HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this,request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
