package org.csta.taoke.oa.database;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;;

/**
 * 功能：
 * 登录到主数据库
 * 
 * 修订版本：
 * 2017-12-17 首次编写
 * 
 * @author 路伟饶
 * 
 */
public class DataBaseConnector {
	
	private String dbDriverName;
	private URL dbHost;
	private String dbUser;
	private char[] dbPassword;
	private boolean isConnected;
	private Connection dbConnection;
	
	/**
	 * 构造方法
	 * @param driver 数据库驱动类名
	 * @param host 数据库主机名/IP地址
	 * @param user 数据库用户名
	 * @param password 数据库密码
	 */
	public DataBaseConnector(String driver, URL host, String user, char[] password) {
		this.isConnected = false;
		dbDriverName = driver;
		dbHost = host;
		dbUser = user;
		dbPassword = password;
	}
	/**
	 * 连接到指定的数据库
	 * @return 一个数据库连接
	 * @throws ClassNotFoundException 提供的数据库驱动类名不正确
	 * @throws SQLException 在连接数据库时出错
	 */
	public Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName(dbDriverName);
		dbConnection = (Connection) DriverManager.getConnection (
						dbHost.getHost(),
						dbUser,
						new String(dbPassword)
				);
		this.isConnected = true;
		return dbConnection;
	}
	/**
	 * 获得到数据库的连接
	 * @return 数据库连接，如果尚未连接则返回空
	 */
	public Connection getConnection() {
		if (isConnected) {
			return dbConnection;
		}
		else {
			return null;
		}
	}
	/**
	 * 关闭数据库连接
	 * @throws SQLException 在关闭数据库连接时出错
	 */
	public void close() throws SQLException {
		this.isConnected = false;
		dbConnection.close();
	}
}


