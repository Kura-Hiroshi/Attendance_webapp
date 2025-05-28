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
        <h3 class="text-center"><c:out value="${admin.companyName}" /></h3>
    </header>
    <h2 class="text-center mb-4">従業員の登録</h2>
    <div class="form-container-c">
        <form class="form" id="form">
            <table class="form-table mb-2">
                <tr>
                    <td class="form_cell-label">従業員名</td>
                    <td><input class="input" type="text" name="name" id="name"></td>
                </tr>
                <tr>
                    <td class="form_cell-label">パスワード</td>
                    <td><input class="input" type="password" name="pass" placeholder="パスワードを設定してください" id="pass"></td>
                </tr>
            </table>
            <div class="text-right"><input class="btn btn-m"  type="button" value="登録" onclick="register()"></div>
        </form>
    </div>
    <script>
        async function register() {
            const name = document.querySelector('#name');
            const pass = document.querySelector('#pass');
            const form = document.querySelector('#form');
            const response = await fetch("EmployeeRegisterServlet", {
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
                    msg = "登録しました。\n従業員ID：" + data_id +"\n従業員名：" + data_name + "\nパスワード：" + data_pass + "\n当該従業員に上記の情報を知らせてください。"
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