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
    <h2 class="mb-2 text-center">従業員名簿</h2>
    <div class="form-container-c">
        <div class="form">
            <table class="t-smp">
                <tr>
                    <th class="th-smp">ID</th>
                    <th class="th-smp">従業員名</th>
                    <th class="th-smp">パスワード</th>
                </tr>
                    <c:forEach var="employee" items="${employeeList}">
                        <tr>
                            <td class="td-smp"><c:out value="${employee.id}" /></td>
                            <td class="td-smp"><c:out value="${employee.name}" /></td>
                            <td class="td-smp"><c:out value="${employee.pass}" /></td>
                        </tr>
                    </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>