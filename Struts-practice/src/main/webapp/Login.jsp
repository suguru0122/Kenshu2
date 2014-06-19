<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>フォーム認証</title>
</head>
<body>
<h1>ログインします。</h1>
<br>
<h3>♦♫♦･*:..｡♦♫♦IDとPASSに正しい値を入力し、サイト内で是非お買い物をお楽しみください♦♫♦･*:..｡♦♫♦</h3>
<s:form method="post" action="/LoginCheck">
	<table border="0">
		<tr>
			<s:textfield key="userid" label="ユーザー名" maxlength="40" size="40" />
		</tr>
		
		<tr>
			<s:password key="userpass" label="パスワード" maxlength="40" size="40" />
		</tr>
		
	</table>
		<s:submit method="checkLogin" value="ログイン" theme="simple" />
		<input type="reset" value="取り消し" />
		<hr>
		●<a href="/Struts-practice/TopPage.jsp">トップページへ</a>|
</s:form>
</body>
</html>