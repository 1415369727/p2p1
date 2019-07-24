<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加后台用户</title>
<script src="${pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	$(function(){
		$(".btn-info").click(function(){
		 	$.ajax({
				url:"${pageContext.request.contextPath }/admin/showRole.action",
				type:"get",
				success:function(data){
					for(i in data){
						if(data[i].id!=-1){
						$("#smallCategory").append("<option value="+data[i].id+">"+data[i].rolename+"</option>");
						}
						}
				}
			}); 
		});
});
</script>
</head>
<body>
<div style="margin-bottom: 10px;margin-left: 10px">
<button class="btn btn-info" data-toggle="modal" data-target="#myModal">添加用户</button>
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
                   添加用户
                </h4>
            </div>
            <form action="${pageContext.request.contextPath }/admin/insertMenu.action" role="form" method="post" enctype="multipart/form-data">
            <div class="modal-body">
              	<input type="hidden" id="mid" name="id">
					  <div class="form-group">
					    <label for="name">用户名</label>
					    <input type="text" class="form-control" id="name" name="name" placeholder="请输入用户名称" style="width: 400px">
					  </div>
					   <div class="form-group">
					    <label for="name">密码</label>
					    <input type="text" class="form-control" id="pass" name="pass" placeholder="请输入密码" style="width: 400px">
					  </div>
					   <div class="form-group">
					    <label for="name">分配角色</label>
					    <select  class="form-control" id="smallCategory" name="roleid" style="width: 180px">
					    	
					    </select>
					  </div>
					  <font color="green" style="font-size: 30px">${msg }</font>
				
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <input type="button" class="btn btn-primary isertmenu" value="提交更改">
                  
                </button>
            </div>
            </form>
            <script type="text/javascript">
				$(function(){
					$(".isertmenu").click(function(){
						var name=$("#name").val();
						var pass=$("#pass").val();
						var role=$("#smallCategory").val();
						
						var ts=confirm("您确定要添加该用户吗");	
						if(ts==true){
							$.ajax({
								url:"${pageContext.request.contextPath }/admin/addAdmin.action",
								type:"post",
								data:{"name":name,"pass":pass,"role":role},
								success:function(obj){
									if(obj==1){
										alert("用户添加成功");
										location.href="${pageContext.request.contextPath }/admin/showaddAdmin.action";//相当于刷新界
									}else{
										alert("用户添加失败");
										location.href="${pageContext.request.contextPath }/admin/showaddAdmin.action";//相当于刷新界
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



<div class="" style="margin-left: 10px">
<table class="table table-striped" style="table-layout: fixed;">
	<thead>
	<tr class="warning">
			<td style="width: 60px">ID</td>
			<td>用户名</td>
			<td>申请时间</td>
			<td>分配角色</td>
			<td>操作</td>
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="list" varStatus="s">
		
			<tr >
				<td>${s.count }</td>
				<td>${list.username }</td>
				<td>
				<fmt:formatDate value="${list.creattime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					 <select  class="form-control aaaa" id="updaterole" name="roleid" style="width: 180px">
				 <c:if test="${list.rid==null }"><option>--请选择角色--</option></c:if>
				 <c:forEach items="${rlist }" var="rl">
				 		<c:if test="${rl.id!=-1 }">
		    			<option value="${rl.id }" ${list.rid==rl.id?"selected":"" }>${rl.rolename }</option>
		    			</c:if>
		    	</c:forEach>
					</select>
					<input type="hidden" id="adminid" value="${list.id }"/>
				</td>
				<td>
				<c:if test="${list.state==1 }"><button id="xiajia" class="btn btn-danger">注销</button>&nbsp;</c:if>
				<c:if test="${list.state==2 }"><button id="xiajia" class="btn btn-warning">恢复</button>&nbsp;</c:if>
				 <a href="" class="btn btn-success updapass">初始化密码</a> 
				<input value="${list.id }" id="ids" class="ids" type="hidden"/>
				</td>
			</tr>
		</c:forEach>
		
	</tbody>
	</table>
	<div class="pagination" style="float: right;">
	
		 	<a href="${pageContext.request.contextPath }/admin/showaddAdmin.action?pn=1" class="firstPage">首页</a>
			 
			<c:if test="${page.hasPreviousPage }">
				<a  class="previousPage" href="${pageContext.request.contextPath }/admin/showaddAdmin.action?pn=${page.prePage}">上一页</a>
			</c:if>	
			
				<c:forEach begin="1" end="${page.pages }" var="i">
					<a class="currentPage" href="${pageContext.request.contextPath }/admin/showaddAdmin.action?pn=${i }">${i }</a>
				</c:forEach>
				
			<c:if test="${page.hasNextPage}">
				<a class="nextPage" href="${pageContext.request.contextPath }/admin/showaddAdmin.action?pn=${page.nextPage}">下一页</a>
			</c:if>
			
			<a class="lastPage" href="${pageContext.request.contextPath }/admin/showaddAdmin.action?pn=${page.lastPage}">尾页</a>
			 
	</div>
	
	
	<script type="text/javascript">
		$(function(){
			//初始化密码
			$(".updapass").click(function(){
				var uid=$(this).siblings("#ids").val();
				var ts=confirm("确定要初始化密码吗？");
				if(ts==true){
					$.ajax({
						url:"${pageContext.request.contextPath }/admin/updatePass.action?id="+uid,
						type:"get",
						success:function(data){
							if(data=="1"){
								alert("密码已更改为初始密码 123456");
								location.href="${pageContext.request.contextPath }/admin/showaddAdmin.action";//相当于刷新界
							}
						}
					});
					
				}else{
					location.href="${pageContext.request.contextPath }/admin/showaddAdmin.action";//相当于刷新界
				}
			});
			//修改角色
			$(".aaaa").change(function(){
				var id=$(this).val();
				var uid=$(this).siblings("#adminid").val();
				var ts=confirm("确定要改变该用户角色吗？");
				if(ts==true){
					$.ajax({
						url:"${pageContext.request.contextPath }/admin/updateRoleid.action?id="+id+"&uid="+uid,
						type:"get",
						success:function(data){
							if(data=="1"){
								alert("该用户角色已更改！");
								location.href="${pageContext.request.contextPath }/admin/showaddAdmin.action";//相当于刷新界
							}
						}
					});
					
				}else{
					location.href="${pageContext.request.contextPath }/admin/showaddAdmin.action";//相当于刷新界
				}
			});
			//恢复用户
			$(".btn-warning").click(function(){
				var id=$(this).siblings("#ids").val();
				var ts=confirm("确定要恢复该用户吗？");
				if(ts==true){
				$.ajax({
					url:"${pageContext.request.contextPath }/admin/updateStateHuiFu.action?id="+id,
					type:"get",
					success:function(data){
						if(data=="1"){
							alert("该用户已成功恢复，可以正常登陆");
							location.href="${pageContext.request.contextPath }/admin/showaddAdmin.action";//相当于刷新界
						}
					}
				});
				location.href="${pageContext.request.contextPath }/admin/showaddAdmin.action";//相当于刷新界
				}
			});
			//注销用户
			$(".btn-danger").click(function(){
				var id=$(this).siblings("#ids").val();
				var ts=confirm("确定要主注销该用户吗？");
				if(ts==true){
				$.ajax({
					url:"${pageContext.request.contextPath }/admin/updateStateZhuXiao.action?id="+id,
					type:"get",
					success:function(data){
						if(data=="1"){
							alert("该用户已成功被注销，可以点击恢复按钮进行恢复");
							location.href="${pageContext.request.contextPath }/admin/showaddAdmin.action";//相当于刷新界
						}
					}
				});
				location.href="${pageContext.request.contextPath }/admin/showaddAdmin.action";//相当于刷新界
				}
			});
		});
	</script>
	<%-- <form action="${pageContext.request.contextPath }/admin/addAdmin.action" role="form" method="post" enctype="multipart/form-data">
		  <div class="form-group">
		    <label for="name">用户名</label>
		    <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名" style="width: 400px">
		  </div>
		   <div class="form-group">
		    <label for="name">密码</label>
		    <input type="text" class="form-control" id="password" name="password" placeholder="请输入密码" style="width: 400px">
		  </div>
		<div class="form-group">
		    <label for="name">角色</label>
		    <select  class="form-control" id="smallCategory" name="roleid" style="width: 180px">
		    	<option>请选择角色</option>
		    </select>
		  </div>
		  <button type="submit" class="btn btn-primary btn-lg">添加用户</button>
		  <font color="green" style="font-size: 30px">${msg }</font>
	</form> --%>
	</div>
	
</body>
</html>