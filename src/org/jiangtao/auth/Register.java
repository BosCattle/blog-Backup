package org.jiangtao.auth;


import net.sf.json.JSONObject;
import org.jiangtao.bean.Accounts;
import org.jiangtao.daoImpl.RegisterDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by MrJiang on 2016/3/19.
 * 注册信息
 * 将功能抽出来，只需要调用
 */
@WebServlet(urlPatterns = "/auth/register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html:charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        if (RegisterDaoImpl.getInstance().isExits(phone)) {
            Accounts accounts = RegisterDaoImpl.getInstance().getAccounts(phone, password);
            JSONObject object1 = JSONObject.fromObject(accounts);
            out.print(object1);
        }
    }
}
