package org.jiangtao.auth;


import net.sf.json.JSONObject;
import org.jiangtao.bean.Accounts;
import org.jiangtao.daoImpl.AccountsDaoImpl;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by MrJiang on 2016/3/17.
 * des:登录接口
 */
@javax.servlet.annotation.WebServlet(urlPatterns = "/auth/login")
public class login extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html:charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        Accounts accounts = AccountsDaoImpl.getInstance().getAccount(phone, password);
        if (accounts != null) {
            JSONObject jsonObject = JSONObject.fromObject(accounts);
            out.print(jsonObject);
        }

    }
}
