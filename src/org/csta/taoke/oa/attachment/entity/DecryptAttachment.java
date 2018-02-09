package org.csta.taoke.oa.attachment.entity;
/**
 * 功能：
 * 解密之后的附件的数据模型
 * 
 * 修订版本：
 * 2018-02-09 首次编写
 * 
 * @author 路伟饶
 *
 */
public class DecryptAttachment {
//	附件ID，源于Attachment类中的ID
	private long id;
//	附件名称，源于Attachment类中的名称
	private String name;
//	附件的临时下载地址
	private String templocation;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemplocation() {
		return templocation;
	}
	public void setTemplocation(String templocation) {
		this.templocation = templocation;
	}
}
