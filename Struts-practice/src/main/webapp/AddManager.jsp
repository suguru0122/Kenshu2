<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>マネージャー専用新規登録画面</title>
</head>
<body>

<center><h1>マネージャーのアカウントを追加します。</h1></center>
<s:form action="/Edit" method="post">
	<table border="1">
		<tr>
			<s:textfield key="adminid" label="管理者ID(※40文字まででお願いします)" maxlength="40" size="40" />
		</tr>
		<tr>
			<s:textfield key="adminpass" label="管理者PASSWORD(※40文字まででお願いします)" maxlength="40" size="40" />
		</tr>
</table>
<center><s:submit method="plusAddmanager" value="新規登録" theme="simple"/></center>
<s:reset align="center" value="リセット" />
<br>
<hr>
●<a href="/Struts-practice/TopPage.jsp">トップページへ</a>|
<h4>●<a href="JavaScript:history.back();">前のページに戻る</a></h4>
</s:form>
</body>
</html>