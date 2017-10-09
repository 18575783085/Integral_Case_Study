package day11_ServletConfig;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletConfigDemo2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.创建ServletConfig对象
		ServletConfig config = this.getServletConfig();
		
		//2.获取ServletConfig参数
		String user = config.getInitParameter("user");
		String pwd = config.getInitParameter("password");
		
		System.out.println("user="+user);
		System.out.println("password="+pwd);
		
		//注意两个ServletConfig对象不能获取彼此之间的初始化参数
		
		/*
		 * 通过config对象来获取ServletContext对象
		 */
		//方法一：
		ServletContext context1 = this.getServletConfig().getServletContext();
		
		//方法二：
		ServletContext context2 = this.getServletContext();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
