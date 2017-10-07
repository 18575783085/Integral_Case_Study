package day03_c3p0pool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 利用PreparedStatement创建增删查该的方法
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
public class DaoPreparedStatementDemo1 {
	
	public static void main(String[] args) {
		Emp emp = new Emp(2, "李四", "男", Date.valueOf("2017-10-12"), 7777.9);
		insert(emp);
		insertAll();
		
		deleteById(2);
		deleteAll();
		
		updateById(emp);
		
		selectById(2);
		selectPageSize(1, 10);
		selectAll();
	}
	
	/**
	 *PreparedStatement的优缺点：
	 *	优点：
	 *		1.可以防止 SQL 注入攻击
	 *		2.采用预编译机制，效率高
	 *		3.如果发送的 SQL 语句主干部分相同，主干部分只需要写一次，每次发送的只是参数部分
	 *	
	 *	缺点：
	 *		1.包含的 SQL 语句的主干部分必须相同 
	 */
	
	
	
	/**
	 * 增加一条数据
	 */
	public static void insert(Emp emp){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			String insertSql = "insert into jdbcdemo"
					+ "(id,name,gender,birthday,salary)"
					+ "values"
					+ "(null,?,?,?,?)";
			ps = conn.prepareStatement(insertSql);
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getGender());
			ps.setDate(3, emp.getBirthday());
			ps.setDouble(4, emp.getSalary());
			
			int n = ps.executeUpdate();
			
			System.out.println("成功插入"+n+"条数据");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败",e);
		} finally {
			createC3p0poolDemo2.close(conn, ps, null);
		}
	}
	
	
	/**
	 * 批量增加多条数据
	 */
	public static void insertAll(){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			String insertSql = "insert into jdbcdemo"
					+ "(id,name,gender,birthday,salary)"
					+ "valeus"
					+ "(null,?,?,?,?)";
			ps = conn.prepareStatement(insertSql);
			
			for(int i=1;i<=1000;i++){
				ps.setString(1, "天蝎座"+i+"号");
				ps.setString(2, 1 == (int)(Math.random()*2) ? "男" : "女");
				ps.setDate(3, new Date(System.currentTimeMillis()));
				ps.setDouble(4, (int)(Math.random()*10000));
				ps.addBatch();
				
				if(i%500 == 0){
					ps.executeBatch();
					ps.clearBatch();
				}
			}
			ps.executeBatch();
			
			System.out.println("批量插入成功");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败",e);
		} finally {
			createC3p0poolDemo2.close(conn, ps, null);
		}
	}
	
	/**
	 * 防 SQL 注入
	 * SQL 注入攻击的原理：
	 * 	由于 jdbc 程序在执行的过程中 sql 语句在拼装时使用了由页面传入参数，如果用户恶意传入一些
	 * sql 中的特殊关键字，会导致 sql 语句意义发生变化，这种攻击方式就叫做 sql 注入。
	 */
	
	/**
	 * 根据id删除某一条数据
	 */
	public static void deleteById(int id){
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			String deleteSql = "delete from jdbcdemo where id=?";
			
			ps = conn.prepareStatement(deleteSql);
			ps.setInt(1, id);
			int n = ps.executeUpdate();
			
			if(n==0){
				System.out.println("删无此人");
			}else {
				System.out.println("成功删除"+n+"条数据");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败",e);
		} finally {
			createC3p0poolDemo2.close(conn, ps, null);
		}
	}
	
	/**
	 * 删除所有数据
	 */
	public static void deleteAll(){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			String deleteSql = "delete from jdbcdemo";
			
			ps = conn.prepareStatement(deleteSql);
			int n = ps.executeUpdate();
			
			System.out.println("成功删除"+n+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败",e);
		} finally {
			createC3p0poolDemo2.close(conn, ps, null);
		}
	}
	
	/**
	 * 根据id修改某一条数据
	 */
	public static void updateById(Emp emp){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			String updateSql = "update jdbcdemo "
					+ "set name=?,gender=?,birthday=?,salary=? "
					+ "where id=?";
			ps = conn.prepareStatement(updateSql);
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getGender());
			ps.setDate(3, emp.getBirthday());
			ps.setDouble(4, emp.getSalary());
			ps.setInt(5, emp.getId());
			
			int n = ps.executeUpdate();
			
			if(n==0){
				System.out.println("改无此人");
			}else{
				System.out.println("成功更新"+n+"条数据");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败",e);
		} finally {
			createC3p0poolDemo2.close(conn, ps, null);
		}
	}
	
	/**
	 * 根据id查询某一条数据
	 */
	public static Emp selectById(int id){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			String selectSql = "select * from jdbcdemo where id=?";
			
			ps = conn.prepareStatement(selectSql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(!rs.next()){
				System.out.println("查无此人");
			}else{
				Emp emp = new Emp(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), rs.getDate("birthday"), rs.getDouble("salary"));
				return emp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败", e);
		}finally {
			createC3p0poolDemo2.close(conn, ps, rs);
		}
		return null;
	}
	
	
	/**
	 * 分页查询
	 * Mysql: limit beign,pagesize
	 * beign:第n+1条
	 * pagesize：当前页所显示的条目数
	 */
	public static List<Emp> selectPageSize(int begin,int pagesize){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			String selectSql = "select * from jdbcdemo limit ?,?";
			
			ps = conn.prepareStatement(selectSql);
			
			ps.setInt(1, begin);
			ps.setInt(2, pagesize);
			
			rs = ps.executeQuery();
			
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
			createC3p0poolDemo2.close(conn, ps, rs);
		}
	}
	
	/**
	 * 查询所有数据
	 */
	public static List<Emp> selectAll(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = createC3p0poolDemo2.getConnection();
			
			String selectSql = "select * from jdbcdemo";
			
			ps = conn.prepareStatement(selectSql);
			
			rs = ps.executeQuery();
			
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
			createC3p0poolDemo2.close(conn, ps, rs);
		}
	}
}
