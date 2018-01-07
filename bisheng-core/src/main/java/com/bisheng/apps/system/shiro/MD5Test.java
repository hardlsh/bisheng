package com.bisheng.apps.system.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/** 
 * @ClassName: MD5Test 
 * @author: shihao.li
 * @Explain: 
 * @date: 2017年3月30日 下午4:36:17  
 */
public class MD5Test {
	
	public static void main(String[] args) {
		
		//原始 密码 
		String source = "000000";
		//盐
		String salt = "HXWcjvQWVG0wI3FQBLZpQ3pWj48AV63d";
		//散列次数
		int hashIterations = 2;
		
		//构造方法中：
		//第一个参数：明文，原始密码 
		//第二个参数：盐，通过使用随机数
		//第三个参数：散列的次数，比如散列两次，相当 于md5(md5(''))
		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
		
		String password_md5 =  md5Hash.toString();
		System.out.println(password_md5);
		//第一个参数：散列算法 
		SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
		System.out.println(simpleHash.toString());
	}

}
