package org.jiangtao.servlet;

import net.sf.json.JSONArray;
import org.jiangtao.bean.Focus;
import org.jiangtao.daoImpl.FocusDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by MrJiang on 5/2/2016.
 * 取消关注
 */
@WebServlet(name = "CancelFocusServlet", urlPatterns = "/focus/cancel")
public class CancelFocusServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html:charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String account_id = request.getParameter("account_id");
        String focus_id = request.getParameter("focus_id");
        ArrayList<Focus> focuses = FocusDaoImpl.getInstance().cancelFocus(account_id, focus_id);
        JSONArray array = JSONArray.fromObject(focuses);
        out.print(array.toString());
    }
}
