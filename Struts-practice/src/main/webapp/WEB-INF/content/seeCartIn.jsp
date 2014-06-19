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
<title>お客様のカートの中身</title>
</head>
<body>
<center><h3>お客様が選ばれた商品はこちらです。</h3></center>
<center>
<table border="1">
<tr>
	<th>商品番号</th>
	<th>商品の価格</th>
	<th>商品名</th>
	<th>商品の詳細</th>
	<th>カートに入れた時間</th>
</tr>
<s:iterator value="cartlist">
	<tr>
		<td>No.<s:property value="cartnum" /></td>
		<td><s:property value="cartprice" />円</td>
		<td><s:property value="cartname" /></td>
		<td><s:property value="cartdetail" /></td>
		<td><s:property value="orderday" /></td>
	</tr>
</s:iterator>
</table>
</center>
<br>
<br>
<center>
<h4>●<a href="JavaScript:history.back();">商品リストに戻る</a></h4>
</center>
</body>
</html>