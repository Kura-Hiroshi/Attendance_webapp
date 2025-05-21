<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>管理者画面</title>
</head>
<body>
    <h2>従業員名簿</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>従業員名</th>
            <th>パスワード</th>
        </tr>
            <c:forEach var="employee" items="${employeeList}">
                <tr>
                    <td><c:out value="${employee.id}" /></td>
                    <td><c:out value="${employee.name}" /></td>
                    <td><c:out value="${employee.pass}" /></td>
                </tr>                
            </c:forEach>
    </table>
</body>
</html>