package org.csta.taoke.oa.attachment.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.csta.taoke.oa.attachment.entity.Attachment;
import org.csta.taoke.oa.attachment.entity.DecryptAttachment;
import org.csta.taoke.oa.attachment.service.AttachmentServiceImpl;
import org.csta.taoke.oa.util.StatusConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能：
 * 附件控制器类，Spring MVC中的控制器类，返回关于文章的HTTP响应
 * 注解 @Controller 代表这是一个控制器类
 * 注解 @RequestMapping 定义了匹配的域名路径
 * 
 * 修订版本：
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
	
	@ResponseBody
	@RequestMapping("/getAttachmentList")
	public Object getAttachmentList(Attachment attachment,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			List<Attachment> attachmentList=service.getAttachmentList(attachment);
			data.put("number", attachmentList.size());
			data.put("attachment", attachmentList);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("data", null);
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/getAttachmentTrashList")
	public Object getAttachmentTrashList(Attachment attachment,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			List<Attachment> attachmentList=service.getAttachmentTrashList(attachment);
			data.put("number", attachmentList.size());
			data.put("attachment", attachmentList);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("data", null);
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/getAttachment")
	public Object getAttachment(Attachment attachment,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			List<Attachment> attachmentList=service.getAttachment(attachment);
			data.put("number", attachmentList.size());
			data.put("attachment", attachmentList);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("data", null);
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/insertAttachment")
	public Object insertAttachment(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
//			service.insertAttachment(attachment);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	
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
	
	private List<DecryptAttachment> decryptAttachmentList(List<Attachment> attachments) {
		return null;
	}
}
