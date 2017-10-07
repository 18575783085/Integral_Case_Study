package day03_c3p0pool;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class createC3p0poolDemo1 {
	
	public static void main(String[] args) {
		createConnection();
	}
	
	private static ComboPooledDataSource cpds;
	/**
	 * 利用c3p0---设置数据库连接参数
	 */
	static{
		//1.创建连接池对象
		cpds = new ComboPooledDataSource();
		
		try {
			//2.设置数据库参数
			cpds.setDriverClass("com.mysql.jdbc.Driver");
			cpds.setJdbcUrl("jdbc:mysql://localhost:3306/db10");
			cpds.setUser("root");
			cpds.setPassword("root");
			
			
		} catch (PropertyVetoException e) {
			e.printStackTrace();
			throw new RuntimeException("注册数据库驱动失败", e);
		}
	}
	
	/**
	 * 创建数据库连接池连接
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return cpds.getConnection();
	}
	
	/**
	 * 关闭资源，归还数据库连接
	 */
	public static void close(Connection conn,Statement statement,ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("归还连接失败", e);
			}
		}
	}
	
	
	/**
	 * 测试数据库连接
	 */
	public static void createConnection(){
		Connection conn = null;
		try {
			conn = createC3p0poolDemo1.getConnection();
			
			System.out.println(conn);
			System.out.println("连接成功");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接失败", e);
		} finally {
			createC3p0poolDemo1.close(conn, null, null);
		}
	}
}
