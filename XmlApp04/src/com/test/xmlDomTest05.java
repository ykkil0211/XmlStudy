/*=================================================
	xmlDomTest05.java
	- 콘솔 기반 자바 프로그램
	- XML, DOM 활용 → 로컬(local) XML 읽어내기
	  (VEHICLES.xml)
 ================================================*/
package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xmlDomTest05
{
	public static void main(String[] args)
	{
		try
		{
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			String url = "VEHICLES.xml";
			xmlObj = builder.parse(url);
			
			Element root = xmlObj.getDocumentElement();
			
			NodeList vehicleNodeList = root.getElementsByTagName("VEHICLE");
			
			//System.out.println(root.getNodeName());
			//System.out.println(vehicleNodeList.getLength());
			

			for (int i=0; i<vehicleNodeList.getLength(); i++) 
			{
				Node vehicleNode = vehicleNodeList.item(i);		
				
				Element vehicleElement = (Element)vehicleNode;
				System.out.println("-------------------------------------------------");
				System.out.println("NO NAME  MODEL  YEAR    STYLE      PRICE");
				System.out.println("-------------------------------------------------");
				System.out.printf("%s  %s  %s  %s  %s  %s%n"
								, getText(vehicleElement, "INVENTORY_NUMBER")
								, getText(vehicleElement, "MAKE")
								, getText(vehicleElement, "MODEL")
								, getText(vehicleElement, "YEAR")
								, getText(vehicleElement, "STYLE")
								, getText(vehicleElement, "PRICE"));
				
				NodeList optionNodeList = vehicleElement.getElementsByTagName("OPTIONS");
				System.out.println("option ----------------------------------------------");
				if (optionNodeList.getLength() > 0)
				{
					Node optionNode = optionNodeList.item(0);
					Element optionElement = (Element)optionNode;
					
					NodeList subNodeList = optionElement.getChildNodes();
					for (int j=0; j<subNodeList.getLength(); j++)
					{
						Node subNode = subNodeList.item(j);
						if(subNode.getNodeType() == 1)
						{
							Element subElement = (Element)subNode;
							System.out.printf("    %s : %s%n",subElement.getTagName() ,subElement.getTextContent());
						}
					}
					
				}
				
			}
			
			System.out.println("-------------------------------------------------");
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
	
	public static String getText(Element parent, String tagName)
	{
		String result = "";
		
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		
		result = element.getChildNodes().item(0).getNodeValue();
		
		
		return result;
		
	}

}
