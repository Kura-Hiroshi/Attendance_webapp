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
<h2>勤怠記録の出力</h2>
<p>出力する期間を入力してください</p>
<form action="OutAttendance" method="post">
    <input type="date" name="start" id="start">
    <input type="date" name="end" id="end">
    <input type="submit" value="表を出力する">
    <script>
        async function login() {
            const start = document.querySelector('#start')
            const end = document.querySelector('#end')
            const response = await fetch("http://localhost:8080/Attendance/OutAttendance", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    start: start.value,
                    end:end.value,
                })
            });

            if (response.ok) {
                const data = await response.json();
                if (data["success"]) {
                    data["list"].array.forEach(element => {
                        console.log(element["employeeID"])
                    });
                } else {
                    alert(data['msg']);                    
                }

            } else {
                const data = await response.json();
                alert("サーバーエラーが発生しました。")
            }
        }
    </script>
</form>
</body>
</html>