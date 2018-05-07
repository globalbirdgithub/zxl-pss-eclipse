$(function(){
	//获取饼图数据
	$(".btn").click(function(){
		var url = $(this).data('url');
		if(url){
			$('.modal-body').html('加载中...');
			$('.modal-body').load(url+"?"+$('#domainForm').serialize());
			$('#myModal').modal({
				backdrop:true,
				keyboard:true,
				show:true
			});
		}
	});
});
