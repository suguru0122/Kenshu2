<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者認証画面</title>
</head>
<body>
<h1>管理者専用画面へ遷移します。</h1>
<br>
<h3>認証の為に、IDとPASSWORDの入力をお願いします。</h3>
<s:form method="post" action="/LoginCheck">
	<table border="0">
		<tr>
			<s:textfield key="managerid" label="管理者ID" maxlength="40" size="40" />
		</tr>
		
		<tr>
			<s:password key="managerpass" label="管理者パスワード" maxlength="40" size="40" />
		</tr>
		
	</table>
		<s:submit method="managerLogin" value="ログイン" theme="simple" />
		<input type="reset" value="取り消し" />
		<hr>
		●<a href="/Struts-practice/TopPage.jsp">トップページへ</a>|
</s:form>
</body>
</html>