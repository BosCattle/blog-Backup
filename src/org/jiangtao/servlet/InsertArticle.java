package org.jiangtao.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.jiangtao.bean.Accounts;
import org.jiangtao.bean.Articles;
import org.jiangtao.daoImpl.AccountsDaoImpl;
import org.jiangtao.daoImpl.ArticleDaoImpl;

/**
 * Created by MrJiang on 4/2/2016.
 */
@WebServlet(urlPatterns = "/article/insert")
public class InsertArticle extends HttpServlet {
  String imageUrl;
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html:charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    String accountId =
        new String(request.getParameter("account_id").getBytes("iso-8859-1"), "UTF-8");
    String content = new String(request.getParameter("content").getBytes("iso-8859-1"), "UTF-8");
    String title = new String(request.getParameter("title").getBytes("iso-8859-1"), "UTF-8");
    imageUrl = new String(request.getParameter("image_url").getBytes("iso-8859-1"), "UTF-8");
    if (imageUrl == null) {
      imageUrl = "";
    }
    Accounts accounts = AccountsDaoImpl.getInstance().getAccount(accountId);
    System.out.println("测试");
    Articles articles =
        new Articles(Integer.parseInt(accountId), content, title, imageUrl, accounts);
    Articles articles1 = ArticleDaoImpl.getInstance().insertArticle(articles);
    JSONObject object1 = JSONObject.fromObject(articles1);
    out.print(object1);
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

  }
}
