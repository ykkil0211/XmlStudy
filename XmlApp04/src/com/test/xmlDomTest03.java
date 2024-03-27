package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xmlDomTest03
{
	public static void main(String[] args)
	{
		
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			String url = "memList.xml";
			xmlObj = builder.parse(url);
			
			Element root = xmlObj.getDocumentElement();
			
			//System.out.println(root.getNodeName());
			//-- memList
			
			NodeList memNodeList = root.getElementsByTagName("memberInfo");
			
			//System.out.println(memNodeList.getLength());
			//-- 5
			
			for (int i=0; i<memNodeList.getLength(); i++)
			{
				Node memInfoNode = memNodeList.item(i);
				
				Element memInfoElement = (Element)memInfoNode;

				
				System.out.printf("%s %n"
								, getText(memInfoElement, "name")
								);
				
				if (memInfoElement.getElementsByTagName("telephone").getLength() > 0)
				{
					System.out.printf("%s %n", getText(memInfoElement, "telephone"));
					
				}
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	} // end main()
	
	public static String getText(Element parent, String tagName)
	{
		
		String result = "";
		
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		
		result = element.getChildNodes().item(0).getNodeValue();		
		
		return result;
		
	}
	
	
}
