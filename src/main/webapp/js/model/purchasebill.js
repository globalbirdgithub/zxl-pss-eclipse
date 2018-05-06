//模态框测试
$(function(){
	$("#itemBody").on('click','a[diyCode=selectProduct]',function(){
		$('.modal-body').html("数据正在加载...");
		$('.modal-body').load('product_bill.action');
		$('#myModal').modal({
		    backdrop:true,
		    keyboard:true,
		    show:true
		});
		var tr = $(this).closest("tr");//图片点击问题？
		$('#myModal').on('shown.bs.modal',function(e){//监控模态框
			$(".chooseProduct").click(function(){//监控选择产品操作按钮
				$(".close").click();
				var tds = $(this).closest("tr").find("td");
				tr.find("input[diyCode=productId]").val($(tds[0]).html());
				tr.find("input[diyCode=productName]").val($(tds[1]).html());
				tr.find("td[diyCode=productColor]").html($(tds[2]).html());
				tr.find("td[diyCode=productPic]").html($(tds[3]).html());
				tr.find("input[diyCode=productPrice]").val($(tds[4]).html());
			});
		});
	});
	//添加明细
	$('#addItem').click(function(){
		var tr = $('#itemBody tr:first').clone();
		tr.find("input[diyCode=productId]").val('');
		tr.find("input[diyCode=productName]").val('');
		tr.find("td[diyCode=productColor]").html('');
		tr.find("td[diyCode=productPic]").html('');
		tr.find("input[diyCode=productPrice]").val('');
		tr.find("input[diyCode=productNum]").val('');
		tr.find("td[diyCode=productAmount]").html('');
		tr.find("input[diyCode=productDescs]").val('');
		$('#itemBody').prepend(tr);
		if(window.parent.autoHeight){
			window.parent.autoHeight();
		}
	});
	//保存的时候修改索引
	$("#savePurchaseItems").click(function(){
		var flag = false;
		$('#itemBody tr').each(function(index,domEle){
			var tr = $(domEle);
			tr.find("input[diyCode=productId]").attr("name","purchasebillItems["+index+"].product.id");
			tr.find("input[diyCode=productPrice]").attr("name","purchasebillItems["+index+"].price");
			tr.find("input[diyCode=productNum]").attr("name","purchasebillItems["+index+"].num");
			tr.find("input[diyCode=productDescs]").attr("name","purchasebillItems["+index+"].descs");
			var productId = tr.find("input[diyCode=productId]").val();
			if(!flag&&productId==""){
				alert("请选择产品");
				flag=true;
				tr.find("input[diyCode=productName]").focus();
				return false;
			}
			var productNum = tr.find("input[diyCode=productNum]").val();
			if(!flag&&productNum==""){
				alert("请填写数量");
				flag=true;
				tr.find("input[diyCode=productNum]").focus();
				return false;
			}
			//数量格式
			if(!flag && !/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(productNum)||productNum<0){
				alert("数量格式非法");
				flag=true;
				tr.find("input[diyCode=productNum]").focus();
				return false;
			}
		});
		if(!flag){//验证没有错误
			$('#purchasebillForm').submit();
		}
	});
	//删除明细
	$('#itemBody').on('click','a[diyCode="deleteItem"]',function(){
		if($('#itemBody tr').size()>1){
			$(this).closest('tr').remove();
		}
	});
	//自动计算小计
	$("#itemBody").on('blur','input[diyCode=productNum],input[diyCode=productPrice]',function(){
		var tr = $(this).closest('tr');
		var price = tr.find('input[diyCode=productPrice]').val();
		var num = tr.find('input[diyCode=productNum]').val();
		var amount = (price*num).toFixed(2);//保留两位小数
		tr.find('td[diyCode=productAmount]').html(amount);
	});
});
//审核后 审核功能(审核/作废)和编辑功能(编辑/删除)失去 
function confirmPurchasebill(url,domEle){
	$.get(url,function(data){
		var tds = $(domEle).closest('tr').find('td');
		$(tds[8]).html('<span class="label label-success">已审</span>');
		$(tds[9]).html(data.auditor);
		$(tds[10]).html(data.auditorDate);
		$(tds[12]).html('');
		$(tds[13]).html('');
	});
}
//作废后 审核功能(审核/作废)和编辑功能(编辑/删除)失去
function abandonPurchasebill(url,domEle){
	$.get(url,function(data){
		var tds = $(domEle).closest('tr').find('td');
		$(tds[8]).html('<span class="label">作废</span>');
		$(tds[9]).html(data.auditor);
		$(tds[10]).html(data.auditorDate);
		$(tds[12]).html('');
		$(tds[13]).html('');
	});
}

