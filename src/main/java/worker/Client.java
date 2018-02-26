package worker;

import java.util.ArrayList;
import java.util.List;

import worker.domain.Order;
import worker.domain.OrderDetail;

public class Client {
	private static OrderClient orderClient;
	public static void main(String[] args) throws InterruptedException {
		
			List<Order> orders = new ArrayList<Order>();
			
			orders.add(buildOrder(1,1001));
			orders.add(buildOrder(2,1001,1002));
			orders.add(buildOrder(3,1004));
			orders.add(buildOrder(4,1002,1005));
			orders.add(buildOrder(5,1004));
			
			for(Order o:orders) {
				// 一单多品
				if(o.getOrderDetails().size()>1) {
					if(orderClient.oneOrderOneSkuQueue.isCanPut(o)) {
						orderClient.oneOrderManySkuQueue.put(0, o);
					}
				}else {
					if(orderClient.oneOrderManySkuQueue.isCanPut(o)) {
						long skuId = o.getOrderDetails().get(0).getSkuId();
						int w = Integer.valueOf(String.valueOf(skuId%10));
						orderClient.oneOrderOneSkuQueue.put(w, o);
					}
				}
			}
	}
	
   public static Order buildOrder(long orderId,long...skuIds){
	   Order o = new Order();
	   o.setOrderId(orderId);
	   List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	   for(long skuid:skuIds) {
		   OrderDetail d1 = new OrderDetail(); 
		   d1.setOrderId(orderId);
		   d1.setSkuId(skuid); 
		   orderDetails.add(d1);
	   }
       o.setOrderDetails(orderDetails);
	   return o;
   }
	
}
