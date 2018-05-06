$(function() {
	$("#parent").change(function(){
		var children = $("#children");
		children.empty();
		var parentId = $(this).val();
		if(parentId==-1){
			children.append("<option value='-1'>--请选择--</option>");
		}else{
			//缓存，先获取
			var cacheData = children.data(parentId);
			if(cacheData){
				for (var i = 0; i < cacheData.length; i++) {
					children.append("<option value='"+cacheData[i].id+"'>"+cacheData[i].name+"</option>");
				}
			}else{
				$.get("product_findChildren.action",{id:parentId},function(data){
					//放入缓存
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
						field : "productType.id",
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