package org.csta.taoke.oa.article.dao;

import java.util.List;

import org.csta.taoke.oa.article.entity.Article;
import org.springframework.stereotype.Repository;

/**
 * 功能：
 * 关于文章的数据库映射类的接口
 * 
 * 修订版本：
 * 2018-02-07 首次编写
 * 
 * @author 路伟饶
 *
 */
@Repository
public interface ArticleMapper {
	/**
	 * 获取全部文章列表的数据库映射方法
	 * 由Spring DAO自动实现数据库操作，SQL语句由XML文件定义
	 * 
	 * @param article 文章对象
	 * @return 文章列表
	 */
	public List<Article> getArticleList(Article article);
	/**
	 * 获取已删除文章列表的数据库映射方法
	 * 由Spring DAO自动实现数据库操作，SQL语句由XML文件定义
	 * 
	 * @param article 文章对象
	 * @return 文章列表
	 */
	public List<Article> getArticleTrashList(Article article);
	/**
	 * 获取单个文章的数据库映射方法
	 * 由Spring DAO自动实现数据库操作，SQL语句由XML文件定义
	 * 
	 * @param article 文章对象
	 * @return 文章列表
	 */
	public List<Article> getArticle(Article article);
	/**
	 * 插入文章的数据库映射方法
	 * 由Spring DAO自动实现数据库操作，SQL语句由XML文件定义
	 * 
	 * @param article 文章对象
	 */
	public void insertArticle(Article article);
	/**
	 * 更新文章的数据库映射方法
	 * 由Spring DAO自动实现数据库操作，SQL语句由XML文件定义
	 * 
	 * @param article 文章对象
	 */
	public void updateArticle(Article article);
	/**
	 * 移动文章到回收站的数据库映射方法
	 * 由Spring DAO自动实现数据库操作，SQL语句由XML文件定义
	 * 
	 * @param article 文章对象
	 */
	public void removeArticle(Article article);
	/**
	 * 从回收站恢复文章的数据库映射方法
	 * 由Spring DAO自动实现数据库操作，SQL语句由XML文件定义
	 * 
	 * @param article 文章对象
	 */
	public void redoArticle(Article article);
	/**
	 * 彻底删除文章的数据库映射方法
	 * 由Spring DAO自动实现数据库操作，SQL语句由XML文件定义
	 * 
	 * @param article 文章对象
	 */
	public void deleteArticle(Article article);
}
