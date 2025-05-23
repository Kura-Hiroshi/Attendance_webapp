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
<h2>従業員の削除</h2>
    <form action="EmployeeDeleteServlet" method="post" id="deleteform">
        従業員名：<select name="employee_id" id="employee_id">
            <option value="">--削除対象者を選択してください--</option>
            <c:forEach var="employee" items="${employeeList}">
                <option value=<c:out value="${employee.id}" />><c:out value="${employee.id}" />:<c:out value="${employee.name}" /></option>                
            </c:forEach>
        </select>
        <input type="button" value="削除" onclick="delemp()">
    </form>

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
                    form.reset;
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