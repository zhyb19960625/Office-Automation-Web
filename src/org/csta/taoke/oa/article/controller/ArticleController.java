package org.csta.taoke.oa.article.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.csta.taoke.oa.article.entity.Article;
import org.csta.taoke.oa.article.service.ArticleServiceImpl;
import org.csta.taoke.oa.util.StatusConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能：
 * 文章控制器类，Spring MVC中的控制器类，返回关于文章的HTTP响应
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
@RequestMapping("/article")
public class ArticleController {
//	文章的数据库服务类的实现对象，包含了数据库操作的具体实现（数据库映射类）
	@Autowired
	private ArticleServiceImpl service;
	/**
	 * 获取全部文章列表的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param article 文章对象
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/getArticleList")
	public Object getArticleList(Article article,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
//		允许跨域访问
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
//			执行数据库操作
			List<Article> articleList=service.getArticleList(article);
			data.put("number", articleList.size());
			data.put("article", articleList);
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
	 * 获取已删除的文章列表的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param article 文章对象
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/getArticleTrashList")
	public Object getArticleTrashList(Article article,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			List<Article> articleList=service.getArticleTrashList(article);
			data.put("number", articleList.size());
			data.put("article", articleList);
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
	 * 按照ID获取文章的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param article 文章对象
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/getArticle")
	public Object getArticle(Article article,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			List<Article> articleList=service.getArticle(article);
			data.put("number", articleList.size());
			data.put("article", articleList);
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
	 * 插入文章的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param article 文章对象
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/insertArticle")
	public Object insertArticle(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
//			获得HTML文件存储路径
			String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
			path = path.replace("file:", "");
//			产生唯一的文件夹名称并创建文件夹
			path = path.replace("classes/", "articles/"+UUID.randomUUID().toString().toUpperCase()+"/");
			new File(path).mkdirs();
			File targetFile = new File(path + request.getParameter("title") + ".html");
			FileOutputStream fos = new FileOutputStream(targetFile);
			String content = request.getParameter("content");
			fos.write(content.getBytes());
			fos.close();
			Article article = new Article();
			article.setAttachment(request.getParameter("attachment"));
			article.setAuthor(request.getParameter("author"));
			article.setIsdel(0);
			article.setLocation(targetFile.getAbsolutePath());
			article.setTitle(request.getParameter("title"));
			service.insertArticle(article);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	/**
	 * 更新文章的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param article 文章对象
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/updateArticle")
	public Object updateArticle(Article article,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			service.updateArticle(article);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	/**
	 * 移动文章到回收站的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param article 文章对象
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/removeArticle")
	public Object removeArticle(Article article,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			service.removeArticle(article);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	/**
	 * 从回收站还原文章的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param article 文章对象
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/redoArticle")
	public Object redoArticle(Article article,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			service.redoArticle(article);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	/**
	 * 彻底删除文章的响应体方法 @ResponseBody
	 * 域名路径匹配到 @RequestMapping 定义的路径
	 * @param article 文章对象
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回的实际是JSON类型，在Java中是Map类型
	 * @throws UnsupportedEncodingException 抛出不支持的编码异常
	 */
	@ResponseBody
	@RequestMapping("/deleteArticle")
	public Object deleteArticle(Article article,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			service.deleteArticle(article);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
}
