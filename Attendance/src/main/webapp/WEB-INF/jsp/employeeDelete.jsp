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
        <button class="ma-2 btn btn-s" onclick="window.location.href='EmployeeAdminServlet'">戻る</button>
    </header>
    <h2 class="text-center mb-4">従業員の削除</h2>
    <div class="form-container-c">
        <form class="form" action="EmployeeDeleteServlet" method="post" id="deleteform">
            <div class="mb-2">
                従業員名：<select class="input" name="employee_id" id="employee_id">
                    <option value="">--削除対象者を選択してください--</option>
                    <c:forEach var="employee" items="${employeeList}">
                        <option value=<c:out value="${employee.id}" />><c:out value="${employee.id}" />:<c:out value="${employee.name}" /></option>
                    </c:forEach>
                </select>
            </div>
            <div class="text-right"><input class="btn btn-m" type="button" value="削除" onclick="delemp()"></div>
        </form>
    </div>

    <script>
        async function delemp(){
            const employee_id = document.querySelector('#employee_id')
            const form = document.querySelector('#deleteform')
            const response = await fetch("EmployeeDeleteServlet", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    employee_id: employee_id.value,
                })
            });

            if (response.ok) {
                const data = await response.json();
                if (data["success"]) {
                    alert("削除しました");
                    window.location.href = 'EmployeeDeleteServlet';
                } else {
                    alert(data['msg']);                    
                }

            } else {
                alert("通信に失敗しました")
            }

        }
    </script>
</body>
</html>