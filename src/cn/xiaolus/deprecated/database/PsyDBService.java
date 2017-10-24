package cn.xiaolus.deprecated.database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Properties;

public class PsyDBService {
	private Properties databaseProp = new Properties();
	private Connection conn;
	
	public Hashtable<String, String> getDBInfoFromProperties(InputStream prop) throws Exception{
		databaseProp.load(prop);
		Hashtable<String, String> table = new Hashtable<String, String>();
		table.put("driver", databaseProp.getProperty("driver"));

		table.put("username", databaseProp.getProperty("username"));
		table.put("password", databaseProp.getProperty("password"));
		return table;
	}
	
	public void connertDatabase(String driver,String host,String user,char[] passwd) throws Exception {
		Class.forName(driver);
		conn = (Connection)DriverManager.getConnection(host, user, new String(passwd));
	}
	
	public char[] queryPsyPassword(String schoolid, String personid) throws Exception {
		PreparedStatement sql = conn.prepareStatement("select psypasswd from cspsy where schoolid=? and personid=?");
		sql.setString(1, schoolid);
		sql.setString(2, personid);
		ResultSet result = sql.executeQuery();
		char[] passwd = {};
		if(result.first()==true) {
			passwd = result.getString(1).toCharArray();
		}
		else {
			throw new Exception("无查询结果");
		}
		if(result.next()==true) {
			throw new Exception("数据库中存在重复项目");
		}
		return passwd;
	}
	
	public void initPsyInfo(String schoolid,String name,String personid,String psypasswd) throws Exception{
		PreparedStatement sql = conn.prepareStatement("insert into cspsy values(?,?,?,?)");
		sql.setString(1, schoolid);
		sql.setString(2, name);
		sql.setString(3, personid);
		sql.setString(4, psypasswd);
		if(sql.executeUpdate()!=1) {
			throw new Exception("数据库插入异常");
		}
	}
}
