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
<script type="text/javascript" src="js/model/permission.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Permission页面</title>
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
					添加/编辑 
					<small>
						<i class="icon-double-angle-right"></i>添加或编辑注册会员
					</small>
				</h1>
			</div>
			<div class="row">					       <!-- ===提示：①使用s标签的时候-获取和提交都可以只用name。②不用s标签时-获取需要EL表达式$，提交需要name=== -->
				<div class="col-xs-12">													       <!-- .col-xs-* 针对超小屏幕和中等屏幕设备所定义的类 -->
					<!-- 页面内容开始 -->
					<form id="permissionForm" class="form-horizontal" action="permission_save.action" role="form" method="post"><!-- form-horizontal将 label标签和控件组水平并排布局 -->
						<s:hidden id="userId" name="id"/>																   <!-- 解决修改回显问题 -->
						<s:hidden name="baseQuery.currentPage"/>
						<s:hidden name="baseQuery.pageSize"/>
						<div class="space-4"></div>
						<div class="form-group">								      <!-- 改变 .form-group 的行为，使其表现为栅格系统中的行（row）， -->
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">名称 </label>
							<div class="col-sm-9">											        <!-- .col-sm-栅格系统小屏幕 平板 (≥768px) -->
								<input type="text" id="form-field-1" placeholder="请输入名称..." value="${name}" name="name" class="col-xs-10 col-sm-5"/>
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
								<button class="btn btn-warning" type="button" onclick="cancel()">
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
</body>
</html>