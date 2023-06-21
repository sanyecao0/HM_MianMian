package com.itheima.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.Dept;
import com.itheima.domain.system.Module;
import com.itheima.domain.system.role_Module;
import com.itheima.service.system.DeptService;
import com.itheima.service.system.impl.DeptServiceImpl;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/system/module")
public class moduleServlet extends  BaseServlet{

        private moduleService Service=new moduleServiceimpl();

        protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
            String currpage=req.getParameter("currPage");
            int Currpage=1;
            int size=5;//默认每条页数
            if(StringUtils.isNoneEmpty(currpage)){
                Currpage=Integer.parseInt(currpage);
            }
            PageBean pageBean=Service.findPages(Currpage,size);
            req.setAttribute("page",pageBean);
            req.getRequestDispatcher("/WEB-INF/pages/system/module/list.jsp").forward(req,resp);
        }
        protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
            System.out.println("save");
           Module module= BeanUtil.fillBean(req,Module.class);
            Service.save(module);
            list(req,resp);
        }
        protected void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
            String id = request.getParameter("id");
            System.out.println("toedit");
            Module module = Service.findByID(id);
            request.setAttribute("module", module);
            request.getRequestDispatcher("/WEB-INF/pages/system/module/update.jsp").forward(request,response);
        }
        protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
            Module module=BeanUtil.fillBean(req, Module.class);
            System.out.println("edit");
            Service.update(module);
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
            List<Module> modules = Service.findAll();
            request.setAttribute("moduleList", modules);
            request.getRequestDispatcher("/WEB-INF/pages/system/module/add.jsp").forward(request, response);
        }
    protected  void findModulesByRoleId(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
        String roleId=request.getParameter("roleId");
        List<Module> modules= Service.findModulesByRoleId(roleId);//一个roleid带有的所有module
        List<Module> allModules=Service.findAll();
        List<role_Module> role_modules=new ArrayList<role_Module>();
        for (Module module:allModules){//改为rloe-module对象
            role_Module role_module=new role_Module(module.getId(),module.getParentId(),module.getName(),false);

            if(Service.isExists(role_module.getId(),modules)){
                role_module.setCheck(true);
            }
            role_modules.add(role_module);
        }
        PrintWriter out = response.getWriter();
        String jsonString = JSONObject.toJSONString(role_modules);
        //System.out.println(jsonString);
        out.print(jsonString);
        out.flush();
    }
}
