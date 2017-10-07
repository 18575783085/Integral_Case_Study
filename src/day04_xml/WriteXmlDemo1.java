package day04_xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * 利用dom4j操作节点写出 xml 配置文件
 * @author Administrator
 *
 */
public class WriteXmlDemo1 {
	
	public static void main(String[] args) {
		writeXml();
	}
	
	public static void writeXml(){
		
		try {
			//1.创建Dom对象
			Document document = DocumentHelper.createDocument();
			
			//2.添加根元素
			Element root = document.addElement("书架");
			
			//3.添加两个子元素
			Element bookele1 = root.addElement("书");
			Element bookele2 = root.addElement("书");
			
			//4.添加子子元素
			//第一本书
			Element nameele = bookele1.addElement("书名");
			nameele.addText("数据结构");
			
			Element autorele = bookele1.addElement("作者");
			autorele.addText("严蔚敏");
			
			Element priceele = bookele1.addElement("售价");
			priceele.addText("29.00元");
					
			//第二本书
			Element nameele2 = bookele2.addElement("书名");
			nameele2.addText("高等数学");
			
			Element autorele2 = bookele2.addElement("作者");
			autorele2.addText("同济大学数学系");
			
			Element priceele2 = bookele2.addElement("售价");
			priceele2.addText("55.00元");
			
			//5.创建xml对象
			FileOutputStream fos = new FileOutputStream(new File("book1.xml"));
			XMLWriter xmlWriter = new XMLWriter(fos, OutputFormat.createPrettyPrint());
			
			xmlWriter.write(document);
			
			xmlWriter.close();
			
			System.out.println("写出完毕");
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
