package com.itheima.web.controller.system;

import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.Company;
import com.itheima.domain.system.Dept;
import com.itheima.service.system.DeptService;
import com.itheima.service.system.DeptService;
import com.itheima.service.system.impl.CompanyServiceImpl;
import com.itheima.service.system.impl.DeptServiceImpl;
import com.itheima.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/system/dept")
public class DeptServlet extends  BaseServlet{
    private DeptService deptService=new DeptServiceImpl();

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String currpage=req.getParameter("currPage");
        int Currpage=1;
        int size=5;//默认每条页数
        if(StringUtils.isNoneEmpty(currpage)){
            Currpage=Integer.parseInt(currpage);
        }
        PageBean pageBean=deptService.findPages(Currpage,size);
        req.setAttribute("page",pageBean);
        req.getRequestDispatcher("/WEB-INF/pages/system/dept/list.jsp").forward(req,resp);
    }
    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("save");
        Dept dept= BeanUtil.fillBean(req,Dept.class);
        deptService.save(dept);
        list(req,resp);
    }
    protected void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");
        System.out.println("toedit");
        List<Dept> depts= deptService.findAll();
        request.setAttribute("deptList",depts);
        Dept dept = deptService.findByID(id);
        request.setAttribute("dept", dept);
        request.getRequestDispatcher("/WEB-INF/pages/system/dept/update.jsp").forward(request,response);
    }
    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Dept dept=BeanUtil.fillBean(req,Dept.class);
        System.out.println("edit");
        deptService.update(dept);
        list(req,resp);
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("delete");
        String id = req.getParameter("id");
        deptService.delete(id);
        list(req,resp);
    }
    protected  void toAdd(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        System.out.println("Add");
        List<Dept> depts= deptService.findAll();
        request.setAttribute("deptList",depts);
        request.getRequestDispatcher("/WEB-INF/pages/system/dept/add.jsp").forward(request,response);

    }
}
