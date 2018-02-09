package org.csta.taoke.oa.attachment.entity;
/**
 * 功能：
 * 附件数据模型
 * 
 * 修订版本：
 * 2018-02-09 增加附件名称
 * 2018-02-07 增加删除标志
 * 2018-01-03 首次编写
 * 
 * @author 路伟饶
 *
 */
public class Attachment {
//	附件ID，由数据库自动生成，一般不需要设置
	private long id;
//	附件名称，附件的文件名
	private String name;
//	附件在服务器上存储的位置
	private String location;
//	附件的文件类型
	private String type;
//	加密附件的密钥库入口名称，用于找到解密所需的密钥
	private String keyentry;
//	附件是否被删除
	private int isdel;
	
	public long getId() {
		return id;
	}
	public void setId(String id) {
		this.id = Long.valueOf(id);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKeyentry() {
		return keyentry;
	}
	public void setKeyentry(String keyentry) {
		this.keyentry = keyentry;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
}
