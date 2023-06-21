package com.itheima.web.controller.store;

import com.itheima.domain.common.PageBean;
import com.itheima.domain.store.Course;
import com.itheima.service.store.CourseService;
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

@WebServlet("/store/course")
public class CourseServlet extends BaseServlet {
    private CourseService courseService =new CourseServiceImpl();
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String currpage=req.getParameter("currPage");
        int Currpage=1;
        int size=5;//默认每条页数
        if(StringUtils.isNoneEmpty(currpage)){
            Currpage=Integer.parseInt(currpage);
        }
        PageBean pageBean= courseService.findPages(Currpage,size);
        req.setAttribute("page",pageBean);
        req.getRequestDispatcher("/WEB-INF/pages/store/course/list.jsp").forward(req,resp);
    }
    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("save");
        Course course= BeanUtil.fillBean(req,Course.class);
        courseService.save(course);
        list(req,resp);
    }
    protected void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");
        System.out.println("toedit");
        Course course = courseService.findByID(id);
        request.setAttribute("course",course);
        request.getRequestDispatcher("/WEB-INF/pages/store/course/update.jsp").forward(request,response);
    }
    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Course course=BeanUtil.fillBean(req, Course.class);
        System.out.println("edit");
        courseService.update(course);
        list(req,resp);
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("delete");
        String id = req.getParameter("id");
        courseService.delete(id);
        list(req,resp);
    }
    protected  void toAdd(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Add");
        request.getRequestDispatcher("/WEB-INF/pages/store/course/add.jsp").forward(request,response);

    }
}
