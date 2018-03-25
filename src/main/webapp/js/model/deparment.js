//字段验证功能js
$(function(){
	$("#departmentForm").bootstrapValidator({
        message: '值不能为空',                                                                                              // 字段通用提示语
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
            	validators: {
                    notEmpty:{message:'请输入部门名称'},
			        stringLength: {
		                min: 3,
		                max: 10,
		                message:'请输入3至10个字符'
		            },
            	}
            }
        }
	})
});
