package org.jiangtao.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.jiangtao.bean.test.Image;

/**
 * Created by kevin on 05/11/2016.
 */
@WebServlet(name = "ImageServlet", urlPatterns = "/test/image.action")
public class ImageServlet extends HttpServlet {

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/json:charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.addHeader("Access-Control-Allow-Origin", "*");
    PrintWriter out = response.getWriter();
    Image image = new Image("范冰冰",
        "https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1478341620683&di=b3fe17a82482aeccf43f03682bb510dd&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2014%2F067%2F5116EPAUD762_1000x500.jpg");
    ArrayList<Image> list = new ArrayList<>();
    for (int i = 0;i<100;i++){
      list.add(image);
    }
    out.print(JSONArray.fromObject(list));
  }
}
