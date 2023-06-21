package com.itheima.web.controller.store;

import com.itheima.domain.common.PageBean;
import com.itheima.domain.store.Catalog;
import com.itheima.domain.store.Course;
import com.itheima.service.store.CatalogService;
import com.itheima.service.store.CourseService;
import com.itheima.service.store.Impl.CatalogServiceImpl;
import com.itheima.service.store.Impl.CourseServiceImpl;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.system.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/store/catalog")
public class CataLogServlet extends BaseServlet {
    private CatalogService catalogService =new CatalogServiceImpl();
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String currpage=req.getParameter("currPage");
        int Currpage=1;
        int size=5;//默认每条页数
        if(StringUtils.isNoneEmpty(currpage)){
            Currpage=Integer.parseInt(currpage);
        }
        PageBean pageBean= catalogService.findPages(Currpage,size);
        req.setAttribute("page",pageBean);
        req.getRequestDispatcher("/WEB-INF/pages/store/catalog/list.jsp").forward(req,resp);
    }
    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("save");
        Catalog catalog= BeanUtil.fillBean(req,Catalog.class);
        catalogService.save(catalog);
        list(req,resp);
    }
    protected void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");
        System.out.println("toedit");
        Catalog catalog = catalogService.findByID(id);
        CourseService courseService=new CourseServiceImpl();
        List<Course> courseList=courseService.findAll();
        request.setAttribute("courseList",courseList);
        request.setAttribute("catalog",catalog);
        request.getRequestDispatcher("/WEB-INF/pages/store/catalog/update.jsp").forward(request,response);
    }
    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Catalog catalog=BeanUtil.fillBean(req, Catalog.class);
        System.out.println("edit");
        catalogService.update(catalog);
        list(req,resp);
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("delete");
        String id = req.getParameter("id");
        catalogService.delete(id);
        list(req,resp);
    }
    protected  void toAdd(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        CourseService courseService=new CourseServiceImpl();
        List<Course> courseList=courseService.findAll();
        request.setAttribute("courseList",courseList);
        System.out.println("Add");
        request.getRequestDispatcher("/WEB-INF/pages/store/catalog/add.jsp").forward(request,response);

    }
}
