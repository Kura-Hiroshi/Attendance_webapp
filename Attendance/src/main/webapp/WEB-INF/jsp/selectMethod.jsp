<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <button onclick="location.href='EmployeeAdminServlet'">従業員の登録・削除</button>
    <button onclick="location.href='OutEmployeeListServlet'">従業員名簿の確認</button>
    <button onclick="location.href='OutAttendance'">勤怠記録の出力</button>
</body>
</html>