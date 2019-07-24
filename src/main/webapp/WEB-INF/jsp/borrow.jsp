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
<script src="${pageContext.request.contextPath }/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap-datetimepicker.zh-CN.js"></script>
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	$(function(){
		 $("#sDatePicker").datetimepicker(
	                {
	                    language: 'zh-CN',
	                    autoclose: true,//选中之后自动隐藏日期选择框
	                    clearBtn: false,//清除按钮
	                    todayBtn: true,//今日按钮
	                    format: 'yyyy-mm-dd hh:ii:ss',
	                    startView: 'month',// 进来是月
	                    minView: 'hour',// 可以看到小时
	                    minuteStep:5, //分钟间隔为1分
	                    todayHighlight: false,
	                    forceParse: true,
	                    endDate: new Date()
	                }).on('changeDate', function (ev) {
	                    if (ev.date) {
	                        $("#eDatePicker").datetimepicker('setStartDate', new Date(ev.date.valueOf()))
	                    } else {
	                        $("#eDatePicker").datetimepicker('setStartDate', null);
	                    }
	                });

	            $("#eDatePicker").datetimepicker(
	                {
	                    language: 'zh-CN',
	                    autoclose: true,//选中之后自动隐藏日期选择框
	                    clearBtn: false,//清除按钮
	                    todayBtn: true,//今日按钮
	                    format: 'yyyy-mm-dd hh:ii:ss',
	                    startView: 'month',// 进来是月
	                    minView: 'hour',// 可以看到小时
	                    minuteStep:5, //分钟间隔为5分
	                    todayHighlight: false,
	                    forceParse: true
	                }).on('changeDate', function (ev) {
	                    if (ev.date) {
	                        $("#sDatePicker").datetimepicker('setEndDate', new Date(ev.date.valueOf()))
	                    } else {
	                        $("#sDatePicker").datetimepicker('setEndDate', new Date());
	                    }

	                });
	});
</script>
<style type="text/css">

</style>
</head>
<body>
<script type="text/javascript">
	$(function(){
	/* $("#daochu").click(function(){
		$.ajax({
			url:"${pageContext.request.contextPath }/adminBorrow/daochu/portExcel.action",
			type:"get",
			success:function(data){
				if(data==1){
					alert("Excel导出成功，请到E盘中查看");
				}
			}
		});
	}); */
	
	//根据时间查询
	/* $("#getByTime").click(function(){
		
	var starttime=$("#sDatePicker").val();
	var endtime=$("#eDatePicker").val();
		$.ajax({
			url:"${pageContext.request.contextPath }/adminBorrow/showBorrow.action",
			type:"post",
			data:{"starttime":starttime,"endtime":endtime},
			success:function(obj){
				
			}
		});
	}); */
	 //发标
	 $(".fabiao").click(function(){
		 var bid=$(this).siblings("#ids").val();
		var hname=$(this).siblings("#hidname").val();
		$.ajax({
			url:"${pageContext.request.contextPath }/adminBorrow/updateBiao.action?id="+bid,
			type:"get",
			success:function(data){
				$("#mids").val(data.id);
			}
		});
	 });
});
</script>
<form action="${pageContext.request.contextPath }/adminBorrow/showBorrow.action" method="post">
<div style="margin-bottom: 10px;margin-left: 10px">
        <a  id="daochu" class="btn btn-info btn-lg" href="${pageContext.request.contextPath }/adminBorrow/daochu/portExcel.action" style="height: 40px">
          <span class="glyphicon glyphicon-share"></span> 导出Excel
        </a>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
      
     <span style="font-size: 18px;font-weight:bold;">时间周期</span>   <input size="15" id="sDatePicker" name="starttime" type="text" value="${start }" readonly style="height: 35px" class="form_datetime" placeholder="请输入起始时间"/> 

     <span style="font-size: 18px;font-weight:bold;">至</span>   <input size="15" id="eDatePicker" name="endtime" type="text" value="${end }" readonly style="height: 35px" class="form_datetime" placeholder="请输入结束时间"/>&nbsp;&nbsp;
     <input type="text" id="yonhum" name="names" calss="form-control" value="${names }" placeholder="请输入用户姓名或真实姓名" style="height:35px "/>
     <button type="submit" class="btn btn-default btn-sm glyphicon glyphicon-search" id="getByTime" style="height: 35px" >搜索</button>
	</div>
</form>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                   发布标
                </h4>
            </div>
            <form action="${pageContext.request.contextPath }/adminBorrow/insertInvest.action" role="form" method="post">
            <div class="modal-body">
              	<input type="hidden" id="mids" name="id">
              	<input type="hidden" id="hnames" name="hnames">
					  <div class="form-group">
					    <label for="name">项目名</label>
					    <input type="text" class="form-control" id="proname" name="proname" placeholder="请输入项目名称" style="width: 400px">
					  </div>
					   <div class="form-group">
					    <label for="name">标期限</label>
					    <select  class="form-control" id="smallCategory" name="biaolimit" style="width: 180px">
					    	<option value="3">3天</option>
					    	<option value="7">7天</option>
					    	<option value="10">10天</option>
					    	<option value="15">15天</option>	
					    </select>
					  </div>
					   <div class="form-group">
					    <label for="name">信用等级</label>
					    <select  class="form-control" id="xydj" name="xydj" style="width: 180px">
					    	<option value="A">A级</option>
					    	<option value="B">B级</option>
					    	
					    </select>
					  </div>
					<div class="form-group">
					    <label for="name">详情描述</label>
					    <textarea type="text" class="form-control" id="des" name="des" style="width: 400px"></textarea>
					  </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <input type="button" class="btn btn-primary isertInvest" value="提交更改">
                  
                </button>
            </div>
            </form>
            <script type="text/javascript">
            //发标
				$(function(){
					$(".isertInvest").click(function(){
						var proname=$("#proname").val();
						var biaolimit=$("#smallCategory").val();
						var des=$("#des").val();
						var id=$("#mids").val();
						var uid=$("#uid").val();
						var xydj=$("#xydj").val();
						var ts=confirm("您确定要发布该标吗？");	
						if(ts==true){
							$.ajax({
								url:"${pageContext.request.contextPath }/adminBorrow/insertInvest.action",
								type:"post",
								data:{"proname":proname,"biaolimit":biaolimit,"id":id,"des":des,"xydj":xydj,"uid":uid},
								success:function(obj){
									if(obj==1){
										alert("标发布成功，请到标管理中查看！");
										location.href="${pageContext.request.contextPath }/adminBorrow/showBorrow.action";//相当于刷新界
									}else{
										alert("标发布失败");
										location.href="${pageContext.request.contextPath }/adminBorrow/showBorrow.action";//相当于刷新界
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
			<td>申请金额</td>
			<td style="width: 180px">申请时间</td>
			<td style="width: 60px">月利率</td>
			<td>借款期限</td>
			<td>月收入</td>
			<td style="width: 120px">联系电话</td>
			<td>借款状态</td>
			<td style="width: 260px">操作状态</td>
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="list" varStatus="in">
		
			<tr >
				<td>${in.count}</td>
				<td>${list.users.name }</td>
				<td>${list.jemoney }</td>
				
				<td>
				<fmt:formatDate value="${list.sqdate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${list.rate/100 }</td>
				<td>${list.tlimit }</td>
				<td>${list.yuecom }</td>
				<td>${list.tel }</td>
				<c:if test="${list.state==1 }">
					<td>借款申请中</td>
				</c:if>
				<c:if test="${list.state==2 }">
					<td>审核通过</td>
				</c:if>
				<c:if test="${list.state==3 }">
					<td>审核未通过</td>
				</c:if>
				<c:if test="${list.state==4 }">
					<td>已发标</td>
				</c:if>
				<td align="center">
				<c:if test="${list.state==1 }">
				<button  class="btn btn-success shenhetongguo">审核通过</button>&nbsp;
				</c:if>
				<c:if test="${list.state==2 }">
				
				<!-- <span class="label label-primary" style="height: 100px;width: 100px;">&nbsp;&nbsp;&nbsp;&nbsp;审核已经通过&nbsp;&nbsp;&nbsp;&nbsp;</span> -->
				<button  class="btn btn-success fabiao" data-toggle="modal" data-target="#myModal">发标</button>&nbsp;
				<button  class="btn btn-info">审核已经通过</button>&nbsp;
				</c:if>
				<c:if test="${list.state==1 }">
				
				<button class="btn btn-warning updapass">审核不通过</button> 
				</c:if >
				<c:if test="${list.state==3}">
				
				<span class="label label-info" style="height: 100px;width: 200px;font-size: 20px">&nbsp;&nbsp;&nbsp;&nbsp;审核没有通过&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
				<c:if test="${list.state==4}">
				
				<span class="label label-info" style="height: 100px;width: 200px;font-size: 20px">&nbsp;&nbsp;&nbsp;&nbsp;标已发布成功&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
				<input value="${list.id }" id="ids" class="ids" type="hidden"/>
				 <input value="${list.users.name }" id="hidname" type="hidden"/>
				<input value="${list.uid }" id="uid" class="ids" type="hidden"/>
				
				</td>
			</tr>
		</c:forEach>
		
	</tbody>
	</table>
	
	
	
	
	
	
	
	
	<script type="text/javascript">
	$(function(){
		
		
		
		//审核通过
		$(".shenhetongguo").click(function(){
			var bid=$(this).siblings("#ids").val();
			var hname=$(this).siblings("#hidname").val();
			var uid=$(this).siblings("#uid").val();
			var ts=confirm("确定要审核通过  "+hname+"  的借款申请吗？");
			if(ts==true){
				$.ajax({
					url:"${pageContext.request.contextPath }/adminBorrow/updateTongg.action?id="+bid+"&uid="+uid,
					type:"get",
					success:function(data){
						if(data=="1"){
							alert(hname+"  的借款申请审核已经通过");
							location.href="${pageContext.request.contextPath }/adminBorrow/showBorrow.action";//相当于刷新界
						}
					}
				});
				
			}else{
				location.href="${pageContext.request.contextPath }/adminBorrow/showBorrow.action";//相当于刷新界
			}
		});

		//审核不通过
		$(".btn-warning").click(function(){
			var id=$(this).siblings("#ids").val();
			var hname=$(this).siblings("#hidname").val();
			var ts=confirm("确定要审核 不 通过  "+hname+"  的借款申请吗？");
			if(ts==true){
			$.ajax({
				url:"${pageContext.request.contextPath }/adminBorrow/updateBuTongg.action?id="+id,
				type:"get",
				success:function(data){
					if(data=="1"){
						alert(hname+"  的借款申请审核没有通过");
						location.href="${pageContext.request.contextPath }/adminBorrow/showBorrow.action";//相当于刷新界
					}
				}
			});
			location.href="${pageContext.request.contextPath }/adminBorrow/showBorrow.action";//相当于刷新界
			}
		});
	});
	</script>
	
	<div class="pagination" style="float: right;">
	
		 	<a href="${pageContext.request.contextPath }/adminBorrow/showBorrow.action?pn=1&starttime=${start}&endtime=${end}&names=${names}" class="firstPage">首页</a>
			 
			<c:if test="${page.hasPreviousPage }">
				<a  class="previousPage" href="${pageContext.request.contextPath }/adminBorrow/showBorrow.action?pn=${page.prePage}&starttime=${start}&endtime=${end}&names=${names}">上一页</a>
			</c:if>	
			
				<c:forEach begin="1" end="${page.pages }" var="i">
					<a class="currentPage" href="${pageContext.request.contextPath }/adminBorrow/showBorrow.action?pn=${i }&starttime=${start}&endtime=${end}&names=${names}">${i }</a>
				</c:forEach>
				
			<c:if test="${page.hasNextPage}">
				<a class="nextPage" href="${pageContext.request.contextPath }/adminBorrow/showBorrow.action?pn=${page.nextPage}&starttime=${start}&endtime=${end}&names=${names}">下一页</a>
			</c:if>
			
			<a class="lastPage" href="${pageContext.request.contextPath }/adminBorrow/showBorrow.action?pn=${page.lastPage}&starttime=${start}&endtime=${end}&names=${names}">尾页</a>
			 
	</div>
	
	
	

	</div>
	
</body>
</html>