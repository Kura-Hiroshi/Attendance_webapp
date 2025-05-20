<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                        <option value="1">1:山田　一郎</option>
        </select>
        <input type="button" value="削除" onclick="delemp()">
    </form>

    <script>
        async function delemp(){
            const employee_id = document.querySelector('#employee_id')
            const form = document.querySelector('#deleteform')
            const response = await fetch("http://localhost:8080/Attendance/EmployeeDeleteServlet", {
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