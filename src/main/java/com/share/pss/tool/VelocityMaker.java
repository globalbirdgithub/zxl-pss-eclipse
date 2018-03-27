package com.share.pss.tool;

import java.io.File;
import java.io.FileWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

/**
 * 代码生成器工具类
 * @author MrZhang
 * @date 2018年3月26日 下午6:37:00
 * @version V1.0
 */
public class VelocityMaker {
	// 定义文件路径常量
	private static final String SRC = "src/main/java/";
	private static final String PACKAGE = "com/share/pss/";
	private static final String TEST = "src/test/java/";
	private static final String RESOURCES = "src/main/resources/";
	private static final String WEBAPP = "src/main/webapp/";
	// 定义模型集合
	private static String[] domains = { "Dept" };
	// 定义输出文件名集合
	private static String[] fileNames = { "Service.java", "ServiceImpl.java", "ServiceTest.java", "Query.java",
			"Action.java", "hbm.xml", "Context.xml", "domain.js", "list.jsp", "input.jsp" };
	// 定义模板文件名集合
	private static String[] templateNames = { "IDomainService.java", "DomainServiceImpl.java",
			"DomainServiceTest.java", "DomainQuery.java", "DomainAction.java", "Domain.hbm.xml", "domainContext.xml",
			"domain.js", "domain.jsp", "domain_input.jsp" };
	// 定义模板输出位置集合
	private static String[] files = { SRC + PACKAGE + "service/", SRC + PACKAGE + "service/impl/",
			TEST + PACKAGE + "service/", SRC + PACKAGE + "query/", SRC + PACKAGE + "web/action/",
			RESOURCES + PACKAGE + "domain/", RESOURCES + "manager/", WEBAPP + "js/model/",
			WEBAPP + "WEB-INF/views/", WEBAPP + "WEB-INF/views/" };

	/**
	 * 生成文件方法 void 2018年3月26日下午7:35:36
	 * 
	 * @throws Exception
	 */
	public static void createCode() throws Exception {
		if (fileNames.length != files.length) {
			throw new RuntimeException("fileNames.length!=files.length");
		}
		Velocity.init();
		VelocityContext velocityContext = new VelocityContext();
		// 外循环：模型
		for (int i = 0; i < domains.length; i++) {
			String domainLowerCaseName = domains[i].substring(0, 1).toLowerCase()+domains[i].substring(1);//首字母小写
			velocityContext.put("domain", domains[i]);
			velocityContext.put("domainLower", domainLowerCaseName);
			// 内循环：模板
			for (int j = 0; j < fileNames.length; j++) {
				File file = new File(files[j] + domains[i] + fileNames[j]);
				// 对特殊文件名处理
				if ("Service.java".equals(fileNames[j])) {
					file = new File(files[j] + "I" + domains[i] + fileNames[j]);
				} else if ("hbm.xml".equals(fileNames[j])) {
					file = new File(files[j] + domains[i] + "." + fileNames[j]);
				} else if ("domain.js".equals(fileNames[j])) {
					file = new File(files[j] + domainLowerCaseName + ".js");
				} else if ("list.jsp".equals(fileNames[j])) {
					file = new File(files[j] + domainLowerCaseName + "/" + domainLowerCaseName + ".jsp");
				} else if ("input.jsp".equals(fileNames[j])) {
					file = new File(files[j] + domainLowerCaseName + "/" + domainLowerCaseName + "_" + fileNames[j]);
				} else if ("Context.xml".equals(fileNames[j])) {
					file = new File(files[j] + domainLowerCaseName + fileNames[j]);
				}
				System.out.println(file.getAbsolutePath());
				File parentFile = file.getParentFile();
				if(!parentFile.exists()){
					parentFile.mkdirs();
				}
				Template template = Velocity.getTemplate("velocityTemplate/"+templateNames[j], "UTF-8");
				FileWriter fileWriter = new FileWriter(file);
				template.merge(velocityContext, fileWriter);
				fileWriter.close();
			}
		}
		System.out.println("先刷新工程，修改映射文件，运行测试，启动tomcat");
	}
}
