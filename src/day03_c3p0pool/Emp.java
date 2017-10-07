package day03_c3p0pool;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * 员工表
 * @author Administrator
 *
 */
public class Emp {
	private int id;
	private String name;
	private String gender;
	private Date birthday;
	private Double salary;
	
	public Emp(int id, String name, String gender, Date birthday, Double salary) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + ", gender=" + gender + ", birthday=" + birthday + ", salary="
				+ salary + "]";
	}
	
	
}
