<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>勤怠入力システム</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <h1 class="text-center">勤怠入力システム</h1>
    <h2 class="text-center"><c:out value="${eventType}"/></h2>
    <form class="block-center large-block" action=""  autocomplete="off">
        <table>
            <tr>
                <td>従業員名</td>
                <td>
                    <select name="employee_id" id="employee_id">
                        <option value="">--自分の名前を選択してください--</option>
                        <c:forEach var="employee" items="${employeeList}">
                            <option value=<c:out value="${employee.id}" />><c:out value="${employee.id}" />:<c:out value="${employee.name}" /></option>
                            
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>パスワード</td>
                <td><input type="password" name="pass" id="pass" required></td>
            </tr>
        </table>
        <input type="hidden" name="event_type" id="event_type" value=<c:out value="${eventType}"/>>
        <input class="genbtn sbtn adjust-right" type="button" value="打刻する" onclick="send()">
    </form>

    <script>
        async function send() {
            const employee_id = document.querySelector('#employee_id')
            const pass = document.querySelector('#pass')
            const event_type = document.querySelector('#event_type')
            const response = await fetch("http://localhost:8080/Attendance/AttendanceServlet", {
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
                    alert("登録成功");
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