//字段验证功能js
$(function(){
	$("#stockIncomebillForm").bootstrapValidator({
        message: '值不能为空',                                                                                              // 字段通用提示语
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty:{message:'值不能为空'},
                }
            }
        }
	});
});
