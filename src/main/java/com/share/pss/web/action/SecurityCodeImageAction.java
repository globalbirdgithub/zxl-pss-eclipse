package com.share.pss.web.action;

import java.io.ByteArrayInputStream;

import com.opensymphony.xwork2.ActionContext;
import com.share.pss.tool.SecurityCode;
import com.share.pss.tool.SecurityImage;

/**提供图片验证码
 * @author MrZhang 
 * @date 2018年3月10日 下午2:08:56
 * @version V1.0
 */
public class SecurityCodeImageAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	//图片流
	private ByteArrayInputStream byteArrayInputStream;
	public ByteArrayInputStream getByteArrayInputStream() {
		return byteArrayInputStream;
	}
	public void setByteArrayInputStream(ByteArrayInputStream byteArrayInputStream) {
		this.byteArrayInputStream = byteArrayInputStream;
	}
	@Override
	public String execute() throws Exception {
		//如果开启Hard模式，可不区分大小写
		//String securityCode = SecurityCode.getSecurityCode(4, SecurityCodeLevel.Hard, false).toLowerCase();
		//获取默认难度和长度的验证码
		String securityCode = SecurityCode.getSecurityCode();
		//获取验证码图片
		byteArrayInputStream = SecurityImage.getImageAsInputStream(securityCode);
		//验证码放入Session
		ActionContext.getContext().getSession().put(SESSION_SECURITY_CODE, securityCode);
		return SUCCESS;
	}
}
