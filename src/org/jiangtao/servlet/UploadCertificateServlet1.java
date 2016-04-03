package org.jiangtao.servlet;

import com.qiniu.util.Auth;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

/**
 * 七牛token
 */
@WebServlet(urlPatterns = "/qiniu/token")
public class UploadCertificateServlet1 extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final String AK = "LJssRPnt4N8FbBhvIOBymUjDq_WtZqLr6UuCVVmd";
  private static final String SK = "BHzc3sMP2kw9svyeHLO_KjO2DWyP2LMMAbEBRVYh";
  private static final String bucket = "kevin";

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    Auth auth = Auth.create(AK, SK);
    String token = auth.uploadToken(bucket);
    JSONObject object = new JSONObject();
    object.accumulate("uploadToken", token);
    out.print(object.toString());
    out.flush();
    out.close();
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.flush();
    out.close();
  }
}
