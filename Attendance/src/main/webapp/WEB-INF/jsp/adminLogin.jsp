<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>管理者ログイン画面</title>
</head>
<body>
    <form action="">
        <table>
            <tr>
                <td>事業所ID</td>
                <td><input type="text" name="company_id" id="company_id"></td>
            </tr>
            <tr>
                <td>パスワード（管理者用）</td>
                <td><input type="password" name="pass" id="pass"></td>
            </tr>
        </table>
        <input type="button" value="ログイン" onclick="login()">
    </form>
    <script>
        async function login() {
            const company_id = document.querySelector('#company_id')
            const pass = document.querySelector('#pass')
            const response = await fetch("http://localhost:8080/Attendance/AdminLoginServlet", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    company_id: company_id.value,
                    pass:pass.value,
                })
            });

            if (response.ok) {
                const data = await response.json();
                if (data["success"]) {
                    window.location.href = 'SelectMethodServlet';
                } else {
                    alert(data['msg']);                    
                }

            } else {
                const data = await response.json();
                alert("ログインすることができませんでした。しばらくしてから再度お試しください。")
            }
        }
    </script>
</body>
</html>