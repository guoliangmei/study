package com.jd.worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.jd.worker.domain.Order;
import com.jd.worker.domain.OrderDetail;
/**
 * 
 * jvm 调优
 * https://www.cnblogs.com/rainy-shurun/p/5732341.html
 * 
 * @author meiguoliang
 *
 */
public class Client {
	private static OrderClient orderClient;

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {

			List<Order> orders = new ArrayList<Order>();
			orders.add(buildOrder(2, 1001,1002,1003,1004));
			orders.add(buildOrder(1, 1001));
			orders.add(buildOrder(3, 1004));
			orders.add(buildOrder(4, 1002, 1005));
			orders.add(buildOrder(5, 1004));
			processOrders(orders);
			
		}
	}
	
	public static void processOrders(List<Order> orders) throws Exception {
		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch end = new CountDownLatch(orders.size());

		OrderConstants.setStart(start);
		OrderConstants.setEnd(end);
        
		OrderConstants.getStart().countDown();
		
		long startTime = System.currentTimeMillis();// 获取当前时间
		for (Order o : orders) {
			// 一单多品
			if (o.getOrderDetails().size() > 1) {
				if (orderClient.oneOrderOneSkuQueue.isCanPut(o)) {
					orderClient.oneOrderManySkuQueue.put(0, o);
				}
			} else {
				if (orderClient.oneOrderManySkuQueue.isCanPut(o)) {
					long skuId = o.getOrderDetails().get(0).getSkuId();
					int w = Integer.valueOf(String.valueOf(skuId % OrderConstants.ONE_ORDER_ONE_SKU_QUEUE_CONUT));
					orderClient.oneOrderOneSkuQueue.put(w, o);
				}
			}
		}
		// 所有线程都跑完后开始统计总的执行时间
		OrderConstants.getEnd().await();
		long endTime = System.currentTimeMillis();
		System.out.println("执行总时间:" + (endTime - startTime) + "ms");
	}
	

	public static Order buildOrder(long orderId, long... skuIds) {
		Order o = new Order();
		o.setOrderId(orderId);
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		for (long skuid : skuIds) {
			OrderDetail d1 = new OrderDetail();
			d1.setOrderId(orderId);
			d1.setSkuId(skuid);
			orderDetails.add(d1);
		}
		o.setOrderDetails(orderDetails);
		return o;
	}

}
