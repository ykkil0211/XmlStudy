/*=================================================
	xmlDomTest08.java
	- 콘솔 기반 자바 프로그램
	- XML, DOM 활용 → 원격(remote) XML 읽어내기
	  (https://fs.jtbc.co.kr/RSS/newsflash.xml)
	  ※ 언론사부터 얻어낸 데이터
 ================================================*/

/*
	title> JTBC News
	Link> https://fs.jtbc.co.kr/RSS/newsflash.xml
	description> 속보 RSS
	copyright>Copyright(C) JTBC All rights reserved
	
	주요 기사 --------------------------------------------
	title>2월 2일 (금) 아침& 다시보기
	description> 시청자 여러분 안녕하십니까, 2월 2일 JTBC 아침& 시작합니다. 먼저 오늘 아침 주요뉴스입니다.
	link>https://news.jtbc.co.kr/article/article.aspx?news_id=NB12163608
	pubDate>2024.02.02</pubDate>
	
	title>외식비 급등에 인기 '쑥'…냉동치킨 '영양성분' 꼭 확인하세요</title>
	description> [앵커]프랜차이즈 치킨값이 2만 원을 훌쩍 넘는 요즘, 대체품으로 마트의 냉동 치킨을 찾는 경우가 늘어났습니다. 그런데 잘 살펴보셔야 합니다. 인기 제품들 가운데 포화지방과 나트륨이 과하게 들어 있거나, 원산
	pubDate>2024.02.02</pubDate>
*/
package com.test;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlDomTest08
{
	public static void main(String[] args)
	{
		
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			String str = "https://fs.jtbc.co.kr/RSS/newsflash.xml";
			
			URL url = new URL(str);
			
			InputSource is = new InputSource(url.openStream());
			xmlObj = builder.parse(is);
			
			Element root = xmlObj.getDocumentElement();
			
			System.out.printf("%s%n %s%n %s%n %s%n"
										, XMLDOM.getText(root, "title")
										, XMLDOM.getText(root, "link")
										, XMLDOM.getText(root, "description")
										, XMLDOM.getText(root, "copyright"));
			
			NodeList itemNodeList = root.getElementsByTagName("item");
			
			for(int i=0; i<itemNodeList.getLength(); i++)
			{
				
				Node itemNode = root.getElementsByTagName("item").item(i);
				Element itemElement = (Element)itemNode;
				
				System.out.println("주요 기사----------------------------------------------------");
				
				System.out.printf("%s%n %s%n %s%n %s%n"
						, XMLDOM.getText(itemElement, "title")
						, XMLDOM.getText(itemElement, "description")
						, XMLDOM.getText(itemElement, "link")
						, XMLDOM.getText(itemElement, "pubDate"));
			}
			
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
	}

}
