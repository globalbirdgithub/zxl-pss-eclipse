<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- 页码导航条公共div -->
<div class="row">
	<div class="col-sm-6">
		<div id="sample-talbe-2_info" class="dataTables_info">
		显示
		${pageList.beginIndex}
		到
		<span id="end">${pageList.endIndex}</span>
		共
		<span id="total">${pageList.totalCount}</span>
		条
		</div>
	</div>
	<div class="col-sm-6">
		<ul class="pagination pull-right no-margin">
			<s:property value="pageList.goPage" escapeHtml="false" />
		</ul>
	</div>
</div>

