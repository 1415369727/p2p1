<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	//查询下拉菜单
	$(function(){
		$(".form-control").change(function(){
			var id=$(this).val();
			location.href="${pageContext.request.contextPath }/admin/getRole.action?id="+id;//相当于刷新界
			/* $.ajax({
				url:"${pageContext.request.contextPath }/admin/getRole.action?id="+id,
				type:"get",
				success:function(){
					
				}
			}); */
		});
		

	});
</script>
</head>
<body>
<div style="margin-left: 30px">
<form action="${pageContext.request.contextPath }/admin/updateRoleMenu.action" method="post">
 <label for="name" style="font-size: 20px">请选择要分配的角色</label>
	<select name="roleid" id="roleid" class="form-control" style="width: 180px">
		<c:forEach items="${rolist}" var="rol">
		<c:if test="${rol.id!=-1 }">
		<option value="${rol.id }" ${rid==rol.id?'selected':'' }>${rol.rolename }</option>
		</c:if>
		</c:forEach>
	</select>
	<hr>
	
	<p style="color: #428bca;font-size: 20px">请选择您要为该角色分配的菜单:</p> 
	<c:forEach items="${menu }" var="m">
	<div>
	<label class="checkbox-inline">
        <input type="checkbox" id="allcheck" ${m.rmenu.rid==null?'':'checked' } class="allcheck"  name="ids" value="${m.id }" ><label for="name" style="font-size: 18px">${m.menu }</label> 
    </label>
     </div> 
  
	<div style="margin-top: 10px">
	
	<c:forEach items="${m.lmenu }" var="ml">
		<label class="checkbox-inline">
        <input type="checkbox" id="inlineCheckbox1" ${ml.rmenu.rid==null?'':'checked' } name="ids" id="pkIds"  value="${ml.id }"> <span style="font-size: 15px">${ml.menu }</span> 
     </c:forEach>
    	</label>
	</div>
 
	<hr>
</c:forEach>	
	<input type="submit" value="修改权限" class="btn btn-primary btn-lg">
	<font color="green" style="font-size: 30px">${msg }</font>
	</form>
</div>
<script type="text/javascript">
		
	 $(function(){
		//修改权限
			 
			 
		//复选框全选
		
		/*  $(".allcheck").click(function(){
			  if(this.checked){
				  
			  	$("input[name='ids']").attr("checked","checked");
			  	  
			  }else{
			  	  $("input[name='ids']").attr("checked",null);
			  }        
		 		}); 
			 */
		 		
		
		});
		
</script>
</body>
</html>