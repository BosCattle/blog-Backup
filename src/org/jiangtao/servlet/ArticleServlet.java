package org.jiangtao.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.jiangtao.bean.Articles;
import org.jiangtao.daoImpl.ArticleDaoImpl;

/**
 * Created by MrJiang on 4/4/2016. 获取所有文章
 */
@WebServlet(urlPatterns = "/article/all")
public class ArticleServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html:charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    String sinceId = request.getParameter("max-id");
    ArrayList<Articles> lists = ArticleDaoImpl.getInstance().getAllArticles(sinceId);
    JSONArray array = JSONArray.fromObject(lists);
    out.print(array.toString());
  }
}
