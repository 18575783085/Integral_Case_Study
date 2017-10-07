package day02_jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class CreateJdbcDemo1 {
	
	public static void main(String[] args) {
		createConnection();
		createTable();
		insert();
		update();
		delete();
		select();
	}
	
	/**
	 * jdbc快速创建连接数据库案例一：
	 * 测试连接
	 */
	public static void createConnection(){
		Connection conn = null;
		try {
			//1.注册数据库驱动
			DriverManager.registerDriver(new Driver());
			//2.获取数据库连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db10", "root", "root");
			//3.测试连接
			System.out.println(conn);
			System.out.println("连接成功");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("注册驱动失败",e);
		} finally{
			//4.关闭资源
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
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
			//1.注册数据库驱动
			DriverManager.deregisterDriver(new Driver());
			
			//2.创建数据库连接
			conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/db10", "root", "root");
			
			//3.创建传输器
			statement = conn.createStatement();
			
			//4.创建sql语句
			String createtable = "create table jdbcdemo("
					+ "id number primary key auto_increment,"
					+ "name varchar(50) unique"
					+ "gender char(1) not null"
					+ "birthday date,"
					+ "salary double"
					+ ")";
			//5.利用传输器发送sql语句到数据库执行，返回执行的结果
			statement.executeUpdate(createtable);
			
			System.out.println("创建表成功");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("注册驱动失败", e);
		} finally{
			//6.关闭资源
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					statement = null;
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					conn = null;
				}
				
			}
		}
	}
	
	/**
	 * 插入数据:insert
	 */
	public static void insert(){
		Connection conn = null;
		Statement statement = null;
		
		try {
			//1.
			DriverManager.registerDriver(new Driver());
			
			//2.
			conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/db10", "root", "root");
			
			//3.
			statement = conn.createStatement();
			
			//4.插入一条数据
			String insertSql = "insert into jdbcdemo"
					+ "(id,name,gender,birthday,salary)"
					+ "values(null,'张三','男','2017-10-01',8888.8)";
			
			//5.
			int n = statement.executeUpdate(insertSql);
			
			System.out.println("成功插入"+n+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("注册驱动失败", e);
		} finally {
			//6.
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
				} finally{
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
			DriverManager.registerDriver(new Driver());
			
			conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/db10", "root", "root");
			
			statement = conn.createStatement();
			
			String deleteSql = "delete from jdbddemo where id=1";
			
			int n = statement.executeUpdate(deleteSql);
			
			System.out.println("成功删除"+n+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("注册驱动失败", e);
		} finally {
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
				} finally{
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
			DriverManager.registerDriver(new Driver());
			
			conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/db10", "root", "root");
			
			statement = conn.createStatement();
			
			String updateSql = "update jdbcdemo "
					+ "set name=李四,gender='女',birthday='2017-10-07',salary='888.88 "
					+ "where id=1'";
			
			int n = statement.executeUpdate(updateSql);
			
			System.out.println("成功更新"+n+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("注册驱动失败", e);
		} finally {
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
				} finally{
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
			//1.
			DriverManager.registerDriver(new Driver());
			//2.
			conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/db10", "root", "root");
			//3.
			statement = conn.createStatement();
			//4.
			String selectSql = "select * from jdbcdemo";
			
			//5.执行sql语句，返回结果集
			rs = statement.executeQuery(selectSql);
			
			//6.获取数据
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
			throw new RuntimeException("注册驱动失败", e);
		} finally {
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
				} finally{
					conn = null;
				}
			}
		}
	}

}


