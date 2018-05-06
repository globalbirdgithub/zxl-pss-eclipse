<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>订单管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript" src="plugins/My97DatePicker/WdatePicker.js"></script>
<!-- 自定义模型js -->
<script type="text/javascript" src="js/model/purchasebill.js"></script>
</head>
<body>
	<%-- <s:debug></s:debug> --%>
	<div class="main-content">
	
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i><a href="#">首页</a></li>
				<li><a href="#">表格</a></li>
				<li class="active">动态</li>
			</ul>
			<!-- .breadcrumb -->
		</div>

		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<div class="table-header">采购订单列表</div>
					<div class="table-responsive">
						<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
						
							<div class="row">
								<form action="purchasebill.action" id="domainForm" method="post">
									<div class="col-sm-5">
										<div>
											<label> 显示 <s:select list="{10,20,30,40,50}" name="baseQuery.pageSize" onchange="javascript:$('#domainForm').submit();"></s:select></label>
											跳转<input type="text" id="pageNum" name="baseQuery.currentPage" value="${pageList.currentPage}" size="2" oncontextmenu="return false" />
											<button class="btn-pink" type="submit">GO</button>
										</div>
									</div>
									<div class="col-sm-7">
										<!-- 回显查询时间格式 -->
										<s:date name="baseQuery.beginDate" format="yyyy-MM-dd" var="bdate"/>
										<s:date name="baseQuery.endDate" format="yyyy-MM-dd" var="edate"/>
										交易时间从<s:textfield name="baseQuery.beginDate" value="%{bdate}" onclick="WdatePicker({maxDate:new Date()})" 
											cssClass="Wdate" style="height:30px;width:100px"/>
										到<s:textfield name="baseQuery.endDate" value="%{edate}" onclick="WdatePicker({maxDate:new Date()})" 
											cssClass="Wdate" style="height:30px;width:100px"/>
										审核状态<s:select list="#{-2:'--请选择--',0:'待审',1:'已审',-1:'作废'}" name="baseQuery.status" />
										<button class="btn btn-xs btn-pink" href="#" onclick="javascript:$('#domainForm').submit();">
											 <i class="icon-search">搜索</i>
										</button>
										<a class="btn btn-xs btn-pink" href="purchasebill_input.action">
											<i class="icon-credit-card">新建</i>	
										</a>
									</div>
								</form>
							</div>
							
							<table id="sample-table-2" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center" width="5%">
											<label> 
												<input type="checkbox" class="ace" /><span class="lbl"></span>
											</label>
										</th>
										<th width="4%">编号</th>
										<th width="6%">总数量/件</th>
										<th width="6%">总计/元</th>
										<th width="7%">供应商</th>
										<th width="5%">采购员</th>
										<th width="12%">填写时间</th>
										<th width="5%">填写人</th>
										<th width="6%">审核状态</th>
										<th width="5%">审核人</th>
										<th width="12%">审核时间</th>
										<th width="12%">交易时间</th>
										<th width="5%">审核</th>
										<th width="5%">操作</th>
									</tr>
								</thead>
								<tbody id="itemTbody">
									<s:iterator value="pageList.rows">
										<s:if test="id==#parameters.id[0]">
											<tr style="color:red">
										</s:if>
										<s:else>
											<tr>
										</s:else>
											<td class="center">
												<label> 
													<input type="checkbox" class="ace" /><span class="lbl"></span>
												</label>
											</td>
											<td>${id}</td>
											<td>${totalNum}</td>
											<td>${totalAmount}</td>
											<td>${supplier.name}</td>
											<td>${buyer.username}</td>
											<td>${inputTime}</td>
											<td>${inputUser.username}</td>
											<td>
												<s:if test="status==0"><span class="label label-warning">待审</span></s:if>
												<s:elseif test="status==1"><span class="label label-success">已审</span></s:elseif>
												<s:else><span class="label">作废</span></s:else>
											</td>
											<td>${auditor.username}</td>
											<td>${auditorTime}</td>
											<td>${vdate}</td>
											<td>
												<s:if test="status==0">
													<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
														<a class="blue" href="#" onclick="confirmPurchasebill('purchasebill_confirm.action?id=${id}',this)">
															<i class="icon-file bigger-130" title="审核"></i>
														</a>
														<a class="red" href="#" onclick="abandonPurchasebill('purchasebill_abandon.action?id=${id}',this)">
															<i class="icon-bookmark bigger-130" title="作废"></i>
														</a>
													</div>
												</s:if>
											</td>
											<td>
												<s:if test="status==0">
													<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
														<a class="green" href="#" onclick="updateDomain('purchasebill_input.action?id=${id}')">
															<i class="icon-pencil bigger-130" title="修改"></i>
														</a> 
														<a class="red" href="#" onclick="deleteDomain('purchasebill_delete.action?id=${id}',this)">
															<i class="icon-trash bigger-130" title="删除"></i>
														</a>
													</div>
												</s:if>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
							<%@ include file="/WEB-INF/views/common/page.jsp" %>
							<%@ include file="/WEB-INF/views/common/modal.jsp" %>
						</div>
					</div>
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
	</div><!-- /.main-content -->
</body>
</html>