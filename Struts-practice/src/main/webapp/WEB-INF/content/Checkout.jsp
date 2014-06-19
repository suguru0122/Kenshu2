<%@page import="DAO.OnlineDataDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="action.OnlineAction"%>
<%@ page import="DAO.OnlineField"%>
<%@ page import="DAO.OnlineDataDAO"%>
<%@ page import="DAO.SqlDAO"%>
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
<h2>(m´・ω・｀)m ☆購入商品☆ m(´・ω・｀m)</h2>
</center>

<br><br>
<center><h2>以下が購入する商品と合計です。</h2></center>
<br>
<center>
<table border="1">

<tr>
	<th>商品番号</th>
	<th>価格</th>
	<th>商品名</th>
	<th>詳細</th>
</tr>
<%
List<String> cart = (List<String>)session.getAttribute("CART");
String[] lcb =  (String[])cart.toArray(new String[0]);

if(lcb != null){
	OnlineDataDAO o = new OnlineDataDAO();
	o.getConnection();
	for(String iterListid : lcb){
		OnlineField of = (OnlineField)OnlineDataDAO.findListid(iterListid); 
%>
<tr>
	<td><%= of.getObjectnumber() %></td>
	<td><%= of.getObjectprice() %>円</td>
	<td><%= of.getObjectname() %></td>
	<td><%= of.getObjectdetail() %></td>
</tr>
<%
	}
}

%>
</table>
</center>
<br>
<br>
<center><h3>合計：<%= request.getAttribute("TOTAL") %>円</h3></center>
<center><h4>●<a href="/Struts-practice/Login">商品リストに戻る</a></h4></center>
</body>
</html>