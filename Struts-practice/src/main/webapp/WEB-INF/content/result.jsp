<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false"   --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>result.jsp</title>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>
あなたは、<br>
氏名：<%=request.getParameter("name") %><br>
<%
	String sex_String;
if("male".equals(request.getParameter("sex"))){
	sex_String = "男性";
}else{
	sex_String = "女性";
}

int age = Integer.parseInt(request.getParameter("age"));
String drink;
if(age >= 20){
	drink = "あなたはお酒が飲めますね。";
}else{
	drink = "あなたはお酒を飲んではいけません。";
}

%>
性別：<%= sex_String %><br>
年齢：<%= request.getParameter("age") %>歳<br>

<br>
こんにちは、<%= (String)request.getAttribute("new_name") %>さん<br>

<br>

<%= drink %><br>

<a href="/Struts-practice/index.jsp">トップへ戻る。</a>
</body>
</html>