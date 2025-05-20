<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="true" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>管理者画面</title>
</head>
<body>
    <h2>従業員の登録</h2>
    <form id="register_form">
        <table>
            <tr>
                <td>従業員名</td>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td>パスワード</td>
                <td><input type="password" name="pass" placeholder="パスワードを設定してください" id="pass"></td>
            </tr>
        </table>
        <input type="button" value="登録" onclick="register()">
    </form>
    <script>
        async function register() {
            const name = document.querySelector('#name');
            const pass = document.querySelector('#pass');
            const form = document.querySelector('#register_form');
            const response = await fetch("http://localhost:8080/Attendance/EmployeeRegisterServlet", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    name: name.value,
                    pass:pass.value,
                })
            });

            if (response.ok) {
                const data = await response.json();
                console.log(data);

                if (data["success"]) {
                    data_id = data["id"];
                    console.log(data_id);
                    data_name = data["name"];
                    data_pass = data["pass"];
                    msg = `登録しました。\n従業員ID：${data_id}\n従業員名：${data_name}\nパスワード：${data_pass}\n当該従業員に以上の情報を知らせてください。`
                    alert(msg)
                    form.reset();
                } else {
                    alert(data['msg']);                    
                }

            } else {
                const data = await response.json();
                alert("登録することができませんでした。しばらくしてから再度お試しください。")
            }
        }
    </script>
</body>
</html>