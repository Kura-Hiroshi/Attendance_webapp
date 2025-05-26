<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アカウント作成</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <header class="mt-2 mb-4">
        <button class="ma-2 btn btn-s" onclick="window.location.href='index.jsp'">戻る</button>
    </header>
    <h1 class="text-center mb-2">事業所アカウント作成</h1>
    <div class="form-container-c">
        <form class="form">
            <table class="form_table">
                <tr>
                    <td class="form_cell-label">事業所ID</td>
                    <td><input class="input" type="text" name="company_id" id="company_id"></td>
                </tr>
                <tr>
                    <td class="form_cell-label">事業所名</td>
                    <td><input class="input" type="text" name="company_name" id="company_name"></td>
                </tr>
                <tr>
                    <td class="form_cell-label">パスワード<br>（勤怠システム用）</td>
                    <td><input class="input" type="password" name="pass_for_attend" id="pass_for_attend"></td>
                </tr>
                <tr>
                    <td class="form_cell-label">パスワード<br>（管理者用）</td>
                    <td><input class="input" type="password" name="pass_for_admin" id="pass_for_admin"></td>
                </tr>
            </table>
            <div class="text-right"><input class="btn btn-m" type="button" onclick="create()" value="アカウント作成"></div>
        </form>
    </div>
    <script>
        async function create() {
            console.log("確認")
            const company_id = document.querySelector('#company_id');
            const pass_for_attend = document.querySelector('#pass_for_attend');
            const pass_for_admin = document.querySelector('#pass_for_admin');
            const response =await fetch("#AccountCreate",{
                method:"POST",
                headers:{
                    "Content-Type": "application/json"
                },
                body:JSON.stringify({
                    company_id: company_id.value,
                    company_name:company_name.value,
                    pass_for_attend:pass_for_attend.value,
                    pass_for_admin:pass_for_admin.value,
                })
            })

            if (response.ok) {
                const data = await response.json();
                if (data["success"]) {
                    alert('事業所アカウントを作成しました')
                    window.location.href = 'SelectMethodServlet';
                } else {
                    alert(data['msg']); 
                }
            } else {
                alert('サーバーとの接続に失敗しました')
            }
        }
    </script>
</body>
</html>