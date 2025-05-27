<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>勤怠管理アプリ</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<h1 class="text-center mt-4 mb-4">勤怠管理アプリ</h1>

<div class="form-container-c" >
    <button class="btn btn-l mb-4" onclick="location.href='CompanyLoginServlet'">タイムカード機能</button>
    <button class="btn btn-l mb-4" onclick="location.href='AdminLoginServlet'">管理者ページ</button>
    <button class="btn btn-l mb-4" onclick="location.href='AccountCreate'">新規アカウント作成</button>
</div>
</body>
</html>