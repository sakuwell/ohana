<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<h1>ログイン画面</h1>
	<form action="ExecuteLogin" method="post">
	<p>ユーザーID：<br>
	<input type="text" name="USERID" maxlength="20">
	</p>
	<p>パスワード：<br>
	<input type="password" name="PASS" maxlength="20">
	</p>
	<input type="submit" value="ログイン">
	</form>
</body>
</html>