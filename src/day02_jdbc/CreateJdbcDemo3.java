package day02_jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class CreateJdbcDemo3 {
	
	
	public static void main(String[] args) {
		createConnection();
		createTable();
		insert();
		update();
		delete();
		select();
	}
	
	/**
	 * 数据库路径
	 */
	private static String url;
	/**
	 * 数据库账号
	 */
	private static String user;
	/**
	 * 数据库密码
	 */
	private static String pwd;
	/**
	 * jdbc快速创建连接数据库案例二：通过properties文件获取数据库参数
	 * 测试连接
	 */
	static{
		//1.创建properties对象
		Properties p = new Properties();
		
		try {
			//2.加载文件
			p.load(new FileInputStream(new File("db.properties")));
			
			//3.获取数据库参数
			String dirver = p.getProperty("driver");
			url = p.getProperty("url");
			user = p.getProperty("user");
			pwd = p.getProperty("pwd");
			
			//4.注册驱动
			Class.forName(dirver);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("文件不存在", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("加载文件失败", e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("注册数据库驱动失败", e);
		}
	}
	
	/**
	 * 创建数据库连接
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, pwd);
	}
	
	/**
	 * 关闭资源
	 */
	public static void close(Connection conn,Statement statement,ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				statement = null;
			}
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
	
	
	/**
	 * 测试数据库连接
	 */
	public static void createConnection(){
		Connection conn = null;
		try {
			//1.创建数据库连接
			conn = CreateJdbcDemo3.getConnection();
			
			System.out.println(conn);
			System.out.println("连接成功");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接失败", e);
		} finally {
			CreateJdbcDemo3.close(conn, null, null);
		}
	}
	
	
	/**
	 * 
	 * 创建表：jdbcdemo
	 * id,name,gender,birthday,salary
	 *
	 */
	public static void createTable(){
		Connection conn = null;
		Statement statement = null;
		try {
			//1.创建数据库连接
			conn = CreateJdbcDemo3.getConnection();
			
			//2.创建sql传输器
			statement = conn.createStatement();
			
			//3.创建sql语句
			String createtable = "create table jdbcdemo(" + "id number primary key auto_increment,"
					+ "name varchar(50) unique" 
					+ "gender char(1) not null" 
					+ "birthday date," 
					+ "salary double" + ")";
			
			//4.执行sql语句
			statement.executeUpdate(createtable);
			
			System.out.println("创建表成功");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接失败", e);
		} finally {
			//5.关闭资源
			CreateJdbcDemo3.close(conn, statement, null);
		}
	}
	/**
	 * 插入数据：insert
	 */
	public static void insert(){
		Connection conn = null;
		Statement statement = null;
		try {
			//1.创建数据库连接
			conn = CreateJdbcDemo3.getConnection();
			
			//2.创建sql传输器
			statement = conn.createStatement();
			
			//3.创建sql语句
			String insertSql = "insert into jdbcdemo"
					+ "(id,name,gender,birthday,salary)"
					+ "values"
					+ "(null,'钱七','男','2017-10-03',6666.6)";
			
			//4.执行sql语句
			int n = statement.executeUpdate(insertSql);
			
			System.out.println("成功插入"+n+"条数据");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接失败", e);
		} finally {
			//5.关闭资源
			CreateJdbcDemo3.close(conn, statement, null);
		}
	}
	/**
	 * 更新数据：update
	 */
	public static void update(){
		Connection conn = null;
		Statement statement = null;
		try {
			//1.创建数据库连接
			conn = CreateJdbcDemo3.getConnection();
			
			//2.创建sql传输器
			statement = conn.createStatement();
			
			//3.创建sql语句
			String updateSql = "update jdbcdemo "
					+ "set name='曹八',gender='女',birthday='2017-10-09',salary=666.66 "
					+ "where id=3";
			
			//4.执行sql语句
			int n = statement.executeUpdate(updateSql);
			
			System.out.println("成功更新"+n+"条数据");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接失败", e);
		} finally {
			//5.关闭资源
			CreateJdbcDemo3.close(conn, statement, null);
		}
	}
	/**
	 * 删除数据：delete
	 */
	public static void delete(){
		Connection conn = null;
		Statement statement = null;
		try {
			//1.创建数据库连接
			conn = CreateJdbcDemo3.getConnection();
			
			//2.创建sql传输器
			statement = conn.createStatement();
			
			//3.创建sql语句
			String deleteSql = "delete from jdbcdemo where id=3";
			
			//4.执行sql语句
			int n = statement.executeUpdate(deleteSql);
			
			System.out.println("成功删除"+n+"条数据");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接失败", e);
		} finally {
			//5.关闭资源
			CreateJdbcDemo3.close(conn, statement, null);
		}
	}
	/**
	 * 查询数据：select
	 */
	public static void select(){
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			//1.创建数据库连接
			conn = CreateJdbcDemo3.getConnection();
			
			//2.创建sql传输器
			statement = conn.createStatement();
			
			//3.创建sql语句
			String selectSql = "select * from jdbcdemo";
			
			//4.执行sql语句
			rs = statement.executeQuery(selectSql);
			
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				Date birthday = rs.getDate("birthday");
				Double salary = rs.getDouble("salary");
				
				System.out.println(id+","+name+","+gender+","+birthday+","+salary);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接失败", e);
		} finally {
			//5.关闭资源
			CreateJdbcDemo3.close(conn, statement, rs);
		}
	}
}
