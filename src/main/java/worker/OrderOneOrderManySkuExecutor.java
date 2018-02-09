package worker;

import com.alibaba.fastjson.JSON;

import worker.domain.Order;

public class OrderOneOrderManySkuExecutor implements Runnable{
	
	private OrderQueue orderQueue;
    private int num;
    
    public OrderOneOrderManySkuExecutor(int num,OrderQueue orderQueue){
    	this.orderQueue = orderQueue;
    	this.num = num;
    }
	@Override
	public void run() {
		
		try {
			while(true){
				// 订单处理开始
				Order o = orderQueue.take(num);
				// 处理订单
				System.out.println("OrderOneOrderManySkuExecutor--> queue:"+num+"----> oid:" + o.getOrderId() + "已经处理完");
				// 订单处理结束
				orderQueue.end(o);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
