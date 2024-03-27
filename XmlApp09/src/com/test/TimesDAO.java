package com.test;


import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class TimesDAO {
	
	private Document xmlObj;
	private XPath xPath;
	
	public TimesDAO() {
		
		this ("rss"); // 전체 화면 출력
		
	}

	public TimesDAO(String timesName) {
		
		
		try{
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			String str = String.format("https://www.koreatimes.co.kr/www/rss/%s.xml", timesName);
			
			
			URL url = new URL(str);
			InputSource is = new InputSource(url.openStream());
			
			xmlObj = builder.parse(is);
			xPath = XPathFactory.newInstance().newXPath();
			
			
			
		} catch (Exception e) {
			
			System.out.println(e.toString());
		}
	}
	
	// <title> Korea Times News </title>
	public String TimesTitle() throws XPathExpressionException {
		
		String result = "";
		
		result = xPath.compile("/rss/channel/title").evaluate(xmlObj);
		
		return result;
		
	}
	
	// <description> Whole News</description>
	public String TimesDescription() throws XPathExpressionException {
		
		String result = "";
		
		result = xPath.compile("/rss/channel/description").evaluate(xmlObj);
		
		return result;
		
	}
	
	
	// <title>![CDATA[ SK Innovation 2023 net profit down 71.2% to $409.8 mil. ]]</title>
	public ArrayList<TimesDTO> TimesTitleList() throws XPathExpressionException {
		
		ArrayList<TimesDTO> result = new ArrayList<TimesDTO>();
		
		NodeList timesNodeList = (NodeList)xPath.compile("/rss/channel/item")
				.evaluate(xmlObj, XPathConstants.NODESET);
		
		for (int i=1; i<=timesNodeList.getLength(); i++){
			
			String title = xPath.compile(String.format("/rss/channel/item[%s]/title", i)).evaluate(xmlObj);
			String description = xPath.compile(String.format("/rss/channel/item[%s]/description", i)).evaluate(xmlObj);
			
			String media = xPath.compile(String.format("/rss/channel/item[%s]/content/@url", i)).evaluate(xmlObj); 
			
			//Element itemElement = (Element)timesNodeList.item(i-1);
			//Element mediaElement = (Element)itemElement.getElementsByTagName("media:content").item(0);
			
			String author = xPath.compile(String.format("/rss/channel/item[%s]/author", i)).evaluate(xmlObj);
			String pubDate = xPath.compile(String.format("/rss/channel/item[%s]/pubDate", i)).evaluate(xmlObj);
			String link = xPath.compile(String.format("/rss/channel/item[%s]/link", i)).evaluate(xmlObj);
			String guid = xPath.compile(String.format("/rss/channel/item[%s]/guid", i)).evaluate(xmlObj);
			
			//String media = "";
			
			//if (mediaElement != null) {
				
			//	media = mediaElement.getAttribute("url");
				
			//}
			
			TimesDTO t = new TimesDTO();
			
			t.setTitle(title);
			t.setDescription(description);
			t.setMedia(media);
			t.setAuthor(author);
			t.setPubDate(pubDate);
			t.setLink(link);
			t.setGuid(guid);
			
			result.add(t);
			
		}
		
		return result;
		
	}
	
}
