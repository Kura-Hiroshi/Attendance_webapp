<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>タイムカード機能</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <header class="mt-2 mb-4">
        <button class="ma-2 btn btn-s" onclick="window.location.href='SelectTypeServlet'">戻る</button>
        <h3 class="text-center"><c:out value="${company.companyName}" /></h3>
    </header>
    <h1 class="mb-2 text-center">タイムカード機能</h1>
    <h2 class="mb-2 text-center"><c:out value="${eventType}"/></h2>
    <div class="form-container-c">
        <form class="form" action=""  autocomplete="off">
            <table class="form_table">
                <tr>
                    <td class="form_cell-label">従業員名</td>
                    <td>
                        <select class="input" name="employee_id" id="employee_id">
                            <option value="">--自分の名前を選択してください--</option>
                            <c:forEach var="employee" items="${employeeList}">
                                <option value=<c:out value="${employee.id}" />><c:out value="${employee.id}" />:<c:out value="${employee.name}" /></option>
        
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="form_cell-label">パスワード</td>
                    <td><input class="input" type="password" name="pass" id="pass" required></td>
                </tr>
            </table>
            <input type="hidden" name="event_type" id="event_type" value=<c:out value="${eventType}"/>>
            <div class="text-right"><input class="btn btn-m" type="button" value="打刻する" onclick="send()"></div>
        </form>
    </div>

    <script>
        async function send() {
            const employee_id = document.querySelector('#employee_id')
            const pass = document.querySelector('#pass')
            const event_type = document.querySelector('#event_type')
            const response = await fetch("AttendanceServlet", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    employee_id: employee_id.value,
                    pass:pass.value,
                    event_type:event_type.value
                })
            });

            if (response.ok) {
                const data = await response.json();
                if (data["success"]) {
                    alert(data["eventTime"]);
                    window.location.href = 'SelectTypeServlet';
                } else {
                    alert(data['msg']);                    
                }

            } else {
                const data = await response.json();
                alert("登録失敗")
            }
        }
    </script>
</body>
</html>