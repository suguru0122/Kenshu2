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
<s:form action="/Insert.jsp" method="post" theme="simple">
	いらっしゃいませ！お手数をお掛けしますが、<br>
	非会員の方はご登録をお願い致します！<br>
	(；´・ω・)⇒<s:submit value="新規登録"/>
</s:form>
<br>
<s:form action="/Login.jsp" method="post" theme="simple">
	既に会員の方はこちら<br>
	♫(✽＾ω＾)⇒<s:submit value="ログイン"/>
</s:form>
<br>
<s:form action="/Manager.jsp" method="post" theme="simple">
	管理者はこちら<br>
	(☆`・ω・´)ノ⇒<s:submit value="管理者専用ページ"/>
</s:form>
<br>
</body>
</html>