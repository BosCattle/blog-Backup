package org.jiangtao.servlet;

import org.jiangtao.daoImpl.CollectionsDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by MrJiang on 5/6/2016.
 * 取消收藏
 */
@WebServlet(name = "CancelServlet", urlPatterns = "/collections/cancel")
public class CancelServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html:charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String article_id = request.getParameter("article_id");
        String account_id = request.getParameter("account_id");
        String id = request.getParameter("id");
        out.print(CollectionsDaoImpl.getInstance().cancelCollections(id, article_id, account_id));
    }
}
