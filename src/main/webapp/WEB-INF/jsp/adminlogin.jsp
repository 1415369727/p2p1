<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台登录</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="${root }/css/style.css" />
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="${root }/js/jquery.js"></script>
<script src="${root }/js/verificationNumbers.js"></script>
<script src="${root }/js/Particleground.js"></script>
<script>
$(document).ready(function() {
  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  });
  //验证码
  createCode();
  //测试提交，对接程序删除即可
});
</script>
</head>
<body>
<form action="${root }/admin/adminLogin.action" method="post" onsubmit="return check()">
<dl class="admin_login">
 <dt>
  <strong>p2p后台管理系统</strong>
  <em>Management System</em>
 </dt>
 <dd class="user_icon">
  <input type="text" placeholder="账号" name="username" class="login_txtbx"/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" placeholder="密码" name="password" class="login_txtbx"/>
 </dd>
 <dd>
<input type="hidden" id="msg" value="${msg }"/>
<script type="text/javascript">
$(function(){
	var msg=$("#msg").val();
	alert(msg);
});
</script>
 <br />
<!-- &nbsp;&nbsp; <input type="checkbox"/>记住我&nbsp;&nbsp; -->
<c:if test="${msg==null }"><font color="#FFF" style="font-size: 14px">输入正确用户信息，如有问题请联系下方管理员</font></c:if>
<font color="#DBDBDB" style="font-size: 14px">${msg }</font>
 </dd>
<%--  <dd class="val_icon">
  <div class="checkcode">
    <input type="text" id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx">
    <canvas class="J_codeimg" id="myCanvas" onclick="createCode()">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
  </div>
  <input type="button" value="验证码核验" class="ver_btn" onClick="validate();">
 </dd> --%>
 <dd>
  <input type="submit" value="立即登陆" class="submit_btn"/>
 </dd>
 <dd>
  <p>© 2015-2050 DeathGhost 版权所有</p>
  <p>沪B2-20080224-1</p> 
 </dd>
 <dd><p>管理员QQ:1582656663</p></dd>
</dl>
</form>
</body>
</html>