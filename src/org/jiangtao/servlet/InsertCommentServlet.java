package org.jiangtao.servlet;

import net.sf.json.JSONArray;
import org.jiangtao.bean.Comment;
import org.jiangtao.daoImpl.CommentDaoImpl;

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
 * 插入评论
 */
@WebServlet(name = "InsertCommentServlet", urlPatterns = "/comment/insert")
public class InsertCommentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html:charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String articleId = request.getParameter("article_id");
        String content = request.getParameter("content");
        String isParent = request.getParameter("is_parent");
        String account_id = request.getParameter("account_id");
        String parent_account_id = request.getParameter("parent_account_id");

        ArrayList<Comment> comments = CommentDaoImpl.getInstance().insertComment(Integer.parseInt(articleId),
                content, Integer.parseInt(isParent), Integer.parseInt(account_id), Integer.parseInt(parent_account_id));
        if (comments != null) {
            JSONArray array = JSONArray.fromObject(comments);
            out.print(array.toString());
        }
    }
}
