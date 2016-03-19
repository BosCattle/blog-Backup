package org.jiangtao.auth;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.jiangtao.bean.Accounts;
import org.jiangtao.utils.Collections;

import java.io.IOException;
import java.sql.SQLException;

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
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String token = request.getParameter("token");
        ConnectionSource connectionSource = Collections.getInstance().openConnectionResource();
        Dao<Accounts, String> accountDao = null;
        try {
            accountDao = DaoManager.createDao(connectionSource, Accounts.class);
            System.out.println(name + "         " + password);
            if (token==null||token.length()==0){
                Accounts accounts = new Accounts("",name, password);
                accountDao.create(accounts);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Collections.getInstance().closeConnectionResource(connectionSource);
        }

    }
}
