<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>住所情報の検索</title>
</head>
<!-- DWRの動作に必要なJavaScriptライブラリをインポート -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="/Test/dwr/interface/Address.js"></script>
<script type="text/javascript" src="/Test/dwr/engine.js"></script>
<script type="text/javascript" src="/Test/dwr/util.js"></script>
<script type="text/javascript">
<!-- ページロード時の処理(通信メッセージの表示を有効化) -->
function init() {
	dwr.util.useLoadingMessage();
}
// [検索]ボタンをクリック時に実行
function getResult(){
	// 指定値でaddressテーブルを部分一致検索(その結果をページ下部に表示)
	//alert($('#name1').val());
	Address.getInfosByName($('#name').val(),
		function(result){
			var msg = '<table border="1">';
			msg += '<tr><th>名前</th><th>住所</th><th>TEL</th><th>E-MAIL</th></tr>';
			for(i = 0; i < result.length; i++){
				msg += '<tr>';
				msg += '<td>' + result[i].name + '</td>';
				msg += '<td>' + result[i].address + '</td>';
				msg += '<td>' + result[i].tel + '</td>';
				msg += '<td>' + result[i].email + '</td>';
				msg += '</tr>';
			}
		msg += '</table>';
		$('#result').html(msg);
	}
	);
}
</script>
<body onload="init()">
<form>
名前：
<input type="text" name="name" id="name" size="20" />
<input type="button" value="検索" onclick="getResult()" />
</form>
<div id="result"></div>
</body>
</html>