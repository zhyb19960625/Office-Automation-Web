package org.csta.taoke.oa.entity;

import java.sql.Date;

import org.csta.taoke.oa.common.DateTimeTools;

/**
 * 功能：
 * 学生信息数据模型
 * 
 * 修订版本：
 * 2018-01-01 首次编写
 * 2018-01-04 修改生日的类型为Date，借助DateTimeTools进行转换
 * 
 * @author 路伟饶
 *
 */

@Deprecated
public class Student {
//	学号
	private long id;
//	姓名
	private String name;
//	性别
	private String sex;
//	专业
	private String major;
//	班级
	private String classes;
//	出生日期
	private Date birthday;
//	政治面貌
	private String poc;
//	民族
	private String ethnic;
//	身份证号
	private String personid;
//	生源地
	private String from;
//	职务
	private String position;
//	本人联系方式
	private long phone;
//	家长联系方式
	private long parentsphone;
//	父亲姓名
	private String fathername;
//	母亲姓名
	private String mothername;
//	是否贷款
	private String isloan;
//	是否经济困难
	private String isdifficult;
//	高考考试类型
	private String type;
//	宗教信仰
	private String religious;
//	是否单亲
	private String isalone;
//	备注
	private String comment;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = DateTimeTools.conventDateStringToObject(birthday, "-");
	}
	public String getPoc() {
		return poc;
	}
	public void setPoc(String poc) {
		this.poc = poc;
	}
	public String getEthnic() {
		return ethnic;
	}
	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = Long.valueOf(phone);
	}
	public long getParentsphone() {
		return parentsphone;
	}
	public void setParentsphone(String parentsphone) {
		this.parentsphone = Long.valueOf(parentsphone);
	}
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	public String getMothername() {
		return mothername;
	}
	public void setMothername(String mothername) {
		this.mothername = mothername;
	}
	public String getIsloan() {
		return isloan;
	}
	public void setIsloan(String isloan) {
		this.isloan = isloan;
	}
	public String getIsdifficult() {
		return isdifficult;
	}
	public void setIsdifficult(String isdifficult) {
		this.isdifficult = isdifficult;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReligious() {
		return religious;
	}
	public void setReligious(String religious) {
		this.religious = religious;
	}
	public String getIsalone() {
		return isalone;
	}
	public void setIsalone(String isalone) {
		this.isalone = isalone;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
