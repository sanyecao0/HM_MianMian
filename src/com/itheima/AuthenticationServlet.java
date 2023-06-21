package com.itheima;

import com.itheima.domain.system.Module;
import com.itheima.domain.system.User;
import com.itheima.service.system.RoleService;
import com.itheima.service.system.UserService;
import com.itheima.service.system.impl.RoleServiceImpl;
import com.itheima.service.system.impl.UserServiceImpl;
import com.itheima.service.system.impl.moduleServiceimpl;
import com.itheima.service.system.moduleService;
import com.itheima.web.controller.system.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/authentication")
public class AuthenticationServlet extends BaseServlet {

    private UserService userService=new UserServiceImpl();
    RoleService roleService=new RoleServiceImpl();
    /**
     * 用户登录
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1 获取email和password
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //2 调用service层方法登录，得到user对象
        User user=userService.login(email,password);
        //3 如果user==null,说明登录失败,保存错误信息到request域中,请求转发到login.jsp页面展示
        if(user==null){
            request.setAttribute("error","用户名或者密码错误!");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else {
            request.getSession().setAttribute("user",user);
              moduleService service=new moduleServiceimpl();
            List<Module> moduleList= service.findModulesByUserId(user.getId());
            request.getSession().setAttribute("moduleList",moduleList);
            List<String> roleIdsList =
                    roleService.findRoleIdsByUserID(user.getId());
            request.getSession().setAttribute("permission",roleIdsList);
            System.out.println("该用户的角色Id们："+roleIdsList);
            response.sendRedirect(request.getContextPath()+"/pages/home/main.jsp");
        }
    }
    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //销毁session
        request.getSession().invalidate();
        //重定向到登录页面
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }
}
