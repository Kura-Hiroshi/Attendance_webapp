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
        <button class="ma-2 btn btn-s" onclick="window.location.href='SelectMethodServlet'">戻る</button>
    </header>
<h2 class="text-center mb-2">勤怠記録の出力</h2>
<p class="text-center mb-2">出力する期間を入力してください</p>
<div class="form-container-c">
    <form class="form">
        <div class="mb-2">
            <input type="date" name="start" id="start">
            <input type="date" name="end" id="end">
            <input type="button" value="表を出力する" onclick="output(false)">
            <input type="button" value="Excelファイルで出力する" onclick="output(true)">
        </div>
        <table class="t-smp" id="resultTable" style="display: none;">
            <thead>
            <tr>
                <th class="th-smp">社員ID</th>
                <th class="th-smp">社員名</th>
                <th class="th-smp">日付</th>
                <th class="th-smp">出勤</th>
                <th class="th-smp">退勤</th>
            </tr>
        </thead>
        <tbody></tbody>
        </table>
    </form>
</div>


    <script>
        async function output(exportExcel) {
            const start = document.querySelector('#start')
            const end = document.querySelector('#end')
            const response = await fetch("OutAttendance", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    start: start.value,
                    end:end.value,
                    exportExcel:exportExcel,
                })
            });

            const contentType = response.headers.get("Content-Type");

            if (response.ok) {
                if (contentType && contentType.includes("application/json")) {//contetTypeがnullでなく、かつJSONを含む場合
                    //現在のページに勤怠表を作成する
                    const data = await response.json();
                    const table = document.getElementById("resultTable");
                    const tbody = table.querySelector("tbody");
                    tbody.innerHTML = "";

                    if (data.success) {
                        data.list.forEach(item => {
                            const tr = document.createElement("tr");
                            tr.innerHTML =
                                "<td class='td-smp'>" + item.employeeId + "</td>" +
                                "<td class='td-smp'>" + item.employeeName + "</td>" +
                                "<td class='td-smp'>" + item.clockDate + "</td>" +
                                "<td class='td-smp'>" + (item.workin != null ? item.workin : '') + "</td>" +
                                "<td class='td-smp'>" + (item.workout != null ? item.workout : '') + "</td>";
                            tbody.appendChild(tr);
                        });
                        table.style.display = "table";
                    } else {
                        alert(data.msg);
                    }
                } else if (contentType && contentType.includes("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                    // Excelファイルの処理
                    const blob = await response.blob();
                    const url = window.URL.createObjectURL(blob);
                    const a = document.createElement("a");
                    a.href = url;
                    a.download = "attendance.xlsx";
                    a.click();
                    window.URL.revokeObjectURL(url);
                } else {
                    alert("不明なコンテンツタイプです。");
                }
            } else {
                alert("サーバーエラーが発生しました。");
            }
        }
    </script>
</body>
</html>