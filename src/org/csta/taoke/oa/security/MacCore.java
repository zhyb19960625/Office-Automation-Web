package org.csta.taoke.oa.security;

import java.io.File;
import java.io.FileInputStream;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

/**
 * 功能：
 * 消息认证码类
 * 
 * 修订版本：
 * 2017-06-23 首次编写
 * 
 * @author 路伟饶
 *
 */
public class MacCore {
	private static MacCore instance;
	private String algorithm;
	/**
	 * 获得消息认证码类的实例
	 * @param algorithm
	 * 消息认证算法
	 * @return
	 * 消息认证码类的实例
	 */
	public static MacCore getInstance(String algorithm) {
		if(instance==null) {
			instance=new MacCore();
		}
		instance.algorithm=algorithm;
		return instance;
	}
	/**
	 * 获得字符串的消息认证码
	 * @param message
	 * 消息字符串
	 * @param password
	 * 用于消息认证的密码
	 * @return
	 * 消息认证码
	 * @throws Exception
	 */
	public String getMac(String message,char[] password) throws Exception {
		byte[] in=message.getBytes();
		Mac mac=Mac.getInstance(algorithm);
		SecretKey key=getKeyByPassword(password);
		mac.init(key);
		mac.update(in);
		byte[] out=mac.doFinal();
//		return new HexBinaryAdapter().marshal(out);
		return Base64.getEncoder().encodeToString(out);
	}
	/**
	 * 校验字符串的消息认证码
	 * @param message
	 * 消息字符串
	 * @param password
	 * 用于消息认证的密码
	 * @param value
	 * 消息认证码
	 * @return
	 * 是否通过验证
	 * @throws Exception
	 */
	public boolean checkMac(String message,char[] password,String value) throws Exception {
		String currentValue=getMac(message, password);
		return currentValue.equals(value);
	}
	/**
	 * 获得文件的消息验证码
	 * @param fileToMac
	 * 文件对象
	 * @param password
	 * 用于消息认证的密码
	 * @return
	 * 消息认证码
	 * @throws Exception
	 */
	public String getMac(File fileToMac,char[] password) throws Exception{
		FileInputStream fin=new FileInputStream(fileToMac);
		Mac mac=Mac.getInstance(algorithm);
		SecretKey key=getKeyByPassword(password);
		mac.init(key);
		byte[] buffer=new byte[1024];
		int n;
		while((n=fin.read(buffer))!=-1) {
			mac.update(buffer, 0, n);
		}
		byte[] out=mac.doFinal();
		fin.close();
//		return new HexBinaryAdapter().marshal(out);
		return Base64.getEncoder().encodeToString(out);
	}
	/**
	 * 验证文件的消息认证码
	 * @param fileToCheck
	 * 文件对象
	 * @param password
	 * 用于消息认证的密码
	 * @param value
	 * 消息认证码
	 * @return
	 * 是否验证通过
	 * @throws Exception
	 */
	public boolean checkMac(File fileToCheck,char[] password,String value) throws Exception {
		String currentValue=getMac(fileToCheck, password);
		return currentValue.equals(value);
	}
	/**
	 * 根据密码生成消息认证的密钥
	 * @param password
	 * 密码
	 * @return
	 * 构建好的密钥对象
	 * @throws Exception
	 */
	private SecretKey getKeyByPassword(char[] password) throws Exception{
		KeyGenerator generator=KeyGenerator.getInstance(algorithm);
		byte[] passwordValue=new String(password).getBytes();
		SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(passwordValue);
		generator.init(random);
		return generator.generateKey();
	}
}
