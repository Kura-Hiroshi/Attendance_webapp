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
    <header class="mt-2 mb-4">
        <button class="ma-2 btn btn-s" onclick="window.location.href='CompanyLoginServlet'">戻る</button>
    </header>
    <h1 class="text-center mb-2">勤怠入力システム</h1>
    <p class="text-center mb-4">記録したいものを選んでください</p>
    <form class="form-container-r" action="SelectTypeServlet" method="post">    
        <button class="btn btn-xl mx-4" type="submit" name="action" value="出勤">出勤</button>
        <button class="btn btn-xl mx-4" type="submit" name="action" value="退勤">退勤</button>
    </form>


</body>
</html>