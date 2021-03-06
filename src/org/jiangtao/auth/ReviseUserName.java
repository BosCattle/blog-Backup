package org.jiangtao.auth;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.jiangtao.bean.Accounts;
import org.jiangtao.daoImpl.AccountsDaoImpl;

/**
 * Created by MrJiang on 4/9/2016. 更新用户名
 */
@WebServlet(name = "ReviseUserName", urlPatterns = "/auth/revise/username")
public class ReviseUserName extends HttpServlet {
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html:charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    String userName = new String(request.getParameter("username").getBytes("iso-8859-1"), "UTF-8");
    String id = request.getParameter("id");
    AccountsDaoImpl accountsDao = AccountsDaoImpl.getInstance();
    Accounts accounts = accountsDao.updateAccounts(userName, id);
    if (accounts != null) {
      JSONObject object1 = JSONObject.fromObject(accounts);
      out.print(object1);
    }
  }
}
