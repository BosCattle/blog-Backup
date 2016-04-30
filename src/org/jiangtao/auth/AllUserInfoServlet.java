package org.jiangtao.auth;

import com.google.gson.JsonObject;
import net.sf.json.JSONArray;
import org.jiangtao.bean.Accounts;
import org.jiangtao.daoImpl.AccountsDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by MrJiang on 5/1/2016.
 * 获取所有的用户
 */
@WebServlet(name = "AllUserInfoServlet", urlPatterns = "/auth/allInfo")
public class AllUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html:charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ArrayList<Accounts> accountses = AccountsDaoImpl.getInstance().getAllAccount();
        JSONArray array = JSONArray.fromObject(accountses);
        out.print(array.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
