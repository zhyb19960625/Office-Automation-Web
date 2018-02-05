package org.csta.taoke.oa.article.service;

import java.util.List;

import org.csta.taoke.oa.article.entity.Article;

public interface ArticleService {
	public List<Article> getArticleList(Article article);
	public List<Article> getArticleTrashList(Article article);
	public List<Article> getArticle(Article article);
	public void insertArticle(Article article);
	public void updateArticle(Article article);
	public void removeArticle(Article article);
	public void redoArticle(Article article);
	public void deleteArticle(Article article);
}
