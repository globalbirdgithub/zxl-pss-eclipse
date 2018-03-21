package com.share.pss.tool;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.sun.image.codec.jpeg.*;

/**
 * 工具类 生成验证码图片
 * 
 * @author MrZhang
 * @date 2018年3月10日 上午11:35:56
 * @version V1.0
 */
public class SecurityImage {
	/**生成验证码图片
	 * @param securityCode 验证码字符
	 * @return BufferedImage 验证码图片
	 * 2018年3月10日上午11:54:53
	 */
	public static BufferedImage createImage(String securityCode) {
		// 验证码长度
		int codeLength = securityCode.length();
		// 字体大小
		int fontSize = 15;
		int fontWidth = fontSize + 1;
		// 图片宽度
		int width = codeLength * fontWidth + 6;
		// 图片高度
		int height = fontSize * 2 + 1;
		//图片
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = bufferedImage.createGraphics();
		//背景色
		graphics.setColor(Color.WHITE);
		//填充背景
		graphics.fillRect(0, 0, width, height);
		//设置边框
		graphics.setColor(Color.LIGHT_GRAY);
		//边框字体
		graphics.setFont(new Font("Arial", Font.BOLD, height-2));
		//绘制边框
		graphics.drawRect(0, 0, width-1, height-1);
		//绘制噪点
		Random random = new Random();
		//设置噪点颜色是
		graphics.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < codeLength*6; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			//绘制1*1矩形
			graphics.drawRect(x, y, 1, 1);
		}
		//绘制验证码
		int codeY = height-10;
		//设置字体颜色和样式
		graphics.setColor(new Color(19, 148, 246));
		graphics.setFont(new Font("Georgia", Font.BOLD, fontSize));
		for (int i = 0; i < codeLength; i++) {
			graphics.drawString(String.valueOf(securityCode.charAt(i)), i*16+5, codeY);
		}
		//关闭资源
		graphics.dispose();
		return bufferedImage;
	}
	/**返回验证码图片的流格式
	 * @param securityCode 验证码
	 * @return ByteArrayInputStream 图片流
	 * 2018年3月10日上午11:57:21
	 */
	public static ByteArrayInputStream getImageAsInputStream(String securityCode){
		BufferedImage image = createImage(securityCode);
		return convertImageToStream(image);
	}
	/**将BufferedImage转化为ByteArrayInputStream
	 * @param bufferedImage 图片
	 * @return ByteArrayInputStream 流
	 * 2018年3月10日下午2:02:59
	 */
	private static ByteArrayInputStream convertImageToStream(BufferedImage bufferedImage) {
		ByteArrayInputStream byetArrayInputStream = null;
		try {
			ByteArrayOutputStream byetArrayOutputStream = new ByteArrayOutputStream();
			ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(byetArrayOutputStream);
			ImageIO.write(bufferedImage, "jpeg", imageOutputStream);//将图片转化为图片输出流
			byte[] byteArray = byetArrayOutputStream.toByteArray();//图片输出流转化为字节数组
			byetArrayInputStream = new ByteArrayInputStream(byteArray);//将字节数组转化为字节数组输出流
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byetArrayInputStream;
	}
}
