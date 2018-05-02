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
<!-- 自定义模型js -->
<script type="text/javascript" src="js/model/product.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product页面</title>
</head>
<body>
	<s:debug></s:debug>
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
				<li><i class="icon-home home-icon"></i><a href="/">首页</a></li>
				<li><a href="#">基础数据</a></li>
				<li class="active">产品管理</li>
			</ul>
		</div>
		<!-- 页面内容 -->
		<div class="page-content">
			<!-- 页头 -->
			<div class="page-header">
				<h1>产品管理
					<small>
						<i class="icon-double-angle-right"></i>
						<s:if test="id==null">添加产品</s:if>
						<s:else>编辑产品</s:else>
					</small>
				</h1>
			</div>
			<div class="row">					       <!-- ===提示：①使用s标签的时候-获取和提交都可以只用name。②不用s标签时-获取需要EL表达式$，提交需要name=== -->
				<div class="col-xs-12">													       <!-- .col-xs-* 针对超小屏幕和中等屏幕设备所定义的类 -->
					<!-- 页面内容开始 -->
					<form id="productForm" class="form-horizontal" action="product_save.action" role="form" method="post" enctype="multipart/form-data"><!-- form-horizontal将 label标签和控件组水平并排布局 -->
						<s:hidden id="userId" name="id"/>																   <!-- 解决修改回显问题 -->
						<s:hidden name="baseQuery.currentPage"/>
						<s:hidden name="baseQuery.pageSize"/>
						<div class="form-group">								      <!-- 改变 .form-group 的行为，使其表现为栅格系统中的行（row）， -->
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">名称 </label>
							<div class="col-sm-9">											        <!-- .col-sm-栅格系统小屏幕 平板 (≥768px) -->
								<input type="text" id="form-field-1" placeholder="请输入产品名称..." value="${name}" name="name" class="col-xs-10 col-sm-5"/>
							</div>
						</div>
						<div class="form-group">
							<!-- 改变 .form-group 的行为，使其表现为栅格系统中的行（row）， -->
							<label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">颜色</label>
							<div class="col-sm-9">
								<s:select id="simple-colorpicker-1" name="color" list="{'green','red','blue','black','grey','yellow','purple','while'}" />
							</div>
						</div>
						<div class="form-group">								      <!-- 改变 .form-group 的行为，使其表现为栅格系统中的行（row）， -->
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">成本价格 </label>
							<div class="col-sm-9">											        <!-- .col-sm-栅格系统小屏幕 平板 (≥768px) -->
								<input type="text" id="form-field-1" placeholder="请输入成本价格..." value="${costPrice}" name="costPrice" class="col-xs-10 col-sm-5"/>
							</div>
						</div>
						<div class="form-group">								      <!-- 改变 .form-group 的行为，使其表现为栅格系统中的行（row）， -->
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">销售价格</label>
							<div class="col-sm-9">											        <!-- .col-sm-栅格系统小屏幕 平板 (≥768px) -->
								<input type="text" id="form-field-1" placeholder="请输入销售价格..." value="${salePrice}" name="salePrice" class="col-xs-10 col-sm-5"/>
							</div>
						</div>
						<div class="form-group">								      <!-- 改变 .form-group 的行为，使其表现为栅格系统中的行（row）， -->
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">品牌</label>
							<div class="col-sm-9">											        <!-- .col-sm-栅格系统小屏幕 平板 (≥768px) -->
								<s:select list="#allBrands" name="brand.id" listKey="id" listValue="name"/>
							</div>
						</div>
						<div class="form-group">								      <!-- 改变 .form-group 的行为，使其表现为栅格系统中的行（row）， -->
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">单位</label>
							<div class="col-sm-9">											        <!-- .col-sm-栅格系统小屏幕 平板 (≥768px) -->
								<s:select list="#allUnits" name="unit.id" listKey="id" listValue="name"/>
							</div>
						</div>
						<div class="form-group">								      <!-- 改变 .form-group 的行为，使其表现为栅格系统中的行（row）， -->
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">产品类型</label>
							<div class="col-sm-9">											        <!-- .col-sm-栅格系统小屏幕 平板 (≥768px) -->
								<s:select list="#productParentTypes" id="parent" name="productType.parentProductType.id" 
									listKey="id" listValue="name" headerKey="-1" headerValue="--请选择--"/>
								<s:select list="#productChilrenTypes" id="children" name="productType.id" 
									listKey="id" listValue="name"/>
							</div>
						</div>
						<div class="form-group">				 				      <!-- 改变 .form-group 的行为，使其表现为栅格系统中的行（row）， -->
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">图片 </label>
							<div class="col-sm-9" style="width: 35%">
								<input id="id-input-file-2" name="uploadProductPicture" type="file"> 
									<label class="file-label" data-title="Choose"> 
									<span class="file-name" data-title="No File ..."> </span>
								</label>
							</div>
						</div>
						<!-- 按钮 -->
						<div class="clearfix form-actions">														 <!-- clearfix响应式列重置 -->
							<div class="col-md-offset-5">									  <!-- 使用 .col-md-offset-* 类可以将列向右侧偏移。 -->
								<button class="btn btn-info" type="submit">
									<i class="icon-ok bigger-110"></i> 保存
								</button>
								<button class="btn btn-success" type="button" onclick="clearForm()">
									<i class="icon-refresh bigger-110"></i> 清空
								</button>
								<button class="btn btn-warning" type="button" onclick="cancel('product')">
									<i class="icon-undo bigger-110"></i> 取消
								</button>
							</div>
						</div>
					</form>
					<!-- 表单 -->
				</div>
			</div>
		</div>
		<!-- 页面内容 -->
	</div>
	<script src="assets/js/ace-elements.min.js"></script>
	<script type="text/javascript">
		$(function() {
			//处理颜色选择框
			$('#simple-colorpicker-1').ace_colorpicker();
			//处理图片上传控件
			$('#id-input-file-2').ace_file_input({
				no_file : '选择图片 ...',
				btn_choose : '选择',
				btn_change : '改变',
				droppable : false,
				onchange : null,
				thumbnail : false
			});
		});
	</script>
</body>
</html>