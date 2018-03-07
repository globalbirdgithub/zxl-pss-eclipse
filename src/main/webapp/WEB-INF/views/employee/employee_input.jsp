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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//信息重置功能js
	function clearForm(){
		$(':input','form')																	 //匹配所有 input, textarea, select 和 button 元素  
		 .not(':button, :submit, :reset, :hidden') 															      //去除所有与给定选择器匹配的元素
		 .val('')  																									 //获得/设置匹配元素的当前值
		 .removeAttr('checked')  																				 //从每一个匹配的元素中删除一个属性
		 .removeAttr('selected'); 
		$('select').prop('selectedIndex', 0);						//选中'---请选择---',jquery1.6以下版本$('select').attr('selectedIndex', 0)
	}																			   //jquery1.6或以上版本$('select').prop('selectedIndex', 0)
	//字段验证功能js
	$(function(){
		$("#employeeFrom").bootstrapValidator({
			live:'submitted',                                                                                                //验证生效规则
	        message: '值不能为空',                                                                                              //字段通用提示语
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	            username: {
	            	threshold:6,
	                validators: {
	                    notEmpty:{message:'请输入用户名'},
	                    regexp: {
	                        regexp: /^[a-zA-Z0-9_\.]+$/,
	                        message:'请输入英文或者数字'
	                    },
				        stringLength: {
			                min: 6,
			                max: 30,
			                message:'请输入6至30个字符'
			            },
				        remote: {
		                    url: 'employee_checkUsername.action?id='+$('#username').val(),
		                    delay : 2000,
		                    message: '此用户名已存在'
		                }
	                }
	            },
		        password: {
	                validators: {
	                    notEmpty: {
	                        message: '密码不能为空'
	                    },
	                    identical: {
	                        field: 'confirmPassword',
	                        message: '密码和验证密码不一致'
	                    },
	                    different: {
	                        field: 'username',
	                        message: '密码不能和用户名一样'
	                    }
	                }
	            },
	            confirmPassword: {
	                validators: {
	                    notEmpty: {
	                        message: '验证密码不能为空'
	                    },
	                    identical: {
	                        field: 'password',
	                        message: '验证密码和密码不一致'
	                    },
	                    different: {
	                        field: 'username',
	                        message: '密码不能和用户名一样'
	                    }
	                }
	            },
	            email:{
	                validators: {
	                    emailAddress: {
	                        message: '请输入有效的邮箱'
	                    }
	                }
	            },
	            age:{
	            	validators:{
	            		notEmpty:{message:'请输入年龄'},
	            		integer:true,
	            		between:{
	            			min:18,
	            			max:80
	            		}
	            	}
	            }
	        }
		})
	});
</script>
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
					添加/编辑 
					<small>
						<i class="icon-double-angle-right"></i>添加或编辑注册会员
					</small>
				</h1>
			</div>
			<div class="row">					       <!-- ===提示：①使用s标签的时候-获取和提交都可以只用name。②不用s标签时-获取需要EL表达式$，提交需要name=== -->
				<div class="col-xs-12">													       <!-- .col-xs-* 针对超小屏幕和中等屏幕设备所定义的类 -->
					<!-- 页面内容开始 -->
					<form id="employeeFrom" class="form-horizontal" action="employee_save.action" role="form" method="post"><!-- form-horizontal将 label标签和控件组水平并排布局 -->
						<s:hidden id="username" name="id"/>																   <!-- 解决修改回显问题 -->
						<div class="space-4"></div>
						<div class="form-group">								      <!-- 改变 .form-group 的行为，使其表现为栅格系统中的行（row）， -->
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1">用户名 </label>
							<div class="col-sm-9">											        <!-- .col-sm-栅格系统小屏幕 平板 (≥768px) -->
								<input type="text" id="form-field-1" placeholder="请输入用户名..." value="${username}" name="username" class="col-xs-10 col-sm-5"/>
							</div>
						</div>
						<div class="space-4"></div>
						<s:if test="id==null"><!-- 只有新增才可以修改密码 -->
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-2">密码 </label>
								<div class="col-sm-9">
									<input type="password" id="form-field-2" placeholder="请输入密码..." value="${password}" name="password" class="col-xs-10 col-sm-5" /> 
								</div>
							</div>
							<div class="space-4"></div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-2">确认密码 </label>
								<div class="col-sm-9">
									<input type="password" id="form-field-2" placeholder="确认密码..." name="confirmPassword" class="col-xs-10 col-sm-5" /> 
								</div>
							</div>
						</s:if>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-2">邮箱 </label>
							<div class="col-sm-9">
								<input type="text" id="form-field-2" placeholder="请输入邮箱..." value="${email}" name="email" class="col-xs-10 col-sm-5" /> 
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-2">年龄 </label>
							<div class="col-sm-9">
								<input type="text" id="form-field-2" placeholder="请输入年龄..." value="${age}" name="age" class="col-xs-10 col-sm-5" /> 
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-2">部门 </label>
							<div class="col-sm-9">
								<s:select list="#allDepts" name="department.id" listKey="id" listValue="name" headerKey="-1" headerValue="---请选择---" />
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
								<button class="btn btn-warning" type="cancel">
									<i class="icon-undo bigger-110"></i> 取消
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- 页面内容 -->
	</div>
</body>
</html>