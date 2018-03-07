package com.jd.worker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import com.jd.worker.domain.Order;

public class OrderOneOrderManySkuExecutor implements Runnable{
	
	private OrderQueue orderQueue;
    private int num;
    
    private AtomicInteger count = new AtomicInteger(0);
    
    public OrderOneOrderManySkuExecutor(int num,OrderQueue orderQueue){
    	this.orderQueue = orderQueue;
    	this.num = num;
    }
	@Override
	public void run() {
		
		try {
			while(true){
				OrderConstants.getStart().await();
				// 订单处理开始
				Order o = orderQueue.take(num);
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				long startTime = System.currentTimeMillis();//获取当前时间
				Thread.sleep(1000L);
				long endTime = System.currentTimeMillis();
				// 处理订单
				System.out.println("一单多品queue:"+num+"----> 订单号:" + o.getOrderId() + ",处理日期:" + sdf.format(d)+",运行时间:" + (endTime-startTime)+"ms"+",处理订单个数:" + count.incrementAndGet());
				// 订单处理结束
				orderQueue.end(o);
				OrderConstants.getEnd().countDown();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
