package day04_xml;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 利用dom4j---操作属性
 * @author Administrator
 *
 */
public class XmlAttributeDemo1 {
	
	public static void main(String[] args) {
		addAttr1();
		addAttr2();
		
		updateAttr1();
		updateAttr2();
		updateAttr3();
		
		deleteAttr1();
		deleteAttr2();
		
		addAttr3();
		updateAttr4();
		deleteAttr3();
	}
	
	/**
	 * 1.给第一本书添加一个属性，如：出版社="清华大学出版社"(2种方式)
	 * 方法一：直接利用子标签的添加属性的方法来添加属性
	 */
	public static void addAttr1(){
		//1.创建解析器
		Document document = XmlTools.getDocument("book1.xml");
		
		//2.获取根元素
		Element root = document.getRootElement();
		
		//3.获取第一个子标签
		Element bookele1 = root.element("书");
		
		//4.添加属性
		bookele1.addAttribute("出版社", "清华大学出版社");
		
		//5.创建xml对象，并写出dom
		XmlTools.write(document, "book1.xml");
	}
	
	
	/**
	 * 方法二：先创建游离属性，然后利用子标签来添加该游离属性
	 */
	public static void addAttr2(){
		//1.创建解析器
		Document document = XmlTools.getDocument("book1.xml");
		
		//2.获取根元素
		Element root = document.getRootElement();
		
		//3.获取第一个子标签
		Element bookele1 = root.element("书");
		
		//4.创建游离属性：出版社
		Attribute attr1 = DocumentHelper.createAttribute(bookele1, "出版时间", "不告诉你");
		
		//5.添加属性
		bookele1.add(attr1);
		
		//6.创建xml对象，写出dom对象
		XmlTools.write(document, "book1.xml");
	}
	
	
	/**
	 * 2.在控制台上打印输出第一本书的出版社属性的值,并更新属性的值为“人民出版社”(3种方式)
	 * 方法一：利用子标签获取属性的方法，来通过属性的下标获取该属性的值
	 */
	public static void updateAttr1(){
		//1.创建解析器
		Document document = XmlTools.getDocument("book1.xml");
		
		//2.获取根元素
		Element root = document.getRootElement();
		
		//3.获取第一个子标签
		Element bookele1 = root.element("书");
		
		//4.通过下标获取属性的值
		Attribute attribute = bookele1.attribute(0);
		
		System.out.println(attribute.getName()+"="+attribute.getText());
		
		//5.更新属性的值
		attribute.setText("人民出版社");
		
		//6.创建xml对象
		XmlTools.write(document, "book1.xml");
	}
	/**
	 * 方法二：利用子标签获取属性的方法，来通过属性的名字获取该属性的值
	 */
	public static void updateAttr2(){
		//1.创建解析器
		Document document = XmlTools.getDocument("book1.xml");
		
		//2.获取根元素
		Element root = document.getRootElement();
		
		//3.获取第一个子标签
		Element bookele1 = root.element("书");
		
		//4.通过下标获取属性的值
		Attribute attribute = bookele1.attribute("出版社");
		
		System.out.println(attribute.getName()+"="+attribute.getText());
		
		//5.更新属性的值
		attribute.setText("北京大学出版社");
		
		//6.创建xml对象
		XmlTools.write(document, "book1.xml");
	}
	/**
	 * 方法三：利用子标签的属性方法来直接获取属性的值
	 */
	public static void updateAttr3(){
		Document document = XmlTools.getDocument("book1.xml");
		
		Element root = document.getRootElement();
		
		Element bookele1 = root.element("书");
		
		//直接获取属性的值
		String attributeValue = bookele1.attributeValue("出版社");
		
		System.out.println("出版社："+attributeValue);
	}
	/**
	 * 3.删除第一本书的出版社属性(2种方式)
	 * 方法一：先利用属性的下标/名字 来获取该属性，再利用子标签的移除方法来删除该属性
	 */
	public static void deleteAttr1(){
		Document document = XmlTools.getDocument("book1.xml");
		
		Element root = document.getRootElement();
		
		Element bookele1 = root.element("书");
		
		//利用属性的下标/名字 来获取该属性
		Attribute attribute = bookele1.attribute(0);
		//再利用子标签的移除方法来删除该属性
		bookele1.remove(attribute);
		
		XmlTools.write(document, "book1.xml");
	}
	/**
	 * 方法二：先获取属性的集合，然后利用集合的移除方法来删除该属性
	 */
	public static void deleteAttr2(){
		Document document = XmlTools.getDocument("book1.xml");
		
		Element root = document.getRootElement();
		
		Element bookele1 = root.element("书");
		
		//获取属性集合
		List<Attribute> attrlist = bookele1.attributes();
		//利用集合的移除方法来删除该属性
		attrlist.remove(0);
		
		XmlTools.write(document, "book1.xml");
	}
	/**
	 * 4.（作业）给第二本书添加一个属性，如：编号="b01"
	 */
	public static void addAttr3(){
		//1.创建解析器
		Document document = XmlTools.getDocument("book1.xml");
				
		//2.获取根元素
		Element root = document.getRootElement();
				
		//3.获取子标签集合
		List<Element> elements = root.elements();
		
		//4.获取第二个子标签
		Element bookele2 = elements.get(1);
		
		//5.添加属性
		bookele2.addAttribute("编号", "b01");
				
		//6.创建xml对象，并写出dom
		XmlTools.write(document, "book1.xml");
	}
	/**
	 * 5.（作业）在控制台上打印输出第二本书编号属性的值,并更新该属性的值
	 */
	public static void updateAttr4(){
		//1.创建解析器
		Document document = XmlTools.getDocument("book1.xml");
				
		//2.获取根元素
		Element root = document.getRootElement();
				
		//3.获取子标签集合
		List<Element> elements = root.elements();
		
		//4.获取第二个子标签
		Element bookele2 = elements.get(1);
		
		//5.利用属性的名字来获取属性的值
		Attribute attribute = bookele2.attribute("编号");
		
		System.out.println(attribute.getName()+"="+attribute.getText());
				
		attribute.setText("b02");
		//6.创建xml对象，并写出dom
		XmlTools.write(document, "book1.xml");
	}
	/**
	 * 6.（作业）删除第二本书的编号属性
	 */
	public static void deleteAttr3(){
		//1.创建解析器
		Document document = XmlTools.getDocument("book1.xml");
				
		//2.获取根元素
		Element root = document.getRootElement();
				
		//3.获取子标签集合
		List<Element> elements = root.elements();
		
		//4.获取第二个子标签
		Element bookele2 = elements.get(1);
		
		//5.获取属性
		Attribute attribute = bookele2.attribute("编号");
		//6.删除属性
		bookele2.remove(attribute);
				
		//7.创建xml对象，并写出dom
		XmlTools.write(document, "book1.xml");
	}
}
