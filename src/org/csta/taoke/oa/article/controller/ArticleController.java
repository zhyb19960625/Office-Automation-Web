package org.csta.taoke.oa.article.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.csta.taoke.oa.article.entity.Article;
import org.csta.taoke.oa.article.service.ArticleServiceImpl;
import org.csta.taoke.oa.util.StatusConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleServiceImpl service;
	
	@ResponseBody
	@RequestMapping("/getArticleList")
	public Object getArticleList(Article article,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			List<Article> collectorList=service.getArticleList(article);
			data.put("number", collectorList.size());
			data.put("article", collectorList);
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
	@RequestMapping("/getArticleTrashList")
	public Object getArticleTrashList(Article article,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			List<Article> collectorList=service.getArticleTrashList(article);
			data.put("number", collectorList.size());
			data.put("article", collectorList);
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
	@RequestMapping("/getArticle")
	public Object getArticle(Article article,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			List<Article> collectorList=service.getArticle(article);
			data.put("number", collectorList.size());
			data.put("article", collectorList);
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
	@RequestMapping("/insertArticle")
	public Object insertArticle(Article article,HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			service.insertArticle(article);
			data.put("status",StatusConst.SUCCESS);
		}
		catch(Exception e) {
			e.printStackTrace();
			data.put("status",StatusConst.ERROR);
		}
		return data;
	}
	
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
