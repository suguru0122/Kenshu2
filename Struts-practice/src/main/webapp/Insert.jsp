<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規登録画面</title>
</head>
<body>
<s:form action="/Insert" method="post">
	<table border="1">
		<tr>
			<s:textfield key="userid" label="お好きなID(※40文字まででお願いします)" maxlength="40" size="40" />
		</tr>
		<tr>
			<s:textfield key="userpass" label="お好きなPASSWORD(※40文字まででお願いします)" maxlength="40" size="40" />
		</tr>
</table>
<br>
	<s:submit method="returnAdd" value="新規登録" theme="simple"/>
	<s:reset align="left" value="リセット" />
	<hr>
	●<a href="/Struts-practice/TopPage.jsp">トップページへ</a>|
</s:form>
</body>
</html>