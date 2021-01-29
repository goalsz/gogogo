package zhanglin;

import java.net.InetAddress;
import java.net.UnknownHostException;
public class NetTool{
	
	public static void main( String[] args){
		String url = "www.baidu.com";
		
		InetAddress myServer=null;
		try {
			myServer=InetAddress.getByName(url);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		System.out.println("name : " + myServer.getHostName());
		System.out.println("ip : " + myServer.getHostAddress());
		
	}
}