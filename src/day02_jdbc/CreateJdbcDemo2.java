package day02_jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateJdbcDemo2 {

	public static void main(String[] args) {
		createConnection();
		createTable();
		insert();
		delete();
		update();
		select();
		
	}
	/**
	 * 连接数据库的参数：url，user，password
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/db10", "root", "root");
	}
	
	
	/**
	 * jdbc快速创建连接数据库案例二：
	 * 测试连接
	 */
	public static void createConnection(){
		Connection conn = null;
		
		try {
			//1.注册数据库驱动
			/*
			 * 注：DriverManager.registerDriver(new Driver());
			 * 这种方式会导致：1.驱动注册两次
			 * 			 2.程序和具体的数据库驱动绑死在一起
			 * 
			 * 
			 * 通过.forname方法加载driver类，驱动只会注册一次
			 * 在forname方法中传入的是 driver 类的全限定名称字符串，
			 * 即和程序绑死仅仅是一个字符串，后期可以提取到配置文件中，
			 * 所以也不会和程序绑死！！
			 */
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.获取数据库连接
			conn = CreateJdbcDemo2.getConnection();
			
			//3.测试连接
			System.out.println(conn);
			System.out.println("连接成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("注册驱动失败", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接失败", e);
		} finally {
			//4.关闭资源
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
	}
	
	/**
	 * 创建表：jdbcdemo
	 * id,name,gender,birthday,salary
	 */
	public static void createTable(){
		Connection conn = null;
		Statement statement = null;
		try {
			// 1.注册数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 2.创建数据库连接
			conn = CreateJdbcDemo2.getConnection();
			// 3.创建传输器
			statement = conn.createStatement();

			// 4.创建sql语句
			String createtable = "create table jdbcdemo(" + "id number primary key auto_increment,"
					+ "name varchar(50) unique" 
					+ "gender char(1) not null" 
					+ "birthday date," 
					+ "salary double" + ")";
			// 5.利用传输器发送sql语句到数据库执行，返回执行的结果
			statement.executeUpdate(createtable);

			System.out.println("创建表成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("注册驱动失败", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接失败", e);
		} finally {
			//6.关闭资源
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
	}
	
	/**
	 * 插入数据：insert
	 */
	public static void insert(){
		Connection conn = null;
		Statement statement = null;
		try {
			// 1.注册数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 2.创建数据库连接
			conn = CreateJdbcDemo2.getConnection();
			// 3.创建传输器
			statement = conn.createStatement();

			// 4.创建sql语句
			String insertSql = "insert into jdbcdemo"
					+ "(id,name,gender,birthday,salary)"
					+ "values"
					+ "(null,'王五','男','2017-10-02',7777.7)";
					
			
			// 5.利用传输器发送sql语句到数据库执行，返回执行的结果
			int n = statement.executeUpdate(insertSql);

			System.out.println("成功插入"+n+"条数据");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("注册驱动失败", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接失败", e);
		} finally {
			//6.关闭资源
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
	}
	
	/**
	 * 删除数据：delete
	 */
	public static void delete(){
		Connection conn = null;
		Statement statement = null;
		try {
			// 1.注册数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 2.创建数据库连接
			conn = CreateJdbcDemo2.getConnection();
			// 3.创建传输器
			statement = conn.createStatement();

			// 4.创建sql语句
			String deleteSql = "delete from jdbcdemo where id=2";
					
			
			// 5.利用传输器发送sql语句到数据库执行，返回执行的结果
			int n = statement.executeUpdate(deleteSql);

			System.out.println("成功删除"+n+"条数据");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("注册驱动失败", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接失败", e);
		} finally {
			//6.关闭资源
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
	}
	
	/**
	 * 更新数据：update
	 */
	public static void update(){
		Connection conn = null;
		Statement statement = null;
		try {
			// 1.注册数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 2.创建数据库连接
			conn = CreateJdbcDemo2.getConnection();
			// 3.创建传输器
			statement = conn.createStatement();

			// 4.创建sql语句
			String updateSql = "update jdbcdemo "
					+ "set name='赵六',gender='女',birthday='2017-10-08',salary=777.77 "
					+ "where id=2";
					
			
			// 5.利用传输器发送sql语句到数据库执行，返回执行的结果
			int n = statement.executeUpdate(updateSql);

			System.out.println("成功更新"+n+"条数据");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("注册驱动失败", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接失败", e);
		} finally {
			//6.关闭资源
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
	}
	
	/**
	 * 查询数据：select
	 */
	public static void select(){
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			// 1.注册数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 2.创建数据库连接
			conn = CreateJdbcDemo2.getConnection();
			// 3.创建传输器
			statement = conn.createStatement();

			// 4.创建sql语句
			String selectSql = "select * from jdbcdemo";
					
			
			// 5.利用传输器发送sql语句到数据库执行，返回执行的结果
			rs = statement.executeQuery(selectSql);
			
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				Double salary = rs.getDouble("salary");
				
				System.out.println(id+","+name+","+gender+","+birthday+","+salary);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("注册驱动失败", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接失败", e);
		} finally {
			//6.关闭资源
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
	}
}
