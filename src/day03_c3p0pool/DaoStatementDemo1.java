package day03_c3p0pool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 利用statement创建增删查该的方法
 * 用c3p0数据库连接池方法（带配置文件）
 * @author Administrator
 * 
 * 数据库环境
 * 数据库：db10
 * 表：jdbcdemo
 * 字段：id,name,gender,birthday,salary
 * 对象类：Emp
 *
 */
public class DaoStatementDemo1 {
	
	public static void main(String[] args) {
		Emp emp = new Emp(1, "张三", "男", Date.valueOf("2017-10-11"), 9999.9);
		insert(emp);
		insertBatch();
		
		deleteById(1);
		deleteAll();
		
		updateById(emp);
		
		selectById(1);
		selectPageSize(1, 10);
		selectAll();
		
	}
	
	/**
	 * statement的优缺点：
	 * 优点：可以包含结构不同的 SQL 语句
	 * 缺点:
	 * 	1.不能防止 SQL 注入攻击
	 * 	2.不能预编译机制，效率低下
	 * 	3.如果发送的 SQL 语句主干部分相同，主干部分每次都需要写
	 */
	
	/**
	 * 增加一条数据
	 */
	public static void insert(Emp emp){
		Connection conn = null;
		Statement statement = null;
		try {
			//1.获取数据库连接
			conn = createC3p0poolDemo2.getConnection();
			
			//2.创建传输器
			statement = conn.createStatement();
			
			//3.创建sql语句
			String insertSql = "insert into jdbcdemo"
					+ "(id,name,gender,birthday,salary)"
					+ "values"
					+ "(null,'"+emp.getName()+"','"+emp.getGender()+"','"+emp.getBirthday()+"',"+emp.getSalary()+")";
			
			//4.执行Sql语句
			int n = statement.executeUpdate(insertSql);
			
			System.out.println("成功插入"+n+"条数据");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败", e);
		} finally {
			//5.关闭资源
			createC3p0poolDemo2.close(conn, statement, null);
		}
	}
	
	
	/**
	 * 批量增加数据
	 */
	public static void insertBatch(){
		Connection conn = null;
		Statement statement = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			statement = conn.createStatement();
			
			for(int i=1;i<=1000;i++){
				String gender = (1 == (int)(Math.random()*2) ? "男" : "女");
				
				double salary = (int)(Math.random()*10000);
				
				String insertAll = "insert into jdbcdemo"
						+ "(id,name,gender,birthday,salary)"
						+ "values"
						+ "(null,'处女座"+i+"号','"+gender+"','2017-10-"+(int)(Math.random()*29+1)+"',"+salary+")";
				statement.addBatch(insertAll);
				
				if(i%500 ==0){
					statement.executeBatch();
					statement.clearBatch();
				}
			}
			statement.executeBatch();
			
			System.out.println("批量插入完毕");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败", e);
		} finally {
			createC3p0poolDemo2.close(conn, statement, null);
		}
	}
	
	
	/**
	 * SQL注入问题
	 */
	
	
	
	
	/**
	 * 根据id删除某一条数据
	 */
	public static void deleteById(int id){
		Connection conn = null;
		Statement statement = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			statement = conn.createStatement();
			
			String deleteSql = "delete from jdbcdemo where id="+id;
			
			int n = statement.executeUpdate(deleteSql);
			
			if(n==0){
				System.out.println("删无此人");
				return;
			}else {
				System.out.println("成功删除"+n+"条数据");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败", e);
		} finally {
			createC3p0poolDemo2.close(conn, statement, null);
		}
	}
	
	
	/**
	 * 删除所有数据
	 */
	public static void deleteAll(){
		Connection conn = null;
		Statement statement = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			statement = conn.createStatement();
			
			String deleteAll = "delete from jdbcdemo";
			
			int n = statement.executeUpdate(deleteAll);
			
			System.out.println("成功删除"+n+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败", e);
		} finally {
			createC3p0poolDemo2.close(conn, statement, null);
		}
	}
	/**
	 * 根据id更新某一条数据
	 */
	public static void updateById(Emp emp){
		Connection conn = null;
		Statement statement = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			statement = conn.createStatement();
			
			String updateSql = "update jdbcdemo "
					+ "set name='"+emp.getName()+"',gender='"+emp.getGender()+"',birthday='"+emp.getBirthday()+"',salary="+emp.getSalary()+" "
					+ "where id="+emp.getId();
			
			int n = statement.executeUpdate(updateSql);
			
			if(n==0){
				System.out.println("改无此人");
				return;
			}else {
				System.out.println("成功更新"+n+"条数据");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败", e);
		} finally {
			createC3p0poolDemo2.close(conn, statement, null);
		}
	}
	/**
	 * 根据id查询某一条数据
	 */
	public static Emp selectById(int id){
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			statement = conn.createStatement();
			
			String selectSql = "select * from jdbcdemo where id="+id;
			
			rs = statement.executeQuery(selectSql);
			
			if(!rs.next()){
				System.out.println("查无此人");
			}else {
				Emp emp = new Emp(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), rs.getDate("birthday"), rs.getDouble("salary"));
				return emp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败", e);
		} finally {
			createC3p0poolDemo2.close(conn, statement, rs);
		}
		return null;
	}
	/**
	 * 分页查询：mysql：limit函数
	 * begin:从第几+1条开始查询
	 * pagesize:一页显示多少条数据
	 */
	public static List<Emp> selectPageSize(int begin,int pagesize){
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			statement = conn.createStatement();
			
			String selectSql = "select * from jdbcdemo limit "+begin+","+pagesize;
			
			rs = statement.executeQuery(selectSql);
			
			List<Emp> emplist = new ArrayList<Emp>();
			while(rs.next()){
				Emp emp = new Emp(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), rs.getDate("birthday"), rs.getDouble("salary"));
				emplist.add(emp);
			}
			return emplist;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败", e);
		} finally {
			createC3p0poolDemo2.close(conn, statement, rs);
		}
	}
	
	
	/**
	 * 查询所有数据
	 */
	public static List<Emp> selectAll(){
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			statement = conn.createStatement();
			
			String selectSql = "select * from jdbcdemo";
			
			rs = statement.executeQuery(selectSql);
			
			List<Emp> emplist = new ArrayList<Emp>();
			while(rs.next()){
				Emp emp = new Emp(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), rs.getDate("birthday"), rs.getDouble("salary"));
				emplist.add(emp);
			}
			return emplist;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败",e);
		} finally {
			createC3p0poolDemo2.close(conn, statement, rs);
		}
	}
	
	

}
