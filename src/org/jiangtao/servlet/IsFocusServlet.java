package org.jiangtao.servlet;

import net.sf.json.JSONObject;
import org.jiangtao.bean.IsFocus;
import org.jiangtao.daoImpl.FocusDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by MrJiang on 5/2/2016.
 * 判断两人是否已关注
 */
@WebServlet(name = "IsFocusServlet", urlPatterns = "/focus/judge")
public class IsFocusServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html:charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String account_id = request.getParameter("account_id");
        String focus_id = request.getParameter("focus_id");
        IsFocus isFocus = FocusDaoImpl.getInstance().isFocus(account_id, focus_id);
        JSONObject object = JSONObject.fromObject(isFocus);
        out.print(object.toString());
    }
}
