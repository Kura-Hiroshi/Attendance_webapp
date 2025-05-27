<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>管理者画面</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <header class="mt-2 mb-4">
        <button class="ma-2 btn btn-s" onclick="window.location.href='SelectMethodServlet'">戻る</button>
        <h3 class="text-center"><c:out value="${admin.companyName}" /></h3>
    </header>
    <div class="form-container-r">
        <button class="btn btn-xl mx-4" value="regist" onclick="location.href='EmployeeRegisterServlet'">従業員の登録</button>
        <button class="btn btn-xl mx-4" value="delete" onclick="location.href='EmployeeDeleteServlet'">従業員の削除</button>
    </div>
</body>
</html>