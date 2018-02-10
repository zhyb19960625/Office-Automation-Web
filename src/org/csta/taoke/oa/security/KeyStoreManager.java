package org.csta.taoke.oa.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import javax.crypto.SecretKey;

/**
 * 功能：
 * 密钥库管理类，默认使用JCEKS密钥库
 * 
 * 修订版本：
 * 2017-06-23 首次编写
 * 
 * @author 路伟饶
 *
 */
public class KeyStoreManager {
	private static KeyStoreManager instance;
	private File keystoreFile;
	private KeyStore keystoreInst;
	private char[] password;
	
	private KeyStoreManager() {}
	/**
	 * 获得密钥库管理类的实例
	 * @return
	 * 返回密钥库管理类的实例
	 * @throws Exception
	 * 密钥库类型不正确
	 */
	public static KeyStoreManager getInstance() throws Exception{
		if(instance==null) {
			instance=new KeyStoreManager();
		}
		instance.keystoreInst=KeyStore.getInstance("JCEKS");
		return instance;
	}
	/**
	 * 从文件加载密钥库，该方法不会立即验证密码的正确性
	 * @param file
	 * 密钥库文件
	 * @param password
	 * 密钥库的密码，不会在此处验证密钥库密码的正确性
	 */
	public void openKeyStoreFromFile(File file,char[] password) {
		this.password=(char[]) password.clone();
		keystoreFile=file;
	}
	/**
	 * 创建一个新的密钥库并保存到文件
	 * @param file
	 * 密钥库文件
	 * @param password
	 * 密钥库的密码，此处不会进行二次验证，必须保证此密码的正确性
	 * @throws Exception
	 * 文件异常
	 */
	public void createKeyStore(File file,char[] password) throws Exception{
		this.password=(char[]) password.clone();
		keystoreFile=file;
		FileOutputStream out=new FileOutputStream(keystoreFile);
		keystoreInst.load(null, password);
		keystoreInst.store(out, password);
		out.close();
	}
	/**
	 * 从密钥库读取一个对称密码的密钥
	 * @param entryName
	 * 入口名称
	 * @return
	 * 构建好的密钥对象
	 * @throws Exception
	 * 入口不存在或者不是对称密码的密钥
	 */
	public SecretKey getSymmetricKey(String entryName) throws Exception{
		FileInputStream in=new FileInputStream(keystoreFile);
		keystoreInst.load(in, password);
		KeyStore.ProtectionParameter protParam=new KeyStore.PasswordProtection(password);
		KeyStore.SecretKeyEntry keyEntry=(SecretKeyEntry)keystoreInst.getEntry(entryName, protParam);
		SecretKey key=keyEntry.getSecretKey();
		in.close();
		return key;
	}
	
}

