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
    String accountId =
        new String(request.getParameter("account_id").getBytes("iso-8859-1"), "UTF-8");
    String content = new String(request.getParameter("content").getBytes("iso-8859-1"), "UTF-8");
    String address = getServletContext().getRealPath("/");//项目绝对路径
    Articles articles = null;
    try {
      articles = ArticleDaoImpl.getInstance().writeArticle(content, accountId, address);
    } catch (Exception e) {
      e.printStackTrace();
    }
    Articles articles1 = ArticleDaoImpl.getInstance().insertArticle(articles);
    JSONObject object1 = JSONObject.fromObject(articles1);
    out.print(object1);
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

  }
}
