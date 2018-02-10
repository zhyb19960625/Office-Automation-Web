package org.csta.taoke.oa.security;

import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Base64;

//import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
/**
 * 功能：
 * 消息摘要类
 * 
 * 修订版本：
 * 2017-06-23 首次编写
 * 
 * @author 路伟饶
 *
 */
public class DigestCore {
	private static DigestCore instance;
	private String algorithm;
	/**
	 * 获得消息摘要类的实例
	 * @param algorithm
	 * 消息摘要算法
	 * @return
	 * 消息摘要对象
	 */
	public static DigestCore getInstance(String algorithm) {
		if(instance==null) {
			instance=new DigestCore();
		}
		instance.algorithm=algorithm;
		return instance;
	}
	private DigestCore(){}
	/**
	 * 获得字符串消息摘要值的方法
	 * @param message
	 * 消息字符串
	 * @return
	 * 消息摘要值
	 * @throws Exception
	 */
	public String getHashValue(String message) throws Exception {
		byte[] in=message.getBytes();
		MessageDigest md=MessageDigest.getInstance(algorithm);
		byte[] out=md.digest(in);
		return Base64.getEncoder().encodeToString(out);
//		return new HexBinaryAdapter().marshal(out);
	}
	/**
	 * 校验字符串消息摘要值的方法
	 * @param message
	 * 消息字符串
	 * @param checksum
	 * 消息摘要值
	 * @return
	 * 是否通过校验
	 * @throws Exception
	 */
	public boolean checkHashValue(String message,String checksum) throws Exception{
		String hashValue=this.getHashValue(message);
		return hashValue.equals(checksum);
	}
	/**
	 * 获得文件消息摘要值的方法
	 * @param fileToHash
	 * 文件对象
	 * @return
	 * 消息摘要值
	 * @throws Exception
	 */
	public String getHashValue(File fileToHash) throws Exception {
		FileInputStream in=new FileInputStream(fileToHash);
		MessageDigest md=MessageDigest.getInstance(algorithm);
		DigestInputStream din=new DigestInputStream(in, md);
		byte[] buffer=new byte[1024];
		while((din.read(buffer))!=-1);
		byte[] out=md.digest();
		din.close();
		return Base64.getEncoder().encodeToString(out);
//		return new HexBinaryAdapter().marshal(out);
	}
	/**
	 * 校验文件消息摘要值的方法
	 * @param fileToHash
	 * 文件对象
	 * @param checksum
	 * 文件的消息摘要值
	 * @return
	 * 是否通过校验
	 * @throws Exception
	 */
	public boolean checkHashValue(File fileToHash,String checksum) throws Exception{
		String hashValue=this.getHashValue(fileToHash);
		return hashValue.equals(checksum);
	}
}
