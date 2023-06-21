package com.itheima.web.controller.system;

import com.itheima.dao.system.RoleDao;
import com.itheima.dao.system.UserDao;
import com.itheima.dao.system.impl.RoleDaoImpl;
import com.itheima.dao.system.impl.UserDaoImpl;
import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.Dept;
import com.itheima.domain.system.Module;
import com.itheima.domain.system.Role;
import com.itheima.domain.system.User;
import com.itheima.service.system.DeptService;
import com.itheima.service.system.RoleService;
import com.itheima.service.system.UserService;
import com.itheima.service.system.impl.DeptServiceImpl;
import com.itheima.service.system.impl.RoleServiceImpl;
import com.itheima.service.system.impl.UserServiceImpl;
import com.itheima.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/system/user")
public class UserServlet extends  BaseServlet{
    private UserService Service=new UserServiceImpl();
    DeptService deptService=new DeptServiceImpl();
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String currpage=req.getParameter("currPage");
        int Currpage=1;
        int size=5;//默认每条页数
        if(StringUtils.isNoneEmpty(currpage)){
            Currpage=Integer.parseInt(currpage);
        }
        PageBean pageBean=Service.findPages(Currpage,size);
        req.setAttribute("page",pageBean);
        req.getRequestDispatcher("/WEB-INF/pages/system/user/list.jsp").forward(req,resp);
    }
    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("save");
        User user= BeanUtil.fillBean(req, User.class);
        Service.save(user);
        list(req,resp);
    }
    protected void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");
        System.out.println("toedit");
        List<Dept> depts = deptService.findAll();
        request.setAttribute("deptList", depts);
        User user = Service.findByID(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/pages/system/user/update.jsp").forward(request,response);
    }
    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        User user=BeanUtil.fillBean(req, User.class);
        System.out.println("edit");
        Service.update(user);
        list(req,resp);
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("delete");
        String id = req.getParameter("id");
        Service.delete(id);
        list(req,resp);
    }
    protected  void toAdd(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        System.out.println("Add");
        List<Dept> depts = deptService.findAll();
        request.setAttribute("deptList", depts);
        request.getRequestDispatcher("/WEB-INF/pages/system/user/add.jsp").forward(request, response);
    }
    protected  void toAuthor(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        String userID=request.getParameter("userId");
        User user=Service.findByID(userID);
        UserDao dao=new UserDaoImpl();
        RoleDao roledao=new RoleDaoImpl();
        List<Role> roleList=roledao.findAll();
        List<String> roleIds =dao.findRoleID(userID);
        System.out.println(roleIds);
        request.setAttribute("user", user);
        request.setAttribute("roleList", roleList);
        request.setAttribute("roleIds",roleIds);
        request.getRequestDispatcher("/WEB-INF/pages/system/user/author.jsp").forward(request,response);
    }
    protected  void updateUserAndRoles(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
             String[] roleIds= request.getParameterValues("roleIds");
             String userId=request.getParameter("userId");
             Service.updateUserAndRoles(roleIds,userId);
        list(request,response);

    }
}
