$(function() {
	$("#parent").change(function(){
		var children = $("#children");
		children.empty();
		var parentId = $(this).val();
		if(parentId==-1){
			children.append("<option value='-1'>--请选择--</option>");
		}else{
			var myData = children.data(parentId);
			if(myData){
				for (var i = 0; i < myData.length; i++) {
					children.append("<option value='"+myData[i].id+"'>"+myData[i].name+"</option>");
				}
			}else{
				$.get("product_findChildren.action",{id:parentId},function(data){
					children.data(parentId,data);
					for (var i = 0; i < data.length; i++) {
						children.append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
					}
				});
			}
		}
	});
	$('#productForm').bootstrapValidator({
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			name : {
				validators : {
					notEmpty : true
				}
			},
			costPrice : {
				validators : {
					notEmpty : true,
					numeric : true//数值
				}
			},
			salePrice : {
				validators : {
					notEmpty : true,
					numeric : true
				}
			},
			"productType.id" : {//二级类型
				validators : {
					notEmpty : true
				}
			},
			"productType.parentProductType.id" : {//一级类型
				validators : {
					different : {
						field : "parentProductType.id",
						message : '必须选择正确产品类型'
					}
				}
			},
			uploadProductPicture : {
				validators : {
					file : {
						extension : 'gif,png,jpg,jpeg',
						type : 'image/gif,image/png,image/jpeg',
						maxSize : 5 * 1024 * 1024, // 5 MB
						message : '必须上传图片而且图片不能超过5MB'
					}
				}
			}
		}
	});
});