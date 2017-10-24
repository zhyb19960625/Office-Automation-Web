package cn.xiaolus.oa.article.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import cn.xiaolus.oa.article.entity.OAArticle;
import cn.xiaolus.oa.common.OADBConnector;

public class OAArticleDao extends OADBConnector {
	public OAArticleDao() throws Exception {
		super();
		super.connectDBWithDefaultProp();
	}
	
	public LinkedList<OAArticle> getAllArticles() throws Exception{
		LinkedList<OAArticle> resultList = new LinkedList<>();
		PreparedStatement sql = conn.prepareStatement("select * from articles");
		ResultSet rs = sql.executeQuery();
		while(rs.next()) {
			OAArticle article = new OAArticle();
			article.setId(rs.getInt(1));
			article.setTitle(rs.getString(2));
			article.setAuthor(rs.getString(3));
			article.setLocation(rs.getString(4));
			article.setFilename(rs.getString(5));
			resultList.add(article);                      
		}
		return resultList;
	}
}
