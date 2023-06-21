package com.itheima.web.controller.system;
import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.Company;
import com.itheima.service.system.CompanyService;
import com.itheima.service.system.impl.CompanyServiceImpl;
import com.itheima.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/system/company")
public class CompanyServlet extends  BaseServlet{
    private CompanyService companyService=new CompanyServiceImpl();

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
          String currpage=req.getParameter("currPage");
          int Currpage=1;
          int size=5;//默认每条页数
          if(StringUtils.isNoneEmpty(currpage)){
            Currpage=Integer.parseInt(currpage);
          }
        PageBean pageBean=companyService.findPages(Currpage,size);
          req.setAttribute("page",pageBean);
          req.getRequestDispatcher("/WEB-INF/pages/system/company/list.jsp").forward(req,resp);
    }
    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("save");
        Company company= BeanUtil.fillBean(req,Company.class);
        companyService.save(company);
        list(req,resp);
    }
    protected void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");
        System.out.println("toedit");
        Company company = companyService.findByID(id);
        request.setAttribute("company",company);
        request.getRequestDispatcher("/WEB-INF/pages/system/company/update.jsp").forward(request,response);
    }
    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Company company=BeanUtil.fillBean(req, Company.class);
        System.out.println("edit");
        companyService.update(company);
        list(req,resp);
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("delete");
        String id = req.getParameter("id");
        companyService.delete(id);
        list(req,resp);
    }
    protected  void toAdd(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Add");
        request.getRequestDispatcher("/WEB-INF/pages/system/company/add.jsp").forward(request,response);

    }

}
