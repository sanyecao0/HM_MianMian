package com.itheima.web.controller.store;

import com.itheima.domain.store.QuestionItem;
import com.itheima.service.store.Impl.QuestionItemServiceImpl;
import com.itheima.service.store.QuestionItemService;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.system.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/store/questionItem")
public class QuestionItemServlet extends BaseServlet {
    /**
     * 查询当前问题的选项
     * @param request
     * @param response
     */
    QuestionItemService questionItemService=new QuestionItemServiceImpl();

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1 获取请求参数（问题id）
        String questionId = request.getParameter("questionId");
        //2 调用service层方法获取分页结果
        List<QuestionItem> questionItemList = questionItemService.findAll(questionId);
        //3 将结果存储到request域中，跳转到list.jsp页面
        request.setAttribute("questionItemList",questionItemList);
        //保存问题id，后续增、删、该使用
        request.setAttribute("questionId",questionId);
        request.getRequestDispatcher("/WEB-INF/pages/store/questionItem/list.jsp").forward(request,response);
    }
    private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1 获取请求参数并封装成QuestionItem对象
        QuestionItem questionItem = BeanUtil.fillBean(request, QuestionItem.class);
        //2 调用service层方法添加数据
        questionItemService.save(questionItem);
        //3 页面跳转
        list(request,response);
    }
    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1 获取要修改的数据id
        String id = request.getParameter("id");
        //2 调用service层方法，根据id查询数据,保存到request域中
        QuestionItem questionItem = questionItemService.findByID(id);
        request.setAttribute("questionItem",questionItem);
        //3 页面跳转
        list(request,response);
    }

    /**
     * 处理修改数据的请求
     * @param request
     * @param response
     */
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1 获取请求参数并封装
        QuestionItem questionItem = BeanUtil.fillBean(request, QuestionItem.class);
        //2 调用service层方法，修改数据
        questionItemService.update(questionItem);
        //3 页面跳转
        list(request,response);
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1 获取请求参数id，
        String id = request.getParameter("id");
        //2 调用service层方法，删除数据
        questionItemService.delete(id);
        //3 页面跳转
        list(request,response);
    }
}
