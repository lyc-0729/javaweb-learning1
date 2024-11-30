<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>下载文件</title>
  <style>
    ul li{
      list-style: none;
      float: left;
      margin-top: 40px;
    }

    ul li a{
      padding: 20px 50px;
      height: 40px;
      background-color: aquamarine;
      color: aliceblue;
      box-sizing: border-box;
      margin-right: 10px;
      font-size: 16px;
      text-decoration: none;
    }

    ul li a:hover{
      background-color: aliceblue;
    }
  </style>
</head>
<body>
<h1><%= "设置 Content-Type 不同类型的资源" %>
</h1>
<h2><%= "根据不同的参数类型返回不同的资源 "%>
</h2>
<br/>
<ul>
  <li>
    <a href="/res?type=img">返回图片</a>
  </li>
  <li>
    <a href="/res?type=pdf">返回pdf</a>
  </li>
  <li>
    <a href="/res?type=txt">返回txt</a>
  </li>
</ul>
<a href="download?filename=image01.jpg">壁纸</a>
<a href="download?filename=b.txt">文件</a>
</body>
</html>