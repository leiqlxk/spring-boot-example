<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCType html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Hello Spring Boot</title>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <script type="text/javascript" src="/jquery.min.js"></script>
        <script type="text/javascript">
            function post() {
                var params = {
                  'userName': 'user_name_new',
                  'sexCode': 1,
                  'note': 'note_new'
                };

                $.ajax({
                  url:"./user",
                  type: 'post',
                  contentType: "application/json",
                  data: JSON.stringify(params),
                  success: function(result) {
                    console.log(result);
                    if(result == null || result.id == null) {
                      alert("插入失败");
                      return;
                    }

                    alert("插入成功");
                  }
                })
            }

            function get() {
                $.ajax({
                  type: "get",
                  url:"./user/11",
                  success: function (result) {
                    console.log(result);
                    if (result == null) {
                      alert("结果为空")
                    }else {
                      alert("用户信息为" + JSON.stringify(result))
                    }
                  }
                })
            }

            function list() {
              $.ajax({
                type: "get",
                url:"./users/user_name_new/note_new/2/1",
                success: function (result) {
                  console.log(result);
                  if (result == null) {
                    alert("结果为空")
                  }else {
                    alert("用户信息为" + JSON.stringify(result))
                  }
                }
              })
            }

            function put() {
              var params = {
                'note': 'note_new1'
              };

              $.ajax({
                type: "put",
                url:"./user/11",
                contentType: "application/json",
                data: JSON.stringify(params),
                success: function (result) {
                  console.log(result);
                  if (result == null) {
                    alert("结果为空")
                  }else {
                    alert("用户信息为" + JSON.stringify(result))
                  }
                }
              })
            }

            function deleteUser() {
              $.ajax({
                type: "delete",
                url:"./user/11",
                success: function (result) {
                  console.log(result);
                  alert("用户信息为" + JSON.stringify(result))
                }
              })
            }
        </script>
    </head>
    <body>
        <h1>测试RESTFUL下的请求</h1>
        <button onclick="post()">新增</button>
        <button onclick="get()">查看</button>
        <button onclick="list()">列表</button>
        <button onclick="put()">修改</button>
        <button onclick="deleteUser()">删除</button>
    </body>
</html>