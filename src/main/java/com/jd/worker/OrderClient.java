package com.jd.worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OrderClient {
	private final static int oneOrderOneSku = OrderConstants.ONE_ORDER_ONE_SKU_QUEUE_CONUT;
	private final static int oneOrderManySku = 1;
	
	// 一单一品订单queue
	public static OrderQueue oneOrderOneSkuQueue = new OrderQueue(oneOrderOneSku);
	
	public static ThreadPoolExecutor oneOrderOneSkuExecutor  = new ThreadPoolExecutor(
			oneOrderOneSku,     //core
			oneOrderOneSku, 	//max
			120L, 	//2分钟
			TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(10));
	// 一单一品订单queue
	public static OrderQueue oneOrderManySkuQueue = new OrderQueue(oneOrderManySku);
	
	public static ThreadPoolExecutor oneOrderManySkuExecutor  = new ThreadPoolExecutor(
			oneOrderManySku,     //core
			oneOrderManySku, 	//max
			120L, 	//2分钟
			TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(10));
	 
	static {
		for(int i=0;i<oneOrderOneSku;i++){
			oneOrderOneSkuExecutor.execute(new OrderOneOrderOneSkuExecutor(i,oneOrderOneSkuQueue));
		}
		for(int i=0;i<oneOrderManySku;i++){
			oneOrderManySkuExecutor.execute(new OrderOneOrderManySkuExecutor(i,oneOrderManySkuQueue));
		}
		
	}
	
	
}
