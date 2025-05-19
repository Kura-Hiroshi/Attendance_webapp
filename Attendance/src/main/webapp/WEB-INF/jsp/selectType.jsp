<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>勤怠記録画面</title>
</head>
<body>
    <form action="SelectTypeServlet" method="post">    
        <button type="submit" name="action" value="出勤">出勤</button>
        <button type="submit" name="action" value="退勤">退勤</button>
    </form>


</body>
</html>