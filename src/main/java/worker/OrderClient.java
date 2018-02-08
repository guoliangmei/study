package worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OrderClient {
	private final static int oneOrderOneSku = 10;
	private final static int oneOrderManySku = 1;
	// 一单一品订单queue
	public static List<LinkedBlockingQueue> oneOrderOneSkuListQueue = new ArrayList<LinkedBlockingQueue>(oneOrderOneSku);
	public static ThreadPoolExecutor oneOrderOneSkuExecutor  = new ThreadPoolExecutor(
			oneOrderOneSku,     //core
			oneOrderOneSku, 	//max
			120L, 	//2分钟
			TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(10));
	// 一单一品订单queue
	public static List<LinkedBlockingQueue> oneOrderManySkuListQueue = new ArrayList<LinkedBlockingQueue>(oneOrderManySku);
	public static ThreadPoolExecutor oneOrderManySkuExecutor  = new ThreadPoolExecutor(
			oneOrderManySku,     //core
			oneOrderManySku, 	//max
			120L, 	//2分钟
			TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(10));
	 
	static{

		
		for(int i=0;i<oneOrderOneSku;i++){
			oneOrderOneSkuListQueue.add(new LinkedBlockingQueue());
			oneOrderOneSkuExecutor.execute(new OrderOneOrderOneSkuExecutor(i));
		}
		for(int i=0;i<oneOrderManySku;i++){
			oneOrderManySkuListQueue.add(new LinkedBlockingQueue());
			oneOrderManySkuExecutor.execute(new OrderOneOrderManySkuExecutor(i));
		}
		
	}
	
	
}
