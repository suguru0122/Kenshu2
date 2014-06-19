<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="action.OnlineAction"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome My OnlineShop!!</title>
</head>
<body>

<s:if test="%{#session.USER != null" >
<center><h1>( ﾟДﾟ)Welcome To My OnlineShop!( ﾟДﾟ)</h1></center>
	こんにちは、<s:property value="%{#session.USER}"/>さん♫
<s:form action="/AddToCart" method="post">
<table border="1">
<tr>
	<th>列</th>
	<th>商品番号</th>
	<th>価格</th>
	<th>商品名</th>
	<th>詳細画面</th>
	<th>カート追加</th>
</tr>
	<s:iterator value="blist">
		<tr>
			<td>No.<s:property value="objectid" /></td>
			<td><s:property value="objectnumber" /></td>
			<td><s:property value="objectprice" />円</td>
			<td><s:property value="objectname" /></td>
			<td><s:property value="objectdetail" /></td>
			
			<td>
				<s:set name="selected_id" value="%{objectnumber}" />
				<s:if test="%{#session.CART != null && !#session.CART.isEmpty() && #session.CART.contains(#selected_id)}" >
					<s:checkbox name="selecteditems" fieldValue="%{objectnumber}" theme="simple" value='true' />
				</s:if>
				<s:else>
					<s:checkbox name="selecteditems" fieldValue="%{objectnumber}" theme="simple" value='false' />
				</s:else>
			</td>
		</tr>
	</s:iterator>
</table>
<s:submit value="カートに追加" method="addTocart" theme="simple" />
	<s:submit value="カート中身を見る" method="addIncart" theme="simple" />
	<s:submit value="会計" method="checkout" theme="simple" />
</s:form>
</s:if>

<s:else>
<center><h1>管理者専用ページです♫(＾ω＾)</h1></center>
	何時もお疲れ様です、<s:property value="%{#session.MANAGER}"/>さん(＾ω＾)♫
<table border="1">
<tr>
	<th>列</th>
	<th>商品番号</th>
	<th>価格</th>
	<th>商品名</th>
	<th>詳細画面</th>
	<th>カート追加</th>
</tr>
	<s:iterator value="blist">
		<tr>
			<td>No.<s:property value="objectid" /></td>
			<td><s:property value="objectnumber" /></td>
			<td><s:property value="objectprice" />円</td>
			<td><s:property value="objectname" /></td>
			<td><s:property value="objectdetail" /></td>
			
			<td>
				<s:set name="selected_id" value="%{objectnumber}" />
				<s:if test="%{#session.CART != null && !#session.CART.isEmpty() && #session.CART.contains(#selected_id)}" >
					<s:checkbox name="selecteditems" fieldValue="%{objectnumber}" theme="simple" value='true' />
				</s:if>
				<s:else>
					<s:checkbox name="selecteditems" fieldValue="%{objectnumber}" theme="simple" value='false' />
				</s:else>
			</td>
		</tr>
	</s:iterator>
</table>
<s:form action="/AddManager.jsp" method="post" theme="simple">
	<s:submit value="管理者の追加"/>
</s:form>
<s:form action="/AddObject.jsp" method="post" theme="simple">
	<s:submit value="商品登録" />
</s:form>
</s:else>

</body>
</html>