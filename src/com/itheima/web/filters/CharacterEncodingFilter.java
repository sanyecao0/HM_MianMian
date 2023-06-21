package com.itheima.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //1.设置请求参数的字符集
        req.setCharacterEncoding("utf-8");
        //2.设置响应对象输出响应正文时的字符集
        resp.setContentType("text/html;charset=UTF-8");
        //3.放行
        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
