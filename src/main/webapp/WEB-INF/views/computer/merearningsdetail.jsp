<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<title>商户收益明细</title>
<script type="text/javascript" src="${hdpath}/jedate/jedate.js"></script>
<%@ include file="/WEB-INF/views/public/commons.jspf"%>
<link rel="stylesheet" href="${hdpath}/css/admin.css">
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
	<%@ include file="/WEB-INF/views/navigation.jsp"%>
</div>
<div class="leftnav" id="lefeMenu">
	<%@ include file="/WEB-INF/views/menu.jsp"%>
</div>
<div><%-- ${admin.realname == null ? admin.realname : admin.username} --%>
	<ul class="bread">
	  <li><a href="javascript:void(0)" target="right" class="icon-home">商户<span style="color: #e66161;">${admin.realname != null ? admin.realname : admin.username}</span>收益明细</a></li>
	</ul>
</div>
 <div class="admin">
	 <div class="panel admin-panel" id="adminPanel">
    		<div class="conditionsd">
			  <form method="post" action="${hdpath}/pcadminiStrator/selecearningsdetail" id="listform">
			    <div class="searchdiv">
			      <ul class="search" style="padding-left:10px;">
			       		<li>
			     		订单号&nbsp;&nbsp;
			     		<input type="text" placeholder="请输入订单号" name="ordernum" class="frame7" value="${ordernum}"/>
			     		<input type="hidden" name="merid" class="input" value="${admin.id}">
			     		</li>
			     		<li>支付方式&nbsp;&nbsp;
			     		<select name="paytype">
							<option value='' <c:if test="${paytype != 1 && paytype != 2 && paytype != 3}"> selected="selected"</c:if> >请选择</option>
							<option value="1" <c:if test="${paytype == 1}"> selected="selected"</c:if> >钱包</option>
							<option value="2" <c:if test="${paytype == 2}"> selected="selected"</c:if> >微信</option>
							<option value="3" <c:if test="${paytype == 3}"> selected="selected"</c:if> >支付宝</option>
						</select>
			     		</li>
			     		<li>类型&nbsp;&nbsp;
			     		<select name="status">
							<option value='' <c:if test="${status != 1 && status != 2}"> selected="selected"</c:if> >请选择</option>
							<option value="1" <c:if test="${status == 1}"> selected="selected"</c:if> >收入</option>
							<option value="2" <c:if test="${status == 2}"> selected="selected"</c:if> >退款</option>
						</select>
			     		</li>
			     		<li>时间:&nbsp;&nbsp;<input type="text" name="startTime" id="startTime" placeholder="请选择时间" value="${startTime}"
											 onClick="jeDate({dateCell:'#startTime',isTime:true,format:'YYYY-MM-DD hh:mm:ss'})" readonly="readonly">
							    &nbsp;&nbsp; —
								&nbsp;&nbsp;<input type="text" name="endTime" id="endTime" placeholder="请选择时间" value="${endTime}"
											onClick="jeDate({dateCell:'#endTime',isTime:true,format:'YYYY-MM-DD hh:mm:ss'})" readonly="readonly">
						</li>
			       		<li class="cmbquery">
			            	<input type="submit" style="width: 80px;" value="搜索">
			      	 	</li>
			        </ul>
			   </div>
			</form>
  		  </div>
		 <div class="table table-div">
			<table class="table table-hover" >
			    <thead>
				   <tr>
					<th>序号</th>
					<th>订单号</th>
					<th>金额</th>
					<th>余额</th>
					<th>支付方式</th>
					<th>类型</th>
				    <th>时间</th>
				   </tr>
				</thead>
				<tbody>
				   <c:forEach items="${earningsdetail}" var="earnings"  varStatus="as">
					  <tr id="name${earnings.id}">
					  	<td >${as.count+current}</td>
					    <td>
					    <c:choose>
					          	<c:when test="${earnings.paysource!=0}"><a href="/pcadminiStrator/earningsorderdetail?ordernum=${earnings.ordernum}&paysource=${earnings.paysource}&type=${earnings.status}">${earnings.ordernum}</a></c:when>
						       	<c:when test="${earnings.paysource==0}"><a href="#">${earnings.ordernum}</a></c:when>
					     </c:choose>
						</td>
						<td>
							<c:choose>
					          	<c:when test="${earnings.status == 1}"><font color='#00CC99'>+${earnings.money}</font></c:when>
						       	<c:when test="${earnings.status == 2}"><font color='#ef1111'>-${earnings.money}</font></c:when>
					       	</c:choose>
						</td>
						<td>${earnings.balance}</td>
						<td>${earnings.paytype == 0 ? "提现" : earnings.paytype == 1 ? "钱包" : earnings.paytype == 2 ? "微信" : earnings.paytype == 3 ? "支付宝" : "其它"}</td>
						<td>
							<c:choose>
					          	<c:when test="${earnings.status == 1}">${earnings.paysource==4 ? "提现" : "收入"}</c:when>
						       	<c:when test="${earnings.status == 2}">${earnings.paysource==4 ? "提现" : "退款"}</c:when>
					       	</c:choose>
						</td>
						<td><fmt:formatDate value="${earnings.create_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					   </tr>
					</c:forEach>
				 </tbody>
			   </table>
			 	<div align="center">
				<!-- 构建分页导航 -->
					共有${pageBean.totalRows}条数据，共${pageBean.totalPages}页，当前为${pageBean.currentPage}页
					<br/>
					 <span class="btn btn-success" onclick="currentPage(0)">首页</span>
					 <c:if test="${pageBean.currentPage >1}">
					 	<span class="btn btn-success" onclick="currentPage(2)">上一页</span>
					 </c:if>
					 
					 <c:forEach begin="${pageBean.start}" end="${pageBean.end}" step="1" var="i">
			        	<span onclick="currentPagenum(${i})">${i} </span>
					 </c:forEach>
					 
					 <c:if test="${pageBean.currentPage < pageBean.totalPages}">
					 	<span class="btn btn-success" onclick="currentPage(3)">下一页</span>
					 </c:if>
					<span class="btn btn-success" onclick="currentPage(1)">尾页</span>
					<form style="display: inline;">
						<input type="text" name="pageNumber" style="width: 50px"> 
						<input class="btn btn-info" type="button" onclick="currentPage(4)" value="go">
					</form>
				</div> 
   			</div>
         </div>
      </div>
</body>
<script type="text/javascript">
$(document).ready(function(){	
	$('#21'+' a').addClass('at');
	$('#21').parent().parent().parent().prev().removeClass("collapsed");
	$('#21').parent().parent().parent().prev().attr("aria-expanded",true)
	$('#21').parent().parent().parent().addClass("in");
	$('#21').parent().parent().parent().prev().attr("aria-expanded",true)
	})
</script>
<script type="text/javascript">
function currentPagenum(mark){//指定页
	var arguments = $("#listform").serialize()+"&currentPage="+mark;
	window.location.href="${pageContext.request.contextPath}/pcadminiStrator/selecearningsdetail?"+arguments; 
}
function  currentPage(mark){
	var arguments = $("#listform").serialize();
	if(mark==0){//首页
		arguments += "&currentPage=1"; 
	}else if(mark==1){//末页
		arguments += "&currentPage="+${pageBean.totalPages}; 
	}else if(mark==2){//上一页
		arguments += "&currentPage="+${pageBean.currentPage-1};
	}else if(mark==3){//下一页
		arguments += "&currentPage="+${pageBean.currentPage+1}; 
	}else if(mark==4){//go跳转页
		var currentPage = $("input[name='pageNumber']").val();
		if(currentPage==null ||currentPage==""){
			alert("请输入页码。");
			return;
		}
		arguments += "&currentPage="+currentPage; 
	}
	window.location.href="${pageContext.request.contextPath}/pcadminiStrator/selecearningsdetail?"+arguments; 
}
</script>
</html>