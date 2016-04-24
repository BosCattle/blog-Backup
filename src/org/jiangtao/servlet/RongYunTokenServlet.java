package org.jiangtao.servlet;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.jiangtao.io.rong.ApiHttpClient;
import org.jiangtao.io.rong.models.FormatType;
import org.jiangtao.io.rong.models.RongYun;
import org.jiangtao.io.rong.models.SdkHttpResult;
import org.jiangtao.utils.Profile;

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
            token = ApiHttpClient.getToken(Profile.APP_KEY,Profile.App_Secret,userId,userName,portraitUri, FormatType.json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (token!=null) {
            RongYun rongyun = new RongYun(userId, userName, portraitUri, token.getResult());
            JSONObject object = JSONObject.fromObject(rongyun);
            out.print(object.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
