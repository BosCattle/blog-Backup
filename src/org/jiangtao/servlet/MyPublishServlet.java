package org.jiangtao.servlet;

import net.sf.json.JSONArray;
import org.jiangtao.bean.Articles;
import org.jiangtao.daoImpl.ArticleDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by MrJiang on 5/2/2016.
 * 我的发布
 */
@WebServlet(name = "MyPublishServlet",urlPatterns = "/article/mine")
public class MyPublishServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html:charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String account_id = request.getParameter("account_id");
        ArrayList<Articles> articles = ArticleDaoImpl.getInstance().getPublishArticle(account_id);
        JSONArray array = JSONArray.fromObject(articles);
        out.print(array.toString());
    }
}
