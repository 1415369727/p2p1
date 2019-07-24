<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>金融后台管理系统</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
		<link href="${root}/assets/css/bootstrap.min.css" rel="stylesheet" /> 
		<link rel="stylesheet" href="${root}/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="http://fonts.useso.com/css?family=Open+Sans:400,300" />
		<link rel="stylesheet" href="${root}/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${root}/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${root}/assets/css/ace-skins.min.css" />
		<script src="${root}/assets/js/ace-extra.min.js"></script>
		<%-- <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css"/> --%>
			<script src="${pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<%-- <script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script> --%>
<%-- <link href="${pageContext.request.contextPath }/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>	 --%>
	</head>

	<body>

		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
							金融后台管理系统
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
	

						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="${root}/assets/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎光临,</small>
									${admin.username }
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<!-- <li>
									<a href="#">
										<i class="icon-cog"></i>
										设置
									</a>
								</li> -->

								<li>
									<a href="#" data-toggle="modal" data-target="#myModal">
										<i class="icon-user"></i>
										个人资料
									</a>
								</li>

								<li class="divider"></li>

								<li>
									<a href="${root }/admin/adminQueit.action">
										<i class="icon-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>
	<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                   个人资料设置
                </h4>
            </div>
            <form action="${pageContext.request.contextPath }/admin/updateAdmin.action" role="form" method="post" enctype="multipart/form-data">
            <div class="modal-body">
              	<input type="hidden" id="mid" name="id" value="${admin.id }">
					  <div class="form-group">
					    <label for="name">用户名</label>
					    <input type="text" class="form-control" id="title1" name="username" value="${admin.username }" style="width: 400px">
					  </div>
					   <div class="form-group">
					    <label for="name">密码</label>
					    <input type="password" class="form-control" id="url1" name="password" placeholder="请输入密码" style="width: 400px">
					  </div>
					 <script type="text/javascript">
					 $(function(){
					  		$(".btn-primary").click(function(){
					  			var name=$("#title1").val();
					  			var pass=$("#url1").val();
					  			var id=$("#mid").val();
					  			var ts=confirm("您确定要修改个人信息吗");	
					  			if(ts==true){
					  				$.ajax({
					  					url:"${pageContext.request.contextPath }/admin/updateAdmin.action",
					  					type:"post",
					  					data:{"name":name,"pass":pass,"id":id},
					  					success:function(obj){
					  						if(obj==1){
					  							alert("个人信息修改成功，请重新登陆");
					  							location.href="${pageContext.request.contextPath }/admin/showLogin.action";//相当于刷新界
					  						}else{
					  							alert("个人信息修改失败");
					  							location.href="${pageContext.request.contextPath }/admin/showIndex.action";//相当于刷新界
					  						}
					  					}
					  				});
					  			}
					  		});
					  	});
					 </script>
					
					  <font color="green" style="font-size: 30px">${msg }</font>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <input type="button" id="btn" class="btn btn-primary" value="提交更改">
                  
                </button>
            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

