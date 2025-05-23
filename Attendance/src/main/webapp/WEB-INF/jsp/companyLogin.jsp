<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>勤怠記録画面</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <h1 class="text-center">勤怠入力システム</h1>
    <h2 class="text-center">勤怠入力システムログイン画面</h2>
    <c:choose>
        <c:when test="${company ==null}">
            <!-- 勤怠システムにログインしていない場合 -->
            <form class="block-center large-block" action="CompanyLoginServlet" method="post">
                <table>
                    <tr>
                        <td class="text-justify">事業所ID</td>
                        <td><input type="text" name="company_id" id="company_id"></td>
                    </tr>
                    <tr>
                        <td class="text-justify">パスワード</td>
                        <td><input type="password" name="pass" id="pass"></td>
                    </tr>
                </table>
                <input class="genbtn sbtn" type="button" value="ログイン" onclick="login()">
            </form>
        </c:when>
        <c:otherwise>
            <!-- 勤怠システムにログイン済みの場合 -->
            <button onclick="window.location.href='SelectTypeServlet'">勤怠システムへ</button>
            <button onclick="window.location.href='Logout?act=attend'">ログアウト</button>
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