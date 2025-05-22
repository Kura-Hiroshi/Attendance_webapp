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
<h1 class="text-center">勤怠管理アプリ</h1>

<div class="block-center large-block" >
    <button class="genbtn innerblk" onclick="location.href='CompanyLoginServlet'">勤怠システム開始</button>
    <button class="genbtn innerblk" onclick="location.href='AdminLoginServlet'">管理者入口</button>
    <button class="genbtn innerblk" onclick="location.href=''">新規事業所登録</button>
</div>
</body>
</html>