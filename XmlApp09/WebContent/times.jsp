<%@page import="com.test.TimesDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.test.TimesDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<%
	String timesName = request.getParameter("timesName");

	if(timesName == null)
		timesName = "rss";
	
	StringBuffer sb = new StringBuffer();
	
	TimesDAO dao = new TimesDAO(timesName);
	
	// title
	String title = dao.TimesTitle();
	
	// description
	String description = dao.TimesDescription();
	
	// item/title
	ArrayList<TimesDTO> dto = dao.TimesTitleList();
		
	for (TimesDTO t : dto) {
		
		sb.append("<div class='row'>");
		sb.append("	<div class='left'>");
		sb.append(String.format("<img src='%s'>", t.getMedia()));
		sb.append("	</div>");
		sb.append("<div class='right'>");
		sb.append(String.format("<h3>%s</h3>", t.getTitle()));
		sb.append(String.format("<p>%s</p>", t.getDescription()));
		sb.append(String.format("<p><strong>Author : </strong>%s</p>",t.getAuthor()));
		sb.append(String.format("<p><strong>Pub Date:</strong>%s</p>",t.getPubDate()));
		sb.append(String.format("<p><strong>Link : </strong><a href='%s'>%s</a></p>",t.getLink(), t.getLink()));
		sb.append(String.format("<p><strong>Guid : </strong><a href='%s'>%s</a></p>",t.getGuid(), t.getGuid()));
		sb.append("	</div>  ");
		sb.append("</div>");
		
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>times.jsp</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		$("select[name='timesName'] option[value='<%=timesName%>']").prop("selected", true);
	    
	});
	
</script>
    <style>
    	.select {

            width: 80%;
            display: flex;
            margin: auto;
            margin-top: 20px;    	
    	
    	}
        .row{
            width: 80%;
            display: flex;
            border: 1px solid #003458;
            margin: auto;
            margin-top: 20px;
        }
        .left {
            width: 40%;
            float: left;
            box-sizing: border-box;
            margin: auto;
        }
        .right {
            width: 50%;
            float: right;
            margin: auto;
            box-sizing: border-box;
            padding-left: 20px;
        }
        img {
            width: 100%;
            height: auto;
        }
    </style>
</head>
<body>

<div>
    <h2 style="text-align: center;"><%=title%></h2>
    <h3 style="text-align: center;"><%=description %></h3>
</div>
<div class="select">
		<form method="get" role="form">

			<label>뉴스 선택하기</label> 
			<select name="timesName" id="timesName">
				<option value="rss">rss</option>
				<option value="northkorea">northkorea</option>
				<option value="entertainment">entertainment</option>
				<option value="opinion">opinion</option>
				<option value="nation">nation</option>
				<option value="biz">biz</option>
				<option value="tech">tech</option>
				<option value="arts">arts</option>
				<option value="sports">sports</option>
				<option value="world">world</option>
			</select>

			<button type="submit" class="btn btn-default">Submit</button>
		</form>
</div>
<%=sb.toString() %>



</body>
</html>