package org.csta.taoke.oa.entity;

import java.sql.Date;
/**
 * 功能：
 * 文章数据模型
 * 
 * 修订版本：
 * 2018-01-03 首次编写
 * 
 * @author 路伟饶
 *
 */
public class Article {
//	文章ID，由数据库自动生成，一般不需要设置
	private long id;
//	文章的创建时间
	private Date createtime;
//	文章的标题
	private String title;
//	文章的作者
	private String author;
//	文章在服务器上存储的位置
	private String location;
//	文章附带的附件的ID，是一个外码
	private long attachment;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public long getAttachment() {
		return attachment;
	}
	public void setAttachment(long attachment) {
		this.attachment = attachment;
	}
}
