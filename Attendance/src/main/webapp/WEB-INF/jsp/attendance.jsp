<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>勤怠入力画面</title>
</head>
<body>
    <h2><c:out value="${eventType}"/></h2>
    <form action=""  autocomplete="off" method="post">
        <table>
            <tr>
                <td>
                    従業員名
                </td>
                <td>
                    <select name="employee_id" id="employee_id">
                        <option value="">--自分の名前を選択してください--</option>
                        <option value="1">1:山田　一郎</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    パスワード<input type="password" name="pass" id="pass" required>
                </td>
            </tr>
        </table>
        <input type="hidden" name="event_type" id="event_type" value=<c:out value="${eventType}"/>>
        <input type="button" value="打刻する" onclick="send()">
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