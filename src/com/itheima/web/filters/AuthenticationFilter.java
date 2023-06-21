package com.itheima.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter({"/system/*","/store/*","/pages/*"}) //配置拦截路径
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse
            response, FilterChain chain) throws ServletException, IOException
    {
//1 从session获取用户信息
        HttpServletRequest req = (HttpServletRequest) request;
        Object user = req.getSession().getAttribute("user");
//2 如果没有获取到就表示还没有登录，保存错误信息，跳转到登录页面去。
        if(user==null){
            request.setAttribute("error","您还没有登录，无权访问!");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
//3 否则放行
        chain.doFilter(request, response);
    }
    public void init(FilterConfig config) throws ServletException
    {
    }
    public void destroy() {
    }
}
