<%@page import="DAO.OnlineDataDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="action.OnlineAction"%>
<%@ page import="DAO.OnlineField"%>
<%@ page import="DAO.OnlineDataDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会計画面</title>
</head>
<body>

<center>
<h2>購入商品</h2>
</center>

<br><br>
以下が購入する商品と合計です。
<br>

<table border="1">
<%
List<String> cart = (List<String>)session.getAttribute("CART");
String[] lcb =  (String[])cart.toArray(new String[0]);
String sqlcart = "";
for(String ISBN : lcb){
	sqlcart += "'" + ISBN + "'" + ",";
}
if(lcb != null){
	for(String iterListid : lcb){
		OnlineField of = (OnlineField)OnlineDataDAO.findListid(iterListid); 
%>
<tr>
	<td><%= of.getObjectnumber() %></td>
	<td><%= of.getPrice() %></td>
	<td><%= of.getObjectname() %></td>
	<td><%= of.getDetail() %></td>
</tr>
<%
	}
}

%>
</table>

<br>
<br>
合計：<%= request.getAttribute("TOTAL") %>円
</body>
</html>