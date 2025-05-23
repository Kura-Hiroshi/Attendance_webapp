<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <header>
        <button onclick="window.location.href='index.jsp'">戻る</button>
    </header>
    <h1>事業所アカウント作成</h1>
    <form>
        <table>
            <tr>
                <td>事業所ID</td>
                <td><input type="text" name="company_id" id="company_id"></td>
            </tr>
            <tr>
                <td>事業所名</td>
                <td><input type="text" name="company_name" id="company_name"></td>
            </tr>
            <tr>
                <td>パスワード（勤怠システム用）</td>
                <td><input type="password" name="pass_for_attend" id="pass_for_attend"></td>
            </tr>
            <tr>
                <td>パスワード（管理者用）</td>
                <td><input type="password" name="pass_for_admin" id="pass_for_admin"></td>
            </tr>
        </table>
        <input type="button" onclick="create()" value="アカウント作成">
    </form>
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