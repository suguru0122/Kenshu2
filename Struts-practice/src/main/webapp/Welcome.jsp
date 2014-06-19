<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>Welcome My OnlineShop!!</title>
</head>
<body>

<h1>ようこそ！Enterのボタンを押すと商品一覧へ遷移します。</h1>
<table>
<s:form action="/未定" method="post" theme="simple">
	<s:submit value="Enter"/>
</s:form>
</table>
<hr>
●<a href="/Struts-practice/TopPage.jsp">トップページへ</a>|
</body>
</html>