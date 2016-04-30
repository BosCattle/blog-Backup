package org.jiangtao.servlet;

import net.sf.json.JSONObject;
import org.jiangtao.rong.ApiHttpClient;
import org.jiangtao.rong.models.FormatType;
import org.jiangtao.rong.models.RongYun;
import org.jiangtao.rong.models.SdkHttpResult;
import org.jiangtao.utils.Profiles;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by MrJiang on 4/24/2016.
 * 获取融云token
 */
@WebServlet(name = "RongYunTokenServlet",urlPatterns = "/rongyun/token")
public class RongYunTokenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String userId = request.getParameter("userId");
        String userName = new String(request.getParameter("userName").getBytes("iso-8859-1"),"UTF-8");
        String portraitUri = request.getParameter("portraitUri");
        SdkHttpResult token = null;
        try {
            token = ApiHttpClient.getToken(Profiles.APP_KEY, Profiles.App_Secret, userId, userName, portraitUri, FormatType.json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (token!=null) {
            JSONObject objectToken = JSONObject.fromObject(token.getResult());
            String realToken = objectToken.getString("token");
            System.out.println(realToken);
            RongYun rongyun = new RongYun(userId, userName, portraitUri, realToken);
            JSONObject object = JSONObject.fromObject(rongyun);
            System.out.println(object.toString());
            out.print(object.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
