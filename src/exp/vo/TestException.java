package exp.vo;

import com.shangde.common.util.MD5;

public class TestException {
	private static void rangeCheck(int arrayLen, int fromIndex, int toIndex) {
		     if (fromIndex > toIndex)
		         throw new IllegalArgumentException("fromIndex(" + fromIndex +
		                    ") > toIndex(" + toIndex+")");
		     if (fromIndex < 0)
		         throw new ArrayIndexOutOfBoundsException(fromIndex);
		     if (toIndex > arrayLen)
		         throw new ArrayIndexOutOfBoundsException(toIndex);
		 }

	public static void main(String []a ){
		System.out.println("75134296----"+MD5.getMD5("75134296"+"liuqg").substring(0,12));
		System.out.println("75164549----"+MD5.getMD5("75164549"+"liuqg").substring(0,12));
		System.out.println("75162425----"+MD5.getMD5("75162425"+"liuqg").substring(0,12));
		System.out.println("75129239----"+MD5.getMD5("75129239"+"liuqg").substring(0,12));
		System.out.println("75131830----"+MD5.getMD5("75131830"+"liuqg").substring(0,12));
		System.out.println("75156831----"+MD5.getMD5("75156831"+"liuqg").substring(0,12));
		System.out.println("75157801----"+MD5.getMD5("75157801"+"liuqg").substring(0,12));
		
		
		 
		
		
		
	} 
}
