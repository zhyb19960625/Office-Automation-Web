package org.csta.taoke.oa.common.entity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private static String path;
	private static final Properties PROP=new Properties();
	public static String ROOT,CONTENT;
	private Config() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		path = path.replace("file:", "");
		path = path.replace("classes/", "");
		System.out.println(path);
	}
	public void initConfig() throws IOException {
		InputStream inputStream=Config.class.getResourceAsStream(path);
		PROP.load(inputStream);
		ROOT=PROP.getProperty("root");
		CONTENT=PROP.getProperty("contentPath");
		System.out.println(ROOT);
	}
	public static String getPath() {
		return path;
	}
	public static void setPath(String path) {
		Config.path = path;
	}
}
