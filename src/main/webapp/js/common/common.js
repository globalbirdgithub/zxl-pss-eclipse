//分页条公共js
function goPage(number) {
	$('#pageNum').val(number);
	$('#domainForm').submit();
}
//高级修改公共js
function updateDomain(url) {
	//修改表单的的url:employee.action为employee_input.action?id=${id}'目的就是将表单中的两个数据传递到xxx_input页面
	$('#domainForm').attr("action",url);
	$('#domainForm').submit();
}
//页码导航直达功能防粘贴
$(document).ready(function(){
	$('#pageNum').keyup(function(){//监听用户键盘输入事件
		this.value=this.value.replace(/\D/g,'');
	});
	$('#pageNum').pasteEvents = function(){//监听用户CTRL+V粘贴事件过滤字符
		this.value=this.value.replace(/\D/g,'');
	};
});
//ajax删除
function deleteDomain(url,src){
	$.post(url,function(data){
		if(data instanceof Object){
			if(data.success){
				if($('#itemTbody tr').size()<2){
					$("#domainForm").submit();
				}else{
					$(src).closest('tr').remove();
					$("#end").html($("#end").html()-1);
					$("#total").html($("#total").html()-1);
				}
				$(".modal-body").html(data.msg);
				$('#myModal').modal({
				    backdrop:true,
				    keyboard:true,
				    show:true
				});
			}else{
				$(".modal-body").html(data.msg);
				$('#myModal').modal({
				    backdrop:true,
				    keyboard:true,
				    show:true
				});
			}
		}else{
			$(".modal-body").html("没有权限");
			$('#myModal').modal({
			    backdrop:true,
			    keyboard:true,
			    show:true
			});
		}
	});
}
//取消修改/新建
function cancel(domain){
	window.history.back();//获取浏览器缓存对象
	location.href=domain+".action";//很多人同时管理数据使用此方法，每次获取实时数据
}
//信息重置功能js
function clearForm(){
	$(':input','form')																	 //匹配所有 input, textarea, select 和 button 元素  
	 .not(':button, :submit, :reset, :hidden') 															      //去除所有与给定选择器匹配的元素
	 .val('')  																									 //获得/设置匹配元素的当前值
	 .removeAttr('checked')  																				 //从每一个匹配的元素中删除一个属性
	 .removeAttr('selected'); 
	$('select').prop('selectedIndex', 0);						//选中'---请选择---',jquery1.6以下版本$('select').attr('selectedIndex', 0)
}
//下载Excel文件
function downloadDomain(domain){
	$("#domainForm").attr("action",domain+"_download.action");
	$("#domainForm").submit();
	$("#domainForm").attr("action",domain+".action");
}