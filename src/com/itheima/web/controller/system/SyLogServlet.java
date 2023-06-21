package com.itheima.web.controller.system;

import com.itheima.domain.common.PageBean;
import com.itheima.service.system.SylogService;
import com.itheima.service.system.impl.SylogServiceImpl;
import com.itheima.web.controller.system.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/system/sysLog")
public class SyLogServlet extends BaseServlet {
    SylogService Service=new SylogServiceImpl();
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String currpage=req.getParameter("currPage");
        int Currpage=1;
        int size=5;//默认每条页数
        if(StringUtils.isNoneEmpty(currpage)){
            Currpage=Integer.parseInt(currpage);
        }
        System.out.println(req.getContextPath());
        PageBean pageBean=Service.findPages(Currpage,size);
        req.setAttribute("page",pageBean);
        req.getRequestDispatcher("/WEB-INF/pages/system/sysLog/list.jsp").forward(req,resp);
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("delete");
        String id = req.getParameter("id");
        Service.delete(id);
        list(req,resp);
    }
}
