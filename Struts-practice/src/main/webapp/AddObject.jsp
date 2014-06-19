<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>マネージャー専用新規商品登録画面</title>
</head>
<body>
<center><h1>新しい商品をを追加します！</h1></center>
<s:form action="/AddObject" method="post">
	<table border="1">
		<tr>
			<s:textfield key="number" label="商品の番号" maxlength="50" size="40" />
		</tr>
		<tr>
			<s:textfield key="price" label="商品の価格" maxlength="30" size="40" />
		</tr>
		<tr>
			<s:textfield key="name" label="商品の名称" maxlength="60" size="40" />
		</tr>
		<tr>
			<s:textfield key="detail" label="商品の詳細・説明文など" maxlength="100" size="40" />
		</tr>
</table>
<center><s:submit method="plusAddObject" value="新規登録" theme="simple"/></center>
<s:reset align="center" value="リセット" />
<br>
<hr>
</s:form>
●<a href="/Struts-practice/TopPage.jsp">トップページへ</a>|
●<a href="JavaScript:history.back();">前のページに戻る</a>
</body>
</html>