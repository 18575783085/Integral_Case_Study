package day11_ServletConfig;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletConfigDemo1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取在web.xml中为当前Servlet配置的初始化参数
		//1.获取ServletConfig对象 
		ServletConfig config = this.getServletConfig();
		
		//2.获取配置文件的参数
		String driver = config.getInitParameter("driverClass");
		String url = config.getInitParameter("jdbcUrl");
		
		System.out.println("driver="+driver);
		System.out.println("url="+url);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
