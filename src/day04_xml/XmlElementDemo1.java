package day04_xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * dom4j-----操作元素
 * @author Administrator
 *
 */
public class XmlElementDemo1 {
	
	public static void main(String[] args) {
		selectName1();
		selectName2();
		
		//addNode1();
		//addNode2();
		
		insertNode();
		
		deleteNode1();
		deleteNode2();
		
		updateNode();
	}
	/**
	 * 1.查询第一本书的书名，并输出到控制台
	 */
	public static void selectName1(){
		
		try {
			//1.创建sax对象
			SAXReader saxReader = new SAXReader();
			
			//2.创建dom对象
			Document document = saxReader.read("book1.xml");
			
			//3.获取根元素
			Element root = document.getRootElement();
			
			//4.获取子元素
			Element bookele1 = root.element("书");
			
			//5.获取子子元素
			Element nameele = bookele1.element("书名");
			Element autorele = bookele1.element("作者");
			Element priceele = bookele1.element("售价");
			
			//6.获取标签体
			String name = nameele.getText();
			String autor = autorele.getText();
			String price = priceele.getText();
			
			System.out.println(nameele.getName()+":"+name);
			System.out.println(autorele.getName()+":"+autor);
			System.out.println(priceele.getName()+":"+price);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 2.查询第二本书的售价，并输出到控制台
	 */
	public static void selectName2(){
		
		try {
			//1.创建sax对象
			SAXReader saxReader = new SAXReader();

			//2.创建dom对象
			Document document = saxReader.read("book1.xml");
			
			//3.获取根元素
			Element root = document.getRootElement();
			
			//4.获取子元素数组
			List<Element> elements = root.elements();
			
			//5.获取第二本书
			Element bookele2 = elements.get(1);
			
			//6.获取子子元素
			Element nameele2 = bookele2.element("书名");
			Element autorele2 = bookele2.element("作者");
			Element priceele2 = bookele2.element("售价");
			
			System.out.println(nameele2.getName()+"="+nameele2.getText());
			System.out.println(autorele2.getName()+"="+autorele2.getText());
			System.out.println(priceele2.getName()+"="+priceele2.getText());
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 3.给第一本书添加一个特价节点（2种方式）
	 * 方法一：直接通过子标签的方法来添加子子标签
	 */
	public static void addNode1(){
		try {
			//1.创建sax对象
			SAXReader saxReader = new SAXReader();
			//2.创建dom对象
			Document document = saxReader.read("book1.xml");
			//3.获取根元素
			Element root = document.getRootElement();
			//4.获取第一个子标签
			Element bookele1 = root.element("书");
			//5.添加一个子子标签：特价
			Element tejia = bookele1.addElement("特价");
			tejia.setText("998元");
			
			//6.创建xml对象
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File("book1.xml")), OutputFormat.createPrettyPrint());
			
			//7.写出对象
			xmlWriter.write(document);
			
			xmlWriter.close();
			
			System.out.println("添加成功");
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 方法二：通过创建游离节点来添加子子标签
	 */
	public static void addNode2(){
		
		try {
			//1.创建sax对象
			SAXReader saxReader = new SAXReader();
			//2.创建dom对象
			Document document = saxReader.read("book1.xml");
			//3.获取根元素
			Element root = document.getRootElement();
			//4.获取第一个子标签
			Element bookele1 = root.element("书");
			//5.创建游离节点
			Element bigjia = DocumentHelper.createElement("大特价");
			//6.添加游离节点
			bookele1.add(bigjia);
			bigjia.setText("668元");
			//6.创建xml对象
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File("book1.xml")), OutputFormat.createPrettyPrint());
			
			//7.写出对象
			xmlWriter.write(document);
			
			xmlWriter.close();
			
			System.out.println("添加成功");
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 4.给第二本书在作者节点前插入一个特价节点
	 */
	public static void insertNode(){
		
		try {
			//1.创建sax对象
			SAXReader saxReader = new SAXReader();
			//2.创建dom对象
			Document document = saxReader.read("book1.xml");
			//3.获取根元素
			Element root = document.getRootElement();
			
			//4.获取子标签集合
			List<Element> elements = root.elements();
			
			//5.获取第二本书
			Element bookele2 = elements.get(1);
			
			//6.获取子子标签集合
			List<Element> booklist = bookele2.elements();
			
			//7.创建游离节点:特价
			Element tejia = DocumentHelper.createElement("特价");
			booklist.add(1, tejia);
			tejia.addText("520元");
			
			//8.创建xml对象
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File("book1.xml")), OutputFormat.createPrettyPrint());
			
			//9.写出对象
			xmlWriter.write(document);
			
			xmlWriter.close();
			
			System.out.println("添加成功");
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 5.删除第二本书的特价节点（2种方式）
	 * 方法一：利用集合的移除方法来删除节点
	 */
	public static void deleteNode1(){
		
		try {
			//1.创建sax对象
			SAXReader saxReader = new SAXReader();
			//2.创建dom对象
			Document document = saxReader.read("book1.xml");
			//3.获取根元素
			Element root = document.getRootElement();
			
			//4.获取子标签集合
			List<Element> elements = root.elements();
			
			//5.获取第二本书
			Element bookele2 = elements.get(1);
			
			//6.获取子子标签集合
			List<Element> booklist = bookele2.elements();
			
			//7.删除节点：特价
			booklist.remove(1);
			
			
			//8.创建xml对象
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File("book1.xml")), OutputFormat.createPrettyPrint());
			
			//9.写出对象
			xmlWriter.write(document);
			
			xmlWriter.close();
			
			System.out.println("删除成功");
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 方法二：先通过子标签获取“特价”节点的位置，然后利用子标签的移除方法来删除该节点
	 */
	public static void deleteNode2(){
		
		try {
			//1.创建sax对象
			SAXReader saxReader = new SAXReader();
			//2.创建dom对象
			Document document = saxReader.read("book1.xml");
			//3.获取根元素
			Element root = document.getRootElement();
			
			//4.获取子标签集合
			List<Element> elements = root.elements();
			
			//5.获取第二本书
			Element bookele2 = elements.get(1);
			
			//6.删除节点
			Element tejia = bookele2.element("特价");
			bookele2.remove(tejia);
			
			//7.创建xml对象
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File("book1.xml")), OutputFormat.createPrettyPrint());
			
			//8.写出对象
			xmlWriter.write(document);
			
			xmlWriter.close();
			
			System.out.println("删除成功");
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 6.更新第一本书的特价节点的内容为19.8元
	 */
	public static void updateNode(){
		
		try {
			//1.创建sax对象
			SAXReader saxReader = new SAXReader();
			//2.创建dom对象
			Document document = saxReader.read("book1.xml");
			//3.获取根元素
			Element root = document.getRootElement();
			//4.获取第一个子标签
			Element bookele1 = root.element("书");
			
			//5.获取“特价”节点
			Element tejia = bookele1.element("特价");
			//6.修改标签体：19.8
			tejia.setText("19.8元");
			
			
			//7.创建xml对象
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File("book1.xml")), OutputFormat.createPrettyPrint());
			
			//8.写出对象
			xmlWriter.write(document);
			
			xmlWriter.close();
			
			System.out.println("修改成功");
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
