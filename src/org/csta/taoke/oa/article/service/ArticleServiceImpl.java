package org.csta.taoke.oa.article.service;

import java.util.List;

import org.csta.taoke.oa.article.dao.ArticleMapper;
import org.csta.taoke.oa.article.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 关于文章的映射到数据库操作的接口的实现类
 * 
 * 修订版本：
 * 2018-02-07 首次编写
 * 
 * @author 路伟饶
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	
//	包含一个数据库映射类的对象
	@Autowired
	private ArticleMapper dao;
	
	@Override
	public List<Article> getArticleList(Article article) {
		return dao.getArticleList(article);
	}
	
	@Override
	public List<Article> getArticleTrashList(Article article) {
		return dao.getArticleTrashList(article);
	}

	@Override
	public List<Article> getArticle(Article article) {
		return dao.getArticle(article);
	}

	@Override
	public void insertArticle(Article article) {
		dao.insertArticle(article);
	}

	@Override
	public void updateArticle(Article article) {
		dao.updateArticle(article);
	}

	@Override
	public void removeArticle(Article article) {
		dao.removeArticle(article);
	}

	@Override
	public void redoArticle(Article article) {
		dao.redoArticle(article);
	}

	@Override
	public void deleteArticle(Article article) {
		dao.deleteArticle(article);
	}
}
