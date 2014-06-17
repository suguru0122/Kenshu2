<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>Welcome My OnlineShop!!</title>
</head>
<body>

<h1>ようこそ！ログインのボタンを押すと商品一覧へ遷移します。</h1>
<table>
<s:form action="/Login" method="post" theme="simple">
	<s:submit value="ログイン"/>
</s:form>
</table>
</body>
</html>