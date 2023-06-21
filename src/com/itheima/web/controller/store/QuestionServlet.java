package com.itheima.web.controller.store;

import com.itheima.domain.common.PageBean;
import com.itheima.domain.store.Catalog;
import com.itheima.domain.store.Course;
import com.itheima.domain.store.Question;
import com.itheima.domain.system.Company;
import com.itheima.service.store.CatalogService;
import com.itheima.service.store.Impl.CatalogServiceImpl;
import com.itheima.service.store.Impl.QuestionServiceImpl;
import com.itheima.service.store.QuestionService;
import com.itheima.service.system.CompanyService;
import com.itheima.service.system.impl.CompanyServiceImpl;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.system.BaseServlet;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/store/question")
public class QuestionServlet extends BaseServlet {
    private QuestionService questionService = new QuestionServiceImpl();
    CompanyService companyService = new CompanyServiceImpl();
    CatalogService catalogService = new CatalogServiceImpl();

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String currpage = req.getParameter("currPage");
        int Currpage = 1;
        int size = 5;//默认每条页数
        if (StringUtils.isNoneEmpty(currpage)) {
            Currpage = Integer.parseInt(currpage);
        }
        PageBean pageBean = questionService.findPages(Currpage, size);
        req.setAttribute("page", pageBean);
        req.getRequestDispatcher("/WEB-INF/pages/store/question/list.jsp").forward(req, resp);
    }

    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("save");
        Question question = BeanUtil.fillUploadBean(req, Question.class);
        questionService.save(question);
        List<Company> companyList = companyService.findAll();
        List<Catalog> catalogList = catalogService.findAll();
        req.setAttribute("companyList", companyList);
        req.setAttribute("catalogList", catalogList);
        list(req, resp);
    }

    protected void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");
        System.out.println("toedit");
        Question question = questionService.findByID(id);
        List<Company> companyList = companyService.findAll();
        List<Catalog> catalogList = catalogService.findAll();
        request.setAttribute("companyList", companyList);
        request.setAttribute("catalogList", catalogList);
        request.setAttribute("question", question);
        request.getRequestDispatcher("/WEB-INF/pages/store/question/update.jsp").forward(request, response);
    }

    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //Update：edit <c:forEach items="${companyList}" var="item"><option value="${item.id}" ${question.companyId eq item.id ? 'selected' : ''}>${item.name}</option></c:forEach>
        Question question = BeanUtil.fillUploadBean(req, Question.class);
        System.out.println("edit");
        List<Company> companyList = companyService.findAll();
        req.setAttribute("companyList", companyList);
        questionService.update(question);
        list(req, resp);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("delete");
        String id = req.getParameter("id");
        questionService.delete(id);
        list(req, resp);
    }

    protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        List<Company> companyList = companyService.findAll();
        List<Catalog> catalogList = catalogService.findAll();
        request.setAttribute("companyList", companyList);
        request.setAttribute("catalogList", catalogList);
        System.out.println("Add");
        request.getRequestDispatcher("/WEB-INF/pages/store/question/add.jsp").forward(request, response);

    }

    /**
     * 处理跳转到审核页面的请求
     *
     * @param request
     * @param response
     */
    private void toExamine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1 获取要修改的数据id
        String id = request.getParameter("id");
        //2 调用service层方法，根据id查询数据,保存到request域中
        Question question = questionService.findByID(id);
        request.setAttribute("question", question);
        //查询所有题目类型信息,保存到request域中
        List<Catalog> catalogList = catalogService.findAll();
        request.setAttribute("catalogList", catalogList);
        //查询所有企业信息,保存到request域中
        List<Company> companyList = companyService.findAll();
        request.setAttribute("companyList", companyList);
        //只能使用请求转发，因为页面是在web-inf中，浏览器不能直接通过路径访问
        request.getRequestDispatcher("/WEB-INF/pages/store/question/examine.jsp").forward(request, response);
    }
    protected void toExport(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        //1 设置文件下载的响应头
        response.setHeader("content-disposition","attachment;filename=question.xlsx");
        //2 调用service层完成文件下载
        questionService.toExport(response.getOutputStream());
    }

}