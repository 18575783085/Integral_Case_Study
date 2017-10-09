package day11_ServletConfig;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextDemo1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* 获取为整个web应用配置的参数*/
		//1.获取代表整个web应用的对象-------ServletContext
		ServletContext context = this.getServletContext();
		
		//2.通过Context对象获取web应用的初始化参数
		String v1 = context.getInitParameter("param1");
		String v2 = context.getInitParameter("param2");
		
		System.out.println("param1:"+v1);
		System.out.println("param2:"+v2);
		
		/*---设置公共参数，让别的servlet来获取该公共参数*/
		context.setAttribute("name", "彭于晏");
		context.setAttribute("nickname", "parkoo");
		context.setAttribute("age", "18");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
