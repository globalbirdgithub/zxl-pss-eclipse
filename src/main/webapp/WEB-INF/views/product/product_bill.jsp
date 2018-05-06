<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Product</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/views/common/head.jsp"%>
</head>
<body>
	<div class="main-content">
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<div class="table-header">产品列表</div>
					<table id="sample-table-2" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th width="5%">编号</th>
								<th width="10%">名称</th>
								<th width="10%">颜色</th>
								<th width="10%">缩略图</th>
								<th width="10%">成本价格/元</th>
								<th width="10%">销售价格/元</th>
								<th width="10%">产品类型</th>
								<th width="10%"><i class="icon-time"></i>操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="pageList.rows">
								<s:if test="id==#parameters.id[0]">
									<tr style="color:red">
								</s:if>
								<s:else>
									<tr>
								</s:else>
									<td>${id}</td>
									<td>${name}</td>
									<td>
										<span class="btn-colorpicker" style="background-color:${color};"></span>
									</td>
									<td>
										<s:if test="smallPic!=null">
											<div class="row-fluid">
												<ul class="ace-thumbnails">
													<li>
														<a href="${pic}" data-rel="colorbox"> 
															<img alt="100*150" src="${smallPic}"/>
														</a>
													</li>
												</ul>
											</div>
										</s:if>
									</td>
									<td>${costPrice}</td>
									<td>${salePrice}</td>
									<td>${productType.name}</td>
									<td>
										<a class="btn btn-warning btn-xs chooseProduct"><!-- 自定义chooseProduct -->
											<i class="glyphicon-plus bigger-120 icon-only"></i>
										</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<%@ include file="/WEB-INF/views/common/page.jsp" %>
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
	</div><!-- /.main-content -->
	<script src="assets/js/jquery.colorbox-min.js"></script>
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