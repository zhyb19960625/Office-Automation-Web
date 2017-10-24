package cn.xiaolus.oa.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Hashtable;
import java.util.Properties;

public class OADBConnector {
	private Properties databaseProp = new Properties();
	protected Connection conn;
	/**
	 * 从配置文件中获取驱动类名、用户名和密码。
	 * @param prop
	 * @return 包含上述内容的Hash表
	 * @throws Exception 配置文件不存在或者缺少条目
	 */
	public Hashtable<String, String> getDBInfoFromProperties(InputStream prop) throws Exception{
		databaseProp.load(prop);
		Hashtable<String, String> table = new Hashtable<String, String>();
		table.put("driver", databaseProp.getProperty("driver"));
		table.put("username", databaseProp.getProperty("username"));
		table.put("password", databaseProp.getProperty("password"));
		prop.close();
		return table;
	}
	/**
	 * 连接到数据库
	 * @param driver 驱动类名
	 * @param host 数据库服务器主机
	 * @param user 数据库用户名
	 * @param passwd 数据库密码
	 * @throws Exception 无法连接到数据库
	 */
	public void connectDB(String driver,String host,String user,char[] passwd) throws Exception {
		Class.forName(driver);
		conn = (Connection)DriverManager.getConnection(host, user, new String(passwd));
	}
	
	public void connectDBWithDefaultProp() throws Exception {
		Hashtable<String, String> table = this.getDBInfoFromProperties(
				OADBConnector.class.getResourceAsStream("/config/mariadb.properties")
			);
			this.connectDB(
					table.get("driver"), 
					"jdbc:mysql://db.cstacauc.cn/cspsy?useSSL=true",
					table.get("username"), 
					table.get("password").toCharArray()
			);
	}
	
	public void disconnectDB() throws Exception {
		conn.close();
	}
	
}
