<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<!-- 引入validator验证插件 -->
<link rel="stylesheet" href="plugins/validator/css/bootstrapValidator.min.css"/>
<script type="text/javascript" src="plugins/validator/js/bootstrapValidator.js"></script>
<!-- validator国际化（默认英文，下面的会覆盖上面的） -->
<script type="text/javascript" src="plugins/validator/js/language/zh_CN.js"></script>
<!-- 插件 -->
<script type="text/javascript" src="plugins/My97DatePicker/WdatePicker.js"></script>
<!-- 自定义模型js -->
<script type="text/javascript" src="js/model/purchasebill.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单编辑页面</title>
</head>
<body>
	<%-- <s:debug></s:debug> --%>
	<div class="main-content">
		<!-- 面包屑导航 -->
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i><a href="#">首页</a></li>
				<li><a href="#">所有表格</a></li>
				<li class="active">表格单元</li>
			</ul>
		</div>
		<!-- 页面内容 -->
		<div class="page-content">
			<!-- 页头 -->
			<div class="page-header">
				<h1>
					订单信息
				</h1>
			</div>
			<div class="row">					       <!-- ===提示：①使用s标签的时候-获取和提交都可以只用name。②不用s标签时-获取需要EL表达式$，提交需要name=== -->
				<div class="col-xs-12">													       <!-- .col-xs-* 针对超小屏幕和中等屏幕设备所定义的类 -->
					<!-- 页面内容开始 -->
					<form id="purchasebillForm" class="form-horizontal" action="purchasebill_save.action" role="form" method="post"><!-- form-horizontal将 label标签和控件组水平并排布局 -->
						<s:hidden id="userId" name="id"/>																   <!-- 解决修改回显问题 -->
						<s:hidden name="baseQuery.currentPage"/>
						<s:hidden name="baseQuery.pageSize"/>
						<div class="form-group">								      <!-- 改变 .form-group 的行为，使其表现为栅格系统中的行（row）， -->
							<label class="col-sm-1 control-label no-padding-right" for="form-field-1">采购员 </label>
							<div class="col-sm-1">	
								<s:select list="#allBuyers" name="buyer.id" listKey="id" listValue="username"/>										        <!-- .col-sm-栅格系统小屏幕 平板 (≥768px) -->
							</div>
						</div>
						<div class="form-group">								      <!-- 改变 .form-group 的行为，使其表现为栅格系统中的行（row）， -->
							<label class="col-sm-1 control-label no-padding-right" for="form-field-1">供应商 </label>
							<div class="col-sm-1">											        <!-- .col-sm-栅格系统小屏幕 平板 (≥768px) -->
								<s:select list="#allSuppliers" name="supplier.id" listKey="id" listValue="name"/>			
							</div>
						</div>
						<div class="form-group">								      <!-- 改变 .form-group 的行为，使其表现为栅格系统中的行（row）， -->
							<label class="col-sm-1 control-label no-padding-right" for="form-field-1">交易时间 </label>
							<div class="col-sm-1">											        <!-- .col-sm-栅格系统小屏幕 平板 (≥768px) -->
								<!-- 交易时间格式 -->
								<s:date name="vdate" format="yyyy-MM-dd" var="date"/>
								<s:textfield name="vdate" onclick="WdatePicker({maxDate:new Date()})" value="%{date}"
									style="height:30px;width:100px" cssClass="Wdate"/>
							</div>
						</div>
						<div class="page-header">
							<h1>
								订单明细
							</h1>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th width="14%">产品名称</th>
											<th width="8%">产品颜色</th>
											<th width="8%">产品图片</th>
											<th width="1%">采购价格</th>
											<th width="1%">采购数量</th>
											<th width="8%">采购小计</th>
											<th width="1%">备注</th>
											<th width="4%"><a id="addItem" class="glyphicon-plus" style="cursor:pointer">添加</a></th>
										</tr>
									</thead>
									<tbody id="itemBody">
										<s:if test="id==null">
											<tr>
												<td>
													<s:hidden diyCode="productId" name="purchasebillItems[0].product.id"/>
													<s:textfield diyCode="productName" readonly="true"/>
													<a diyCode="selectProduct" class="btn btn-warning btn-xs"><!-- 自定义属性 -->
														<i class="icon-wrench bigger-130 icon-only"></i>
													</a>
												</td>
												<td diyCode="productColor"></td>
												<td diyCode="productPic"></td>
												<td><s:textfield diyCode="productPrice" name="purchasebillItems[0].price" readonly="true"/></td>
												<td><s:textfield diyCode="productNum" name="purchasebillItems[0].num"/></td>
												<td diyCode="productAmount"></td>
												<td><s:textfield diyCode="productDescs" name="purchasebillItems[0].descs"/></td>
												<td>
													<a diyCode="deleteItem" class="btn btn-danger btn-xs">
														<i class="glyphicon-minus  bigger-120 icon-only"></i>
													</a>
												</td>
											</tr>
										</s:if>
										<s:iterator value="purchasebillItems">
											<tr>
												<td>
													<s:hidden diyCode="productId" name="product.id"/>
													<s:textfield diyCode="productName" value="%{product.name}" readonly="true"/>
													<a diyCode="selectProduct" class="btn btn-warning btn-xs"><!-- 自定义属性 -->
														<i class="icon-wrench bigger-130 icon-only"></i>
													</a>
												</td>
												<td diyCode="productColor">
													<span class="btn-colorpicker" style="background-color:${product.color};"></span>
												</td>
												<td diyCode="productPic">
													<s:if test="product.smallPic!=null">
														<div class="row-fluid">
															<ul class="ace-thumbnails">
																<li>
																	<a href="${product.pic}" data-rel="colorbox"> 
																		<img alt="100*150" src="${product.smallPic}"/>
																	</a>
																</li>
															</ul>
														</div>
													</s:if>
												</td>
												<td><s:textfield diyCode="productPrice" name="price" readonly="true"/></td>
												<td><s:textfield diyCode="productNum" name="num"/></td>
												<td diyCode="productAmount">${amount}</td>
												<td><s:textfield diyCode="productDescs" name="descs"/></td>
												<td>
													<a diyCode="deleteItem" class="btn btn-danger btn-xs">
														<i class="glyphicon-minus  bigger-120 icon-only"></i>
													</a>
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
						<!-- 按钮 -->
						<div class="clearfix form-actions">
							<!-- clearfix响应式列重置 -->
							<div class="col-md-offset-5">
								<!-- 使用 .col-md-offset-* 类可以将列向右侧偏移。 -->
								<button id="savePurchaseItems" class="btn btn-info" type="button">
									<i class="icon-ok bigger-110"></i> 保存
								</button>
								<button class="btn btn-success" type="button" onclick="clearForm()">
									<i class="icon-refresh bigger-110"></i> 清空
								</button>
								<button class="btn btn-warning" type="button" onclick="cancel('purchasebill')">
									<i class="icon-undo bigger-110"></i> 取消
								</button>
							</div>
						</div>
					</form>
					<!-- 表单 -->
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/purchasebill/productModal.jsp"%>
	<!-- 产品图片 -->
	<script type="text/javascript">
		jQuery(function($) {
			var colorbox_params = {
				rel: 'colorbox',
				reposition:true,
				scalePhotos:true,
				scrolling:false,
				previous:'<i class="ace-icon fa fa-arrow-left"></i>',
				next:'<i class="ace-icon fa fa-arrow-right"></i>',
				close:'&times;',
				current:'{current} of {total}',
				maxWidth:'100%',
				maxHeight:'100%',
				onOpen:function(){
					$overflow = document.body.style.overflow;
					document.body.style.overflow = 'hidden';
				},
				onClosed:function(){
					document.body.style.overflow = $overflow;
				},
				onComplete:function(){
					$.colorbox.resize();
				}
			};
			$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
		})
	</script>
</body>
</html>