package foo;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
/**
 * Hello world!
 *
 */
public class App 
{
	public static class Task implements Callable<String>{

		public String call() throws Exception {
			 String tid = String.valueOf(Thread.currentThread().getId());
	         System.out.printf("Thread#%s : in call\n", tid);
	         return tid;
		}
		
	}
    public static void main( String[] args ) throws InterruptedException, ExecutionException{
//      ByteBuffer b = ByteBuffer.allocate(15);
//      System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
//      for(int i=0;i<10;i++){
//    	  b.put((byte) i);
//      }
//      System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
//      b.flip(); // 重置position
//      System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
//    	String str = "Test string";
//    	StringBuilder strBuilder = new StringBuilder(str);
//    	strBuilder.setCharAt(1, 'X');
//    	str=strBuilder.toString();
//    	System.out.println(str);
//    	Integer a = 1270;
//    	Integer b = 1270;
//    	int c = 1270;
//    	System.out.println(a == b);
//    	System.out.println(a.equals(b));
//    	System.out.println(a.equals(c));
//    	char a = '9';
//    	System.out.println(Character.getNumericValue(a));
    	
    	int a = 202;
    	int b = 202;
    	System.out.println(a==b);
    	
    }
}
