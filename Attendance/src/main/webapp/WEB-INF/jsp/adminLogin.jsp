<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>管理者ログイン画面</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">

</head>
<body>
    <header class="mt-2 mb-4">
        <button class="ma-2 btn btn-s" onclick="window.location.href='index.jsp'">戻る</button>
    </header>
    <c:choose>
        <c:when test="${admin ==null}">
            <!-- 管理者としてログインしていない場合 -->
            <h1 class="mb-2 text-center">勤怠管理者用システム</h1>
            <h2 class="mb-2 text-center">管理者用ログイン画面</h2>
            <div class="form-container-c">
                <form class="form">
                    <table class="form_table">
                        <tr>
                            <td class="form_cell-label">事業所ID</td>
                            <td><input class="input" type="text" name="company_id" id="company_id"></td>
                        </tr>
                        <tr>
                            <td class="form_cell-label">パスワード（管理者用）</td>
                            <td><input class="input" type="password" name="pass" id="pass"></td>
                        </tr>
                    </table>
                    <div class="text-right"><input class="btn btn-m" type="button" value="ログイン" onclick="login()"></div>
                </form>
            </div>
        </c:when>
        <c:otherwise>
        <!-- 管理者としてログイン済みの場合 -->
        <div class="form-container-r">
            <button class="btn btn-xl mx-4" onclick="window.location.href='SelectMethodServlet'">管理者画面へ</button>
            <button class="btn btn-xl mx-4" onclick="window.location.href='Logout'">ログアウト</button>
        </div>
        </c:otherwise>
    </c:choose>
    <script>
        async function login() {
            const company_id = document.querySelector('#company_id')
            const pass = document.querySelector('#pass')
            const response = await fetch("AdminLoginServlet", {
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
                alert("サーバーとの接続に失敗しました")
            }
        }
    </script>
</body>
</html>