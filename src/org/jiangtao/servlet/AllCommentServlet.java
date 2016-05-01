package org.jiangtao.servlet;

import net.sf.json.JSONArray;
import org.jiangtao.bean.Comment;
import org.jiangtao.daoImpl.CommentDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.smartcardio.CommandAPDU;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by MrJiang on 5/1/2016.
 * 获取文章所有评论
 */
@WebServlet(name = "AllCommentServlet", urlPatterns = "/comment/all")
public class AllCommentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html:charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String articleId = request.getParameter("article_id");
        ArrayList<Comment> comments = CommentDaoImpl.getInstance().getAllComment(articleId);
        if (comments!=null) {
            JSONArray array = JSONArray.fromObject(comments);
            out.print(array.toString());
        }
    }
}
