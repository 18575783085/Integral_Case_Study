package day11_ServletConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextDemo3 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*Properties p1 = new Properties();
		File file = new File("config.properties");
		System.out.println("path:"+file.getAbsolutePath());
		//path:C:\Program Files\Java\apache-tomcat-7.0.67\bin\config.properties
		p1.load(new FileInputStream(file));*/
		
		/*Properties p2 = new Properties();
		File file = new File("/config.properties");
		System.out.println("path:"+file.getAbsolutePath());
		//2.path:C:\config.properties
		p2.load(new FileInputStream(file));*/
		
		
		
		//通过ServletContext获取文件信息
		ServletContext context = this.getServletContext();
		String path = context.getRealPath("/WEB-INF/classes/config.properties");
		Properties p3 = new Properties();
		p3.load(new FileInputStream(path));
		
		//获取参数
		String driver = p3.getProperty("driverClass");
		String url = p3.getProperty("jdbcUrl");
		String user = p3.getProperty("user");
		String pwd = p3.getProperty("password");
		
		System.out.println("driver="+driver);
		System.out.println("url="+url);
		System.out.println("user="+user);
		System.out.println("pwd="+pwd);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
