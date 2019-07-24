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
<form action="${pageContext.request.contextPath }/adminBorrow/showInvestList.action" method="post">
<div style="margin-bottom: 10px;margin-left: 10px">
        <a  id="daochu" class="btn btn-info btn-lg" href="${pageContext.request.contextPath }/adminBorrow/daochu/portInvestExcel.action" style="height: 40px">
          <span class="glyphicon glyphicon-share"></span> 导出Excel
        </a>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
      
     <span style="font-size: 18px;font-weight:bold;">时间周期</span>   <input size="15" id="sDatePicker" name="starttime" type="text" value="${istart }" readonly style="height: 35px" class="form_datetime" placeholder="请输入起始时间"/> 

     <span style="font-size: 18px;font-weight:bold;">至</span>   <input size="15" id="eDatePicker" name="endtime" type="text" value="${iend }" readonly style="height: 35px" class="form_datetime" placeholder="请输入结束时间"/>&nbsp;&nbsp;
     <input type="text" id="yonhum" name="names" calss="form-control" value="${inames }" placeholder="请输入用户姓名或真实姓名" style="height:35px "/>
     <button type="submit" class="btn btn-default btn-sm glyphicon glyphicon-search" id="getByTime" style="height: 35px" >搜索</button>
	</div>
</form>

<div class="" style="margin-left: 10px">
<table class="table table-striped" style="table-layout: fixed;">
	<thead>
	<tr class="warning">
			<td style="width: 60px">ID</td>
			<td>用户名</td>
			<td>借款金额</td>
			<td style="width: 220px">发标时间</td>
			<td>月利率</td>
			<td>借款期限</td>
			<td>发标期限</td>
			<td>联系电话</td>
			<td style="width: 260px">操作状态</td>
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="list" varStatus="in">
		
			<tr >
				<td>${in.count}</td>
				<td> ${list.borrow.users.name } </td>
				<td>${list.jemoney }</td>
				
				<td>
				<fmt:formatDate value="${list.biaodate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${list.borrow.rate/100 }</td>
				<td>${list.borrow.tlimit }</td>
				<td>${list.biaolimit }</td>
				<td>${list.borrow.tel }</td>
				<td align="center">
				<c:if test="${list.state==1}">
				<span class="label label-info" style="height: 100px;width: 200px;font-size: 20px">&nbsp;&nbsp;&nbsp;&nbsp;已发布的标&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
				<c:if test="${list.state==2}">
				<span class="label label-success" style="height: 100px;width: 200px;font-size: 20px">&nbsp;&nbsp;&nbsp;&nbsp;已完成的标&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
				<c:if test="${list.state==3}">
				<span class="label label-warning" style="height: 100px;width: 200px;font-size: 20px">&nbsp;&nbsp;&nbsp;&nbsp;已过期的标&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
				<c:if test="${list.state==4}">
				<span class="label label-success" style="height: 100px;width: 200px;font-size: 20px">&nbsp;&nbsp;&nbsp;&nbsp;已还款的标&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
				<input value="${list.id }" id="ids" class="ids" type="hidden"/>
				<input value="${list.borrow.users.name }" id="hidname" type="hidden"/> 
				
				
				</td>
			</tr>
		</c:forEach>
		
	</tbody>
	</table>
	<div class="pagination" style="float: right;">
	
		 	<a href="${pageContext.request.contextPath }/adminBorrow/showInvestList.action?pn=1&starttime=${istart}&endtime=${iend}&names=${inames}" class="firstPage">首页</a>
			 
			<c:if test="${page.hasPreviousPage }">
				<a  class="previousPage" href="${pageContext.request.contextPath }/adminBorrow/showInvestList.action?pn=${page.prePage}&starttime=${istart}&endtime=${iend}&names=${inames}">上一页</a>
			</c:if>	
			
				<c:forEach begin="1" end="${page.pages }" var="i">
					<a class="currentPage" href="${pageContext.request.contextPath }/adminBorrow/showInvestList.action?pn=${i }&starttime=${istart}&endtime=${iend}&names=${inames}">${i }</a>
				</c:forEach>
				
			<c:if test="${page.hasNextPage}">
				<a class="nextPage" href="${pageContext.request.contextPath }/adminBorrow/showInvestList.action?pn=${page.nextPage}&starttime=${istart}&endtime=${iend}&names=${inames}">下一页</a>
			</c:if>
			
			<a class="lastPage" href="${pageContext.request.contextPath }/adminBorrow/showInvestList.action?pn=${page.lastPage}&starttime=${istart}&endtime=${iend}&names=${inames}">尾页</a>
			 
	</div>
	
	
	

	</div>
	
</body>
</html>