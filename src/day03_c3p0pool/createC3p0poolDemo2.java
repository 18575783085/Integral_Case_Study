package day03_c3p0pool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 创建c3p0实例，直接读取c3p0-config.xml/c3p0.properties配置文件
 * @author Administrator
 *
 */
public class createC3p0poolDemo2 {
	
	public static void main(String[] args) {
		createConnection();
		insert();
		update();
		delete();
		select();
	}
	
	
	/**
	 * 创建c3p0连接池对象
	 */
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	
	/**
	 * 创建获取数据库连接池
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return cpds.getConnection();
	}
	
	/**
	 * 归还数据库连接
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
				throw new RuntimeException("归还连接失败", e);
			}
		}
	}
	
	/**
	 * 测试连接
	 */
	public static void createConnection(){
		Connection conn = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			System.out.println(conn);
			System.out.println("连接成功");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败", e);
		} finally {
			createC3p0poolDemo2.close(conn, null, null);
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
			conn = createC3p0poolDemo2.getConnection();
			
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
			createC3p0poolDemo2.close(conn, statement, null);
		}
	}
	
	
	/**
	 * 插入数据：insert
	 */
	public static void insert(){
		Connection conn = null;
		Statement statement = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			statement = conn.createStatement();
			
			String insertSql = "insert into jdbcdemo"
					+ "(id,name,gender,birthday,salary)"
					+ "values"
					+ "(null,'刘备','男','2017-10-02',789.798)";
			
			int n = statement.executeUpdate(insertSql);
			
			System.out.println("成功插入"+n+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败", e);
		} finally {
			createC3p0poolDemo2.close(conn, statement, null);
		}
	}
	
	
	/**
	 * 更新数据：update
	 */
	public static void update(){
		Connection conn = null;
		Statement statement = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			statement = conn.createStatement();
			
			String updateSql = "update jdbcdemo "
					+ "set name='黄月英',gender='女',birthday='2017-10-04',salary=987.987 "
					+ "where id=1";
			
			int n = statement.executeUpdate(updateSql);
			
			System.out.println("成功更新"+n+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败", e);
		} finally {
			createC3p0poolDemo2.close(conn, statement, null);
		}
	}
	/**
	 * 删除数据：delete
	 */
	public static void delete(){
		Connection conn = null;
		Statement statement = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			statement = conn.createStatement();
			
			String deleteSql = "delete from jdbcdemo where id=1";
			
			int n = statement.executeUpdate(deleteSql);
			
			System.out.println("成功删除"+n+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败", e);
		} finally {
			createC3p0poolDemo2.close(conn, statement, null);
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
			conn = createC3p0poolDemo2.getConnection();
			
			statement = conn.createStatement();
			
			String selectSql = "select * from jdbcdemo";
			
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
			throw new RuntimeException("获取连接失败", e);
		} finally {
			createC3p0poolDemo2.close(conn, statement, null);
		}
	}
}

