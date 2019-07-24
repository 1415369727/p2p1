<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色分配</title>
<script src="${pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div style="margin-bottom: 10px;margin-left: 10px">
<button class="btn btn-info" data-toggle="modal" data-target="#myModal11">添加角色</button>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal11" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                   添加角色
                </h4>
            </div>
            <form action="" role="form" method="post" enctype="multipart/form-data">
            <div class="modal-body">
              	<input type="hidden" id="mid" name="id">
					  <div class="form-group">
					    <label for="name">角色名</label>
					    <input type="text" class="form-control" id="name" name="name" placeholder="请输入角色名称" style="width: 400px">
					  </div>
					   <div class="form-group">
					    <label for="name">角色描述</label>
					    <input type="text" class="form-control" id="des1" name="pass" placeholder="请输入角色描述" style="width: 400px">
					  </div>
					  
					  <font color="green" style="font-size: 30px">${msg }</font>
				
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <input type="button" style="height: 32px" class="isertmenu" id="isertmenu" value="提交更改">
                  
                </button>
            </div>
            </form>
            <script type="text/javascript">
				$(function(){
					$("#isertmenu").click(function(){
						var name=$("#name").val();
						var des=$("#des1").val();
						var ts=confirm("您确定要添加该角色吗");	
						if(ts==true){
							$.ajax({
								url:"${pageContext.request.contextPath }/admin/addRole.action",
								type:"post",
								data:{"name":name,"des":des},
								success:function(obj){
									if(obj==1){
										alert("角色添加成功");
										location.href="${pageContext.request.contextPath }/admin/showszRole.action";//相当于刷新界
									}else{
										alert("角色添加失败");
										location.href="${pageContext.request.contextPath }/admin/showszRole.action";//相当于刷新界
									}
								}
							});
						}
					});
				});
				</script>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<table class="table table-striped" style="table-layout: fixed;">
	<thead>
	<tr class="warning">
			<td style="width: 60px">ID</td>
			<td>角色名</td>
			<td>角色描述</td>
			<td>操作</td>
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="list" varStatus="s">
			<c:if test="${list.id!=-1 }">
			<tr >
				<td>${s.count }</td>
				<td>${list.rolename }</td>
				<td>
				${list.des }
				</td>
				<td>
				<button id="shanchu" class="btn btn-danger scjuese">删除</button>&nbsp;
				<button id="xiajia" class="btn btn-success" data-toggle="modal" data-target="#myModal">修改</button>
				<%-- <c:if test=""><button id="xiajia" class="btn btn-danger">注销</button>&nbsp;</c:if>
				<c:if test=""><button id="xiajia" class="btn btn-warning">恢复</button>&nbsp;</c:if>
				<a href="" class="btn btn-success">分配角色</a> --%>
				<input value="${list.id }" id="ids" class="ids" type="hidden"/>
				</td>
			</tr>
		</c:if>
		</c:forEach>
		<script type="text/javascript">
	
	$(function(){
		//删除角色
		$(".scjuese").click(function(){
			var id=$(this).siblings("#ids").val();
			var ts=confirm("确定要删除该角色吗？");
			if(ts==true){
				$.ajax({
					url:"${pageContext.request.contextPath }/admin/deleteRole.action?id="+id,
					type:"get",
					success:function(data){
						if(data==1){
							alert("角色删除成功！");
							location.href="${pageContext.request.contextPath }/admin/showszRole.action";//相当于刷新界
						}else{
							location.href="${pageContext.request.contextPath }/admin/showszRole.action";//相当于刷新界
						}
					}
				});
			}
		});
		//修改角色
		$(".btn-success").click(function(){
			var id=$(this).siblings("#ids").val();
			$.ajax({
				url:"${pageContext.request.contextPath }/admin/showUpateRoles.action?id="+id,
				type:"get",
				success:function(data){
					$("#des").val(data.des);
					$("#rolename").val(data.rolename);
					$("#mid").val(data.id);
				}
			});
		});
		
	});
	</script>
	</tbody>
	</table>
		<div class="pagination" style="float: right;">
	
		 	<a href="${pageContext.request.contextPath }/admin/showszRole.action?pn=1" class="firstPage">首页</a>
			 
			<c:if test="${page.hasPreviousPage }">
				<a  class="previousPage" href="${pageContext.request.contextPath }/admin/showszRole.action?pn=${page.prePage}">上一页</a>
			</c:if>	
			
				<c:forEach begin="1" end="${page.pages }" var="i">
					<a class="currentPage" href="${pageContext.request.contextPath }/admin/showszRole.action?pn=${i }">${i }</a>
				</c:forEach>
				
			<c:if test="${page.hasNextPage}">
				<a class="nextPage" href="${pageContext.request.contextPath }/admin/showszRole.action?pn=${page.nextPage}">下一页</a>
			</c:if>
			
			<a class="lastPage" href="${pageContext.request.contextPath }/admin/showszRole.action?pn=${page.lastPage}">尾页</a>
			 
	</div>
	
	
	
<%-- 	  <label for="name" style="font-size: 15px">添加角色</label>
	<form action="${pageContext.request.contextPath }/admin/addRole.action" role="form" method="post" enctype="multipart/form-data">
		  <div class="form-group">
		    <label for="name">角色名</label>
		    <input type="text" class="form-control"  name="rolename" placeholder="请输入角色名" style="width: 400px">
		  </div>
		   <div class="form-group">
		    <label for="name">角色描述</label>
		    <input type="text" class="form-control"  name="des" placeholder="请输入角色描述" style="width: 400px">
		  </div>
		
		  <button type="submit" class="btn btn-primary btn-lg">添加角色</button>
		  <font color="green" style="font-size: 30px">${tmsg }</font>
	</form> --%>
	
	
	
	
	
	<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    修改角色信息
                </h4>
            </div>
            <form action="${pageContext.request.contextPath }/admin/updateRole.action" role="form" method="post">
            <div class="modal-body">
              	<input type="hidden" id="mid" name="id">
					  <div class="form-group">
					    <label for="name">角色名称</label>
					    <input type="text" class="form-control" id="rolename" name="rolename" style="width: 400px">
					  </div>
					   <div class="form-group">
					    <label for="name">角色描述</label>
					    <input type="text" class="form-control" id="des" name="des"style="width: 400px">
					  </div>
					
					  <font color="green" style="font-size: 30px">${msg }</font>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <input type="button" class="btn btn-primary" value="提交更改">
                  
                </button>
            </div>
            </form>
            <script type="text/javascript">
            $(function(){
		  		$(".btn-primary").click(function(){
		  			var rolename=$("#rolename").val();
		  			var des=$("#des").val();
		  			var id=$("#mid").val();
		  			var ts=confirm("您确定要修改角色信息吗");	
		  			if(ts==true){
		  				$.ajax({
		  					url:"${pageContext.request.contextPath }/admin/updateRole.action",
		  					type:"post",
		  					data:{"rolename":rolename,"des":des,"id":id},
		  					success:function(obj){
		  						if(obj==1){
		  							alert("角色信息修改成功！");
		  							location.href="${pageContext.request.contextPath }/admin/showszRole.action";//相当于刷新界
		  						}else{
		  							alert("角色信息修改失败");
		  							location.href="${pageContext.request.contextPath }/admin/showszRole.action";//相当于刷新界
		  						}
		  					}
		  				});
		  			}
		  		});
		  	});
            </script>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>