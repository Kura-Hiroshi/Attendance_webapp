<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>勤怠入力システム</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <h1 class="text-center">勤怠入力システム</h1>
    <p class="text-center">記録したいものを選んでください</p>
    <form class="flex block-center large-block" action="SelectTypeServlet" method="post">    
        <button class="genbtn" type="submit" name="action" value="出勤">出勤</button>
        <button class="genbtn" type="submit" name="action" value="退勤">退勤</button>
    </form>


</body>
</html>