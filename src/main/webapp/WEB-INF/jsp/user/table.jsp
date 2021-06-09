<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>用户列表</title>
        <script type="text/javascript" src="/jquery.min.js"></script>
        <script type="text/javascript">
            function onSearch() {
              var url = "./list";
              var data = $('#searchForm').serialize();

              console.log(data);
              $.ajax({
                url: url,
                type: 'get',
                dataType: 'json',
                data: data,
                success: function(result) {
                  console.log(result);
                }
              })

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