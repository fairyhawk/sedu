package exp.vo;

import java.rmi.Naming;

public class RMIClient {
	public static void main(String[] args) {
		try {
			 IRMIService clinet =(IRMIService) Naming.lookup("rmi://localhost:8888/RHello"); 
			 clinet.saysmttosmone("测试");
			 System.out.println("hello");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
