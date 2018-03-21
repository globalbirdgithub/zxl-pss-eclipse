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
<!-- 提示信息模态框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">提示</h4>
      </div>
      <div class="modal-body" style="color:red"></div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

