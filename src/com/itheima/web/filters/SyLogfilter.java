package com.itheima.web.filters;

import com.itheima.domain.system.SysLog;
import com.itheima.domain.system.User;
import com.itheima.service.system.SylogService;
import com.itheima.service.system.impl.SylogServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter({"/system/company","/store/*","/system/dept","/system/module","/system/role","/system/user"})
public class SyLogfilter implements Filter {
    SylogService service=new SylogServiceImpl();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      String operation=servletRequest.getParameter("operation");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String url= String.valueOf(req.getRequestURL());
        User user =(User) req.getSession().getAttribute("user");
      if(!operation.equals(null)){
          SysLog sysLog=new SysLog();
          sysLog.setAction(url);//网页地址
          sysLog.setMethod(operation);
          sysLog.setIp("0:0:0:0:0:0:0:1");
          sysLog.setUsername(user.getUsername());
          try {
              service.save(sysLog);
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
          System.out.println(sysLog);
      }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
