//信息重置功能js
function clearForm(){
	$(':input','form')																	 //匹配所有 input, textarea, select 和 button 元素  
	 .not(':button, :submit, :reset, :hidden') 															      //去除所有与给定选择器匹配的元素
	 .val('')  																									 //获得/设置匹配元素的当前值
	 .removeAttr('checked')  																				 //从每一个匹配的元素中删除一个属性
	 .removeAttr('selected'); 
	$('select').prop('selectedIndex', 0);						//选中'---请选择---',jquery1.6以下版本$('select').attr('selectedIndex', 0)
}																			   //jquery1.6或以上版本$('select').prop('selectedIndex', 0)
//字段验证功能js
$(function(){
	$("#employeeFrom").bootstrapValidator({
        message: '值不能为空',                                                                                              //字段通用提示语
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
            	threshold:6,
                validators: {
                    notEmpty:{message:'请输入用户名'},
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message:'请输入英文或者数字'
                    },
			        stringLength: {
		                min: 6,
		                max: 30,
		                message:'请输入6至30个字符'
		            },
			        remote: {
	                    url: 'employee_checkUsername.action?id='+$('#userId').val(),
	                    message: '此用户名已存在'
	                }
                }
            },
	        password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'confirmPassword',
                        message: '密码和验证密码不一致'
                    },
                    different: {
                        field: 'username',
                        message: '密码不能和用户名一样'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: '验证密码不能为空'
                    },
                    identical: {
                        field: 'password',
                        message: '验证密码和密码不一致'
                    },
                    different: {
                        field: 'username',
                        message: '密码不能和用户名一样'
                    }
                }
            },
            email:{
                validators: {
                    emailAddress: {
                        message: '请输入有效的邮箱'
                    }
                }
            },
            age:{
            	validators:{
            		notEmpty:{message:'请输入年龄'},
            		integer:true,
            		between:{
            			min:18,
            			max:80
            		}
            	}
            }
        }
	})
});
