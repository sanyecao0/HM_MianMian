package com.itheima.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.itheima.dao.system.RoleDao;
import com.itheima.dao.system.UserDao;
import com.itheima.dao.system.impl.RoleDaoImpl;
import com.itheima.dao.system.impl.UserDaoImpl;
import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.*;
import com.itheima.domain.system.Module;
import com.itheima.service.system.RoleService;
import com.itheima.service.system.UserService;
import com.itheima.service.system.impl.DeptServiceImpl;
import com.itheima.service.system.impl.RoleServiceImpl;
import com.itheima.service.system.impl.UserServiceImpl;
import com.itheima.service.system.impl.moduleServiceimpl;
import com.itheima.service.system.moduleService;
import com.itheima.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Provider;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/system/role")
public class RoleServlet extends  BaseServlet {
    private RoleService roleService = new RoleServiceImpl();

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String currpage = req.getParameter("currPage");
        int Currpage = 1;
        int size = 5;//默认每条页数
        if (StringUtils.isNoneEmpty(currpage)) {
            Currpage = Integer.parseInt(currpage);
        }
        PageBean pageBean = roleService.findPages(Currpage, size);
        req.setAttribute("page", pageBean);
        req.getRequestDispatcher("/WEB-INF/pages/system/role/list.jsp").forward(req, resp);
    }

    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("save");
        Role role = BeanUtil.fillBean(req, Role.class);
        roleService.save(role);
        list(req, resp);
    }

    protected void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");
        System.out.println("toedit");
        List<Role> roles = roleService.findAll();
        request.setAttribute("roleList", roles);
        Role role = roleService.findByID(id);
        request.setAttribute("role", role);
        request.getRequestDispatcher("/WEB-INF/pages/system/role/update.jsp").forward(request, response);
    }

    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Role role = BeanUtil.fillBean(req, Role.class);
        System.out.println("edit");
        roleService.update(role);
        list(req, resp);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("delete");
        String id = req.getParameter("id");
        roleService.delete(id);
        list(req, resp);
    }

    protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.getRequestDispatcher("/WEB-INF/pages/system/role/add.jsp").forward(request, response);
    }

    protected void toAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String roleId = request.getParameter("roleId");
        Role role = roleService.findByID(roleId);
        request.setAttribute("role", role);
        request.getRequestDispatcher("/WEB-INF/pages/system/role/author.jsp").forward(request, response);
    }

    protected void updateRoleAndModules(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        //拿到处理的角色roleid
        String id = request.getParameter("roleId");
        String modules = request.getParameter("moduleIds");//拿到moduleid
        List<String> Moduleids=AnalyzeModulesID(modules);
        RoleDao roleDao=new RoleDaoImpl();
        roleDao.updateRoleAndModules(Moduleids,id);
        list(request, response);
    }

    List<String> AnalyzeModulesID(String initString) {
        String[] str=initString.split(",");
        List<String> result = Arrays.asList(str);
        return  result;
    }
}