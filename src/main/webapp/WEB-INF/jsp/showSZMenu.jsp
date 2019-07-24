<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理</title>
<script src="${pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div style="margin-bottom: 10px" data-toggle="modal" data-target="#myModal">
<button class="btn btn-info">添加菜单</button>
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
                   添加菜单
                </h4>
            </div>
            <form action="${pageContext.request.contextPath }/admin/insertMenu.action" role="form" method="post" enctype="multipart/form-data">
            <div class="modal-body">
              	<input type="hidden" id="mid" name="id">
					  <div class="form-group">
					    <label for="name">菜单名称</label>
					    <input type="text" class="form-control" id="menu1" name="menu" placeholder="请输入菜单名称" style="width: 400px">
					  </div>
					   <div class="form-group">
					    <label for="name">链接地址</label>
					    <input type="text" class="form-control" id="url1" name="url" placeholder="请输入链接地址" style="width: 400px">
					  </div>
					   <div class="form-group">
					    <label for="name">菜单描述</label>
					    <input type="text" class="form-control" id="des1" name="des" placeholder="请输入菜单描述" style="width: 400px">
					  </div>
					   <div class="form-group">
					    <label for="name">菜单样式</label>
					    <input type="text" class="form-control" id="icon1" name="icon" placeholder="请输入菜单样式" style="width: 400px">
					  </div>
					   <div class="form-group">
					    <label for="name">菜单分级</label>
					 <select  class="form-control aaaa" id="updaterole" name="roleid" style="width: 220px">
			    			<option value="0">--选择该项为添加父菜单--</option>
			    			<c:forEach items="${mls }" var="m">
			    			<option value="${m.id }">${m.menu }</option>
			    			</c:forEach>
			    			
					</select>
					  </div>
					
					  <font color="green" style="font-size: 30px">${msg }</font>
				
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <input type="button"  style="height: 32px" class="isertmenu" value="提交更改">
                  
                </button>
            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script type="text/javascript">
//forward:showSZMenu.action
	$(function(){
		$(".isertmenu").click(function(){
			var menu=$("#menu1").val();
			var url=$("#url1").val();
			var des=$("#des1").val();
			var icon=$("#icon1").val();
			var pid=$("#updaterole").val();
			var ts=confirm("您确定要添加菜单信息吗");	
			if(ts==true){
				$.ajax({
					url:"${pageContext.request.contextPath }/admin/insertMenu.action",
					type:"post",
					data:{"menu":menu,"url":url,"des":des,"icon":icon,"pid":pid},
					success:function(obj){
						if(obj==1){
							alert("菜单添加成功");
							location.href="${pageContext.request.contextPath }/admin/showSZMenu.action";//相当于刷新界
						}else{
							alert("菜单添加失败");
							location.href="${pageContext.request.contextPath }/admin/showSZMenu.action";//相当于刷新界
						}
					}
				});
			}
		});
	});
/* $(".btn-info").click(function(){
	$.ajax({
		url:"${pageContext.request.contextPath }/admin/showFuMenu.action",
		type:"get",
		success:function(data){
			 for(i in data){
				$("#updaterole").append("<option value='"+data[i].id+"'>"+data[i].menu+"</option>");
			} 
		}
	});
}); */
</script>
<table class="table table-striped" style="table-layout: fixed;">
	<thead>
	<tr class="warning">
			<td style="width: 60px">ID</td>
			<td>菜单名</td>
			<td width="400px">链接地址</td>
			<td>菜单分级</td>
			<td>菜单描述</td>
			<td>操作</td>
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="list" varStatus="s">
			<tr >
				<td>${s.count}</td>
				<td>${list.menu }</td>
				<td>
				<%-- <c:if test="${list.url==null }">-------------</c:if> --%>
				${list.url }
				</td>
				<td>
				<c:if test="${list.pid==0 }">父菜单</c:if>
				<c:if test="${list.pid!=0}">${list.pid }</c:if>
				</td>
				<td>${list.des }</td>
				
				<td>
				<button id="shanchu" class="btn btn-danger scjuese">删除</button>&nbsp;
				<button id="xiajia" class="btn btn-success" data-toggle="modal" data-target="#myModal1">修改</button>
				<%-- <c:if test=""><button id="xiajia" class="btn btn-danger">注销</button>&nbsp;</c:if>
				<c:if test=""><button id="xiajia" class="btn btn-warning">恢复</button>&nbsp;</c:if>
				<a href="" class="btn btn-success">分配角色</a> --%>
				<input value="${list.id }" id="ids" class="ids" type="hidden"/>
				</td>
			</tr>
		</c:forEach>
		<script type="text/javascript">
	
	$(function(){
		//删除菜单
		$(".scjuese").click(function(){
			var id=$(this).siblings("#ids").val();
			var ts=confirm("确定要删除该菜单吗？");
			if(ts==true){
				$.ajax({
					url:"${pageContext.request.contextPath }/admin/deleteMenu.action?id="+id,
					type:"get",
					success:function(data){
						if(data==1){
							alert("菜单删除成功！");
							location.href="${pageContext.request.contextPath }/admin/showSZMenu.action";//相当于刷新界
						}else{
							location.href="${pageContext.request.contextPath }/admin/showSZMenu.action";//相当于刷新界
						}
					}
				});
			}
		});
		//修改角色
		$(".btn-success").click(function(){
			var id=$(this).siblings("#ids").val();
			$.ajax({
				url:"${pageContext.request.contextPath }/admin/showUpateMenus.action?id="+id,
				type:"get",
				success:function(data){
					$("#menu").val(data.menu);
					$("#urls").val(data.url);
					$("#des").val(data.des);
					$("#icon").val(data.icon);
					$("#mids").val(data.id);
				}
			});
		});
		
	});
	</script>
	</tbody>
	</table>
	<div class="pagination" style="float: right;">
	
		 	<a href="${pageContext.request.contextPath }/admin/showSZMenu.action?pn=1" class="firstPage">首页</a>
			 
			<c:if test="${page.hasPreviousPage }">
				<a  class="previousPage" href="${pageContext.request.contextPath }/admin/showSZMenu.action?pn=${page.prePage}">上一页</a>
			</c:if>	
			
				<c:forEach begin="1" end="${page.pages }" var="i">
					<a class="currentPage" href="${pageContext.request.contextPath }/admin/showSZMenu.action?pn=${i }">${i }</a>
				</c:forEach>
				
			<c:if test="${page.hasNextPage}">
				<a class="nextPage" href="${pageContext.request.contextPath }/admin/showSZMenu.action?pn=${page.nextPage}">下一页</a>
			</c:if>
			
			<a class="lastPage" href="${pageContext.request.contextPath }/admin/showSZMenu.action?pn=${page.lastPage}">尾页</a>
			 
	</div>
	
	
	<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    修改菜单
                </h4>
            </div>
            <form action="${pageContext.request.contextPath }/admin/updateMenu.action" role="form" method="post" enctype="multipart/form-data">
            <div class="modal-body">
              	<input type="hidden" id="mids" name="id">
					  <div class="form-group">
					    <label for="name">菜单名称</label>
					    <input type="text" class="form-control" id="menu" name="menu" placeholder="请输入菜单名称" style="width: 400px">
					  </div>
					   <div class="form-group">
					    <label for="name">链接地址</label>
					    <input type="text" class="form-control" id="urls" name="urls" placeholder="请输入链接地址" style="width: 400px">
					  </div>
					   <div class="form-group">
					    <label for="name">菜单描述</label>
					    <input type="text" class="form-control" id="des" name="des" placeholder="请输入菜单描述" style="width: 400px">
					  </div>
					   <div class="form-group">
					    <label for="name">菜单样式</label>
					    <input type="text" class="form-control" id="icon" name="icon" placeholder="请输入菜单样式" style="width: 400px">
					  </div>
					   <div class="form-group">
					    <label for="name">菜单分级</label>
					 <select  class="form-control aaaa" id="updatemenu" name="menuid" style="width: 220px">
			    			<option value="0">--选择该项修改为父菜单--</option>
					</select>
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
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
    <script type="text/javascript">
  //forward:showSZMenu.action
  	$(function(){
  		$(".btn-primary").click(function(){
  			var menu=$("#menu").val();
  			var url=$("#urls").val();
  			var des=$("#des").val();
  			var icon=$("#icon").val();
  			var pid=$("#updatemenu").val();
  			var mids=$("#mids").val();
  			var ts=confirm("您确定要修改菜单信息吗");	
  			if(ts==true){
  				$.ajax({
  					url:"${pageContext.request.contextPath }/admin/updateMenu.action",
  					type:"post",
  					data:{"menu":menu,"url":url,"des":des,"icon":icon,"pid":pid,"mids":mids},
  					success:function(obj){
  						if(obj==1){
  							alert("菜单修改成功");
  							location.href="${pageContext.request.contextPath }/admin/showSZMenu.action";//相当于刷新界
  						}else{
  							alert("菜单修改失败");
  							location.href="${pageContext.request.contextPath }/admin/showSZMenu.action";//相当于刷新界
  						}
  					}
  				});
  			}
  		});
  	});
    
    
window.onload=function(_this){
	$.ajax({
		url:"${pageContext.request.contextPath }/admin/showFuMenu.action",
		type:"get",
		success:function(data){
			 for(i in data){
				$("#updatemenu").append("<option value='"+data[i].id+"'>"+data[i].menu+"</option>");
			} 
		}
	});
} 
</script>
</div>
</body>
</html>