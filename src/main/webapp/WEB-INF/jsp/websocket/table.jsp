<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>用户列表</title>
        <script type="text/javascript" src="/jquery.min.js"></script>
        <script type="text/javascript" src="/websocket.js"></script>
        <script type="text/javascript">
           var websocket = null;

           // 判断当前浏览器是否支持WebSocket
            if ('WebSocket' in window) {
              // 创建WebSocket对象，连接服务器端点
              websocket = new WebSocket("ws://localhost:8080/ws")
            }else {
              alert('Not support websocket')
            }

            // 连接发生错误时回调
            websocket.onerror = function () {
                appendMessage("error");
            }

            // 连接成功建立的回调方法
            websocket.onopen = function (event) {
                appendMessage('open')
            }

            // 接收到消息的回调
            websocket.onmessage = function (event) {
                appendMessage(event.data)
            }

            // 连接关闭的回调
            websocket.onclose = function () {
                appendMessage('close')
            }

            // 关闭连接
            function closeWebSocket() {
                websocket.close();
            }

            // 发送消息
            function sendMessage() {
                websocket.send("message");
            }
        </script>
    </head>
    <body>
        <div style="margin: 20px 0;"></div>
        <div>
            <form id="searchForm" method="post">
                <table>
                    <tr>
                        <td>用户名称：</td>
                        <td><input id="userName" name="userName" style="width: 100%;height: 32px" placeholder="输入用户名称"/></td>
                        <td>备注：</td>
                        <td><input id="note" name="note" style="width: 100%; height: 32px" placeholder="输入备注"/></td>
                        <td>
                            <a href="#" style="width: 80px" onclick="onSearch()">查询</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div>
            <table id="dg">
                <thead>
                    <tr>
                        <th width="80">编号</th>
                        <th width="100">用户名称</th>
                        <th width="80">备注</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${userList}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.userName}</td>
                            <td>${user.note}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>