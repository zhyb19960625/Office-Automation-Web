package org.csta.taoke.oa.attachment.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.csta.taoke.oa.attachment.entity.Attachment;
import org.csta.taoke.oa.attachment.entity.DecryptAttachment;
import org.csta.taoke.oa.attachment.service.AttachmentServiceImpl;
import org.csta.taoke.oa.security.CryptoCore;
import org.csta.taoke.oa.security.KeyStoreManager;
import org.csta.taoke.oa.util.StatusConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 功能：
 * 附件控制器类，Spring MVC中的控制器类，返回关于附件的HTTP响应
 * 注解 @Controller 代表这是一个控制器类
 * 注解 @RequestMapping 定义了匹配的域名路径
 * 
 * 修订版本：
 * 2018-02-13 文件上传整合和附件加密整合
 * 2018-02-12 新增文件上传
 * 2018-02-11 新增解密附件功能
 * 2018-02-07 首次编写
 * 
 * @author 路伟饶
 *
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentController {
//	附件的数据库服务类的实现对象，包含了数据库操作的具体实现（数据库映射类）
	@Autowired
	private AttachmentServiceImpl service;
	/**
	 * 获取全部附件列表的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param attachment 附件对象
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/getAttachmentList")
	public Object getAttachmentList(Attachment attachment,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			List<Attachment> attachmentList=service.getAttachmentList(attachment);
			List<DecryptAttachment> decryptAttachments = decryptAttachmentList(attachmentList);
			data.put("number", attachmentList.size());
			data.put("attachment", decryptAttachments);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("data", null);
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	
	/**
	 * 获取已删除的附件列表的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param attachment 附件对象
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/getAttachmentTrashList")
	public Object getAttachmentTrashList(Attachment attachment,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			List<Attachment> attachmentList=service.getAttachmentTrashList(attachment);
			List<DecryptAttachment> decryptAttachments = decryptAttachmentList(attachmentList);
			data.put("number", attachmentList.size());
			data.put("attachment", decryptAttachments);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("data", null);
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	
	/**
	 * 按照ID获取附件的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param attachment 附件对象
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/getAttachment")
	public Object getAttachment(Attachment attachment,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			List<Attachment> attachmentList=service.getAttachment(attachment);
			List<DecryptAttachment> decryptAttachments = decryptAttachmentList(attachmentList);
			data.put("number", attachmentList.size());
			data.put("attachment", decryptAttachments);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("data", null);
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	
	/**
	 * 插入附件的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param request HTTP请求对象，需要是一个包含上传文件的请求
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/insertAttachment")
	public Object insertAttachment(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			File tempFile = this.receiveUploadFile(request);
			List<DecryptAttachment> decryptAttachments = new ArrayList<>();
			DecryptAttachment decryptAttachment = new DecryptAttachment();
			decryptAttachment.setName(tempFile.getName());
			decryptAttachment.setTemplocation(tempFile.getAbsolutePath());
			decryptAttachments.add(decryptAttachment);
			List<Attachment> attachments = encryptAttachmentList(decryptAttachments);
			for (Attachment attachment : attachments) {
				service.insertAttachment(attachment);
			}
			data.put("status",StatusConst.SUCCESS);
			data.put("count", attachments.size());
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	
	/**
	 * 更新附件的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param attachment 附件对象
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/updateAttachment")
	public Object updateAttachment(Attachment article,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			service.updateAttachment(article);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	
	/**
	 * 移动附件到回收站的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param attachment 附件对象
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/removeAttachment")
	public Object removeAttachment(Attachment attachment,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			service.removeAttachment(attachment);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	
	/**
	 * 从回收站还原附件的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param attachment 附件对象
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/redoAttachment")
	public Object redoAttachment(Attachment attachment,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			service.redoAttachment(attachment);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	
	/**
	 * 彻底删除附件的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param attachment 附件对象
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/deleteAttachment")
	public Object deleteAttachment(Attachment attachment,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			service.deleteAttachment(attachment);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	
	/**
	 * 接收上传的文件
	 * 上传文件临时存储于 /WEB-INF/uploads/随机唯一UUID值/ 中
	 * @param request HTTP请求对象
	 * @return 存储的上传临时文件
	 * @throws Exception
	 */
	private File receiveUploadFile(HttpServletRequest request) throws Exception {
//		将普通HTTP请求对象强制转换为多媒体HTTP请求对象
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//      获得上传的文件
		MultipartFile multipartFile = multipartRequest.getFile("file");  
//		获得临时文件保存路径
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		path = path.replace("file:", "");
//		产生唯一的文件夹名称并创建文件夹
		path = path.replace("classes/", "uploads/"+UUID.randomUUID().toString().toUpperCase()+"/");
		new File(path).mkdirs();
		File targetFile = new File(path + multipartFile.getOriginalFilename());
//		写入文件
		multipartFile.transferTo(targetFile);
		return targetFile;
	}
	
	/**
	 * 加密附件列表
	 * 加密后的附件存储于 /WEB-INF/attachments/随机唯一UUID值/ 中
	 * 该随机唯一UUID值同时也是密钥在密钥库的入口名称
	 * 生成密钥所用的密码来源于另一个随机唯一UUID值
	 * 密钥库是 /WEB-INF/security/KeyStoreCenter.keystore
	 * @param decryptAttachments 明文附件列表
	 * @return 密文附件列表
	 * @throws Exception
	 */
	private List<Attachment> encryptAttachmentList(List<DecryptAttachment> decryptAttachments) throws Exception{
//		获得服务器运行路径
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		path = path.replace("file:", "");
//		得到加密附件存储路径
		String attachmentPath = path.replace("classes/", "attachments/");
//		检查加密附件存储路径是否已经创建
		File attachmentPathFile = new File(attachmentPath);
		if (!attachmentPathFile.exists() && !attachmentPathFile.isDirectory()) {
			attachmentPathFile.mkdirs();
		}
//		得到密钥库路径
		String keystorePath = path.replace("classes/", "security/");
//		检查密钥库路径是否已经创建
		File keystorePathFile = new File(keystorePath);
		if (!keystorePathFile.exists() && !keystorePathFile.isDirectory()) {
			keystorePathFile.mkdirs();
		}
//		获得密钥库文件对象
		File keystoreFile = new File(keystorePath + "KeyStoreCenter.keystore");
//		获得密钥库管理器对象
		KeyStoreManager manager = KeyStoreManager.getInstance();
//		获得对称加密对象
		CryptoCore cryptoCore = CryptoCore.getInstance();
		if (!keystoreFile.exists() && !keystoreFile.isDirectory()) {
			manager.createKeyStore(keystoreFile, "12345678".toCharArray());
		}
//		密码需要修改！！！
		manager.openKeyStoreFromFile(keystoreFile, "12345678".toCharArray());
		List<Attachment> attachments = new ArrayList<>();
		for(DecryptAttachment decryptAttachment : decryptAttachments) {
//			随机产生密钥入口名称
			String keyEntryString = UUID.randomUUID().toString().toUpperCase();
//			创建永久附件保存文件夹
			new File(attachmentPath + keyEntryString).mkdir();
//			获得输出文件对象
			File outputFile = new File(attachmentPath + keyEntryString + "/" +decryptAttachment.getName());
//			由随机UUID获得密钥，因最终存储了密钥到密钥库，所以无需存储该随机UUID
			SecretKey key = CryptoCore.getKeyByPassword(UUID.randomUUID().toString().toCharArray(), 16);
//			加密文件
			cryptoCore.encryptFile(new File(decryptAttachment.getTemplocation()), key, 16, outputFile);
//			存储加密所用的密钥，而IV存储于文件本体当中
			manager.saveSymmetricKey(key, keyEntryString);
			Attachment attachment = new Attachment();
			attachment.setKeyentry(keyEntryString);
			attachment.setLocation(outputFile.getAbsolutePath());
			attachment.setName(outputFile.getName());
			attachment.setType("Normal");
			attachments.add(attachment);
		}
		return attachments;
	}
	
	/**
	 * 解密附件列表
	 * 解密后的临时文件存储于 /oa-download/随机唯一UUID值/ 中
	 * 密钥库是 /WEB-INF/security/KeyStoreCenter.keystore
	 * @param attachments 密文附件列表
	 * @return 明文附件列表
	 * @throws Exception
	 */
	private List<DecryptAttachment> decryptAttachmentList(List<Attachment> attachments) throws Exception {
//		获得服务器运行路径
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
//		去除file:开头
		path = path.replace("file:", "");
//		获得密钥库路径
		String keystorePath = path.replace("classes/", "security/");
//		获得密钥库文件对象
		File keystoreFile = new File(keystorePath + "KeyStoreCenter.keystore");
//		获得密钥库管理对象
		KeyStoreManager manager = KeyStoreManager.getInstance();
//		获得对称加密对象
		CryptoCore cryptoCore = CryptoCore.getInstance();
//		打开密钥库，此处密码需要修改！！！
		manager.openKeyStoreFromFile(keystoreFile, "12345678".toCharArray());
//		创建用于返回的解密附件列表对象
		List<DecryptAttachment> decryptAttachments = new ArrayList<>();
		for(Attachment attachment : attachments) {
//			获得密钥入口字符串
			String keyEntryString = attachment.getKeyentry();
//			从密钥库获得密钥
			SecretKey key = manager.getSymmetricKey(keyEntryString);
//			获得源附件文件
			File sourceFile = new File(attachment.getLocation());
//			获得下载路径并创建
			String dlPath = path.replace("WEB-INF/security/", "oa-download/" + UUID.randomUUID().toString() + "/");
			new File(dlPath).mkdirs();
//			创建输出文件对象
			File outputFile = new File(dlPath + attachment.getName());
//			用对称加密对象解密文件
			cryptoCore.decryptFile(sourceFile, key, outputFile);
			DecryptAttachment decryptAttachment = new DecryptAttachment();
			decryptAttachment.setId(attachment.getId());
			decryptAttachment.setName(attachment.getName());
			decryptAttachment.setTemplocation(outputFile.getAbsolutePath());
			decryptAttachments.add(decryptAttachment);
		}
		return decryptAttachments;
	}
}
