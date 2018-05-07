<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>PurchasebillItem</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/views/common/head.jsp"%>
<script type="text/javascript" src="js/model/purchasebillItem.js"></script>
<script type="text/javascript" src="plugins/My97DatePicker/WdatePicker.js"></script>
<script src="plugins/highcharts/js/highcharts.js"></script>
<script src="plugins/highcharts/js/highcharts-3d.js"></script>
<script src="plugins/highcharts/js/modules/exporting.js"></script>
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
					<div class="table-header">订单交易报表</div>
					<div class="table-responsive">
						<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
						
							<div class="row">
								<form action="purchasebillItem.action" id="domainForm" method="post">
									<div class="col-sm-7">
										<!-- 回显查询时间格式 -->
										<s:date name="baseQuery.beginDate" format="yyyy-MM-dd" var="bdate"/>
										<s:date name="baseQuery.endDate" format="yyyy-MM-dd" var="edate"/>
										交易时间从<s:textfield name="baseQuery.beginDate" value="%{bdate}" onclick="WdatePicker({maxDate:new Date()})" 
											cssClass="Wdate" style="height:30px;width:100px"/>
										到<s:textfield name="baseQuery.endDate" value="%{edate}" onclick="WdatePicker({maxDate:new Date()})" 
											cssClass="Wdate" style="height:30px;width:100px"/>
										审核状态<s:select list="#{-2:'--请选择--',0:'待审',1:'已审',-1:'作废'}" name="baseQuery.status" />
										分组<s:select list="#{'o.purchasebill.supplier.name':'供应商分组','o.purchasebill.buyer.username':'采购员分组','month(o.purchasebill.vdate)':'月份分组'}" 
											 name="baseQuery.groupBy" onchange="javascript:$('#domainForm').submit()"/>
										<button class="btn btn-xs btn-pink" href="#" onclick="javascript:$('#domainForm').submit();">
											 <i class="icon-search">搜索</i>
										</button>
										<a data-url="purchasebillItem_chart3d.action" class="btn btn-xs btn-pink" href="#">3D</a>
										<a data-url="purchasebillItem_chart2d.action" class="btn btn-xs btn-pink" href="#">2D</a>
									</div>
								</form>
							</div>
							
							<table id="sample-table-2" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th width="4%">明细编号</th>
										<th width="6%">供应商</th>
										<th width="6%">采购员</th>
										<th width="10%">交易时间</th>
										<th width="10%">产品名称</th>
										<th width="10%">产品类型名称</th>
										<th width="6%">采购价格</th>
										<th width="6%">采购数量</th>
										<th width="6%">小计</th>
										<th width="6%">状态</th>
									</tr>
								</thead>
								<tbody id="itemTbody">
									<s:iterator value="#list" var="objects">
										<tr>
											<td colspan="10" align="left">${objects[0]}-${objects[1]}条记录</td>
										</tr>
										<s:set value="0" var="totalNum"/>
										<s:set value="0" var="totalAmount"/>
										<s:iterator value="findItems(#objects[0])">
											<tr align="center">
												<td>${id}</td>
												<td>${purchasebill.supplier.name}</td>
												<td>${purchasebill.buyer.username}</td>
												<td>${purchasebill.vdate}</td>
												<td>${product.name}</td>
												<td>${product.productType.name}</td>
												<td>${price}</td> 
												<td>${num}</td>
												<td>${amount}</td>
												<td>
													<s:if test="purchasebill.status==0"><span class="label label-warning">待审</span></s:if>
													<s:elseif test="purchasebill.status==1"><span class="label label-success">已审</span></s:elseif>
													<s:else><span class="label">作废</span></s:else>
												</td>
											</tr>
											<s:set value="#totalNum+num" var="totalNum"/>
											<s:set value="#totalAmount+amount" var="totalAmount"/>
										</s:iterator>
										<tr style="color: red" align="center">
											<td colspan="7" align="left">合计</td>
											<td>${totalNum}</td>
											<td>${totalAmount}</td>
											<td></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					</div>
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
	</div><!-- /.main-content -->
	<%@include file="highchartsModal.jsp"%>
</body>
</html>