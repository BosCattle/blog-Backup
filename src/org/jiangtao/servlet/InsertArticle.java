package org.jiangtao.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.jiangtao.bean.Articles;
import org.jiangtao.daoImpl.ArticleDaoImpl;

/**
 * Created by MrJiang on 4/2/2016.
 */
@WebServlet(urlPatterns = "/article/insert")
public class InsertArticle extends HttpServlet {
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html:charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    String accountId = request.getParameter("account_id");
    String content = request.getParameter("content");
    File directory = new File("");
    String address = directory.getAbsolutePath();
    Articles articles = ArticleDaoImpl.getInstance().writeArticle(content, accountId, address);
    Articles articles1 = ArticleDaoImpl.getInstance().insertArticle(articles);
    JSONObject object1 = JSONObject.fromObject(articles1);
    out.print(object1);
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

  }
}
