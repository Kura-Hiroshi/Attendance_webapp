<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>タイムカード機能</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <c:choose>
        <c:when test="${company ==null}">
            <!-- ログインしていない場合 -->
            <header class="mt-2 mb-4">
                <button class="ma-2 btn btn-s" onclick="window.location.href='index.jsp'">戻る</button>
            </header>
            <h1 class="text-center mt-4 mb-4">タイムカード機能</h1>
            <h3 class="text-center mb-4">事業所ログイン画面</h3>
            <div class="form-container-c">
                <form class="form" action="CompanyLoginServlet" method="post">
                    <table class="form_table">
                        <tr>
                            <td class="form_cell-label">事業所ID</td>
                            <td><input class="input" type="text" name="company_id" id="company_id"></td>
                        </tr>
                        <tr>
                            <td class="form_cell-label">パスワード</td>
                            <td><input class="input" type="password" name="pass" id="pass"></td>
                        </tr>
                    </table>
                    <div class="text-right"><input class="btn btn-m" type="button" value="ログイン" onclick="login()"></div>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <!-- ログイン済みの場合 -->
            <div class="form-container-r mt-8">
                <button class="btn btn-xl mx-4" onclick="window.location.href='SelectTypeServlet'">タイムカード機能へ</button>
                <button class="btn btn-xl mx-4" onclick="window.location.href='Logout'">ログアウト</button>
            </div>
        </c:otherwise>
    </c:choose>

    <script>
        async function login() {
            const company_id = document.querySelector('#company_id')
            const pass = document.querySelector('#pass')
            const response = await fetch("CompanyLoginServlet", {
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
                    window.location.href = 'SelectTypeServlet';
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