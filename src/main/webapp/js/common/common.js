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
		if(data.success){
			if($('#itemTbody tr').size()<2){
				$("#domainForm").submit();
			}else{
				$(src).closest('tr').remove();
				$("#end").html($("#end").html()-1);
				$("#total").html($("#total").html()-1);
			}
			alert("删除成功");
		}else{
			$(".modal-body").html(data.msg);
			$('#myModal').modal({
			    backdrop:true,
			    keyboard:true,
			    show:true
			});
		}
	},'json');
}
//取消修改/新建
function cancel(){
	window.history.back();//获取浏览器缓存对象
	location.href="employee.action";//很多人同时管理数据使用此方法，每次获取实时数据
}

