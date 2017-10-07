package day04_xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * xml 的工具类
 * @author Administrator
 *
 */
public class XmlTools {
	
	private XmlTools(){}
	
	/**
	 * 创建解析对象：sax对象 && dom对象
	 */
	public static Document getDocument(String filename){
		
		try {
			//1.创建sax对象
			SAXReader saxReader = new SAXReader();
			
			//2.创建dom对象
			Document document = saxReader.read(filename);
			
			return document;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 创建xml对象,写出dom对象到配置文件中
	 */
	public static void write(Document document,String filename){
		try {
			//1.创建xml对象
			XMLWriter xmlWriter = new XMLWriter(
					new FileOutputStream(
							new File(filename)), OutputFormat.createPrettyPrint());
			
			//2.写出dom对象
			xmlWriter.write(document);
			
			//3.关闭资源
			xmlWriter.close();
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
