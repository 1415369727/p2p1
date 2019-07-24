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
	                    minuteStep:5, //分钟间隔为1分
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
<form action="${pageContext.request.contextPath }/touzi/myRecordList.action" method="post">
<div style="margin-bottom: 10px;margin-left: 10px">
        <a  id="daochu" class="btn btn-info btn-lg" href="${pageContext.request.contextPath }/touzi/recordExcel.action" style="height: 40px">
          <span class="glyphicon glyphicon-share"></span> 导出Excel
        </a>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
      
     <span style="font-size: 18px;font-weight:bold;">时间周期</span>   <input size="15" id="sDatePicker" name="starttime" type="text" value="${rtstart }" readonly style="height: 35px" class="form_datetime" placeholder="请输入起始时间"/> 

     <span style="font-size: 18px;font-weight:bold;">至</span>   <input size="15" id="eDatePicker" name="endtime" type="text" value="${rtend }" readonly style="height: 35px" class="form_datetime" placeholder="请输入结束时间"/>&nbsp;&nbsp;
     <input type="text" id="yonhum" name="names" calss="form-control" value="${rtnames }" placeholder="请输入投资人或被投资人姓名" style="height:35px ;width: 200px"/>
     <button type="submit" class="btn btn-default btn-sm glyphicon glyphicon-search" id="getByTime" style="height: 35px" >搜索</button>
	</div>
</form>

<div class="" style="margin-left: 10px">
<table class="table table-striped" style="table-layout: fixed;">
	<thead>
	<tr class="warning">
			<td style="width: 60px">ID</td>
			<td>交易人</td>
			<td>交易类型</td>
			<td>交易详情</td>
			<td>操作金额</td>
			<td style="width: 220px">交易时间</td>
			
			
			
			
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="list" varStatus="in">
		
			<tr >
				<td>${in.count}</td>
				<td>${list.user.name} </td>
				<td> 
				<c:if test="${list.recordtype eq '1'}">
						投资
				</c:if>
				<c:if test="${list.recordtype eq '2'}">
						借款
				</c:if>
				<c:if test="${list.recordtype eq '3'}">
						充值
				</c:if>
				<c:if test="${list.recordtype eq '4'}">
						提现
				</c:if>
				<c:if test="${list.recordtype eq '5'}">
						还款
				</c:if>
				<c:if test="${list.recordtype eq '6'}">
						回款
				</c:if>
				</td>
				<td> 
					<c:if test="${list.recordtype eq '1'}">
						投资成功
					</c:if>
					<c:if test="${list.recordtype eq '2'}">
						借款成功
					</c:if>
					<c:if test="${list.recordtype eq '3'}">
							充值成功
					</c:if>
					<c:if test="${list.recordtype eq '4'}">
							提现成功
					</c:if>
					<c:if test="${list.recordtype eq '5'}">
							还款成功
					</c:if>
					<c:if test="${list.recordtype eq '6'}">
							回款成功
					</c:if>
				 </td>
				<td>${list.recordmoney }</td>
				<td>
				<fmt:formatDate value="${list.recorddate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				
			</tr>
		</c:forEach>
		
	</tbody>
	</table>
	<div class="pagination" style="float: right;">
	
		 	 <a href="${pageContext.request.contextPath }/touzi/myRecordList.action?pn=1&starttime=${rtstart}&endtime=${rtend}&names=${rtnames}" class="firstPage">首页</a>
			 
			<c:if test="${page.hasPreviousPage }">
				<a  class="previousPage" href="${pageContext.request.contextPath }/touzi/myRecordList.action?pn=${page.prePage}&starttime=${rtstart}&endtime=${rtend}&names=${rtnames}">上一页</a>
			</c:if>	
			
				<c:forEach begin="1" end="${page.pages }" var="i">
					<a class="currentPage" href="${pageContext.request.contextPath }/touzi/myRecordList.action?pn=${i }&starttime=${rtstart}&endtime=${rtend}&names=${rtnames}">${i }</a>
				</c:forEach>
				
			<c:if test="${page.hasNextPage}">
				<a class="nextPage" href="${pageContext.request.contextPath }/touzi/myRecordList.action?pn=${page.nextPage}&starttime=${rtstart}&endtime=${rtend}&names=${rtnames}">下一页</a>
			</c:if>
			
			<a class="lastPage" href="${pageContext.request.contextPath }/touzi/myRecordList.action?pn=${page.lastPage}&starttime=${rtstart}&endtime=${rtend}&names=${rtnames}">尾页</a> 
			 
	</div>
	
	
	

	</div>
	
</body>
</html>