package day11_ServletConfig;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextDemo2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.创建ServletContext对象
		ServletContext context = this.getServletContext();
		
		//2.获取公共参数
		String name = (String) context.getAttribute("name");
		String nickname = (String) context.getAttribute("nickname");
		String age = (String) context.getAttribute("age");
		
		System.out.println("name:"+name);
		System.out.println("nickname:"+nickname);
		System.out.println("age:"+age);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
