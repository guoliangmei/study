package worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import worker.domain.Order;
import worker.domain.OrderDetail;

public class OrderQueue {
	// 存储结构 skuId orderId
	public  Multimap<Long,Long> skuOrderMap = ArrayListMultimap.create();
	public  List<LinkedBlockingQueue<Order>> oneOrderOneSkuListQueue;
	
	private Lock putlock = new ReentrantLock(); 
	private Condition putCondition = putlock.newCondition();
	
	public OrderQueue(int qsize) {
		oneOrderOneSkuListQueue = new ArrayList<LinkedBlockingQueue<Order>>(qsize);
		for(int i=0;i<qsize;i++) {
			oneOrderOneSkuListQueue.add(new LinkedBlockingQueue<Order>());
		}
	}
	
	public void put(int whichQ,Order o){
		try {
			for(OrderDetail od:o.getOrderDetails()) {
            	// SKU orderId
            	skuOrderMap.put(od.getSkuId(), od.getOrderId());
            }
			oneOrderOneSkuListQueue.get(whichQ).put(o);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
		}
	    
	}
	
	public Order take(int whichQ) throws InterruptedException {
		Order o = oneOrderOneSkuListQueue.get(whichQ).take();
		return o;
	}
	
	public void end(Order o) {
		putlock.lock();
		try {
			// 循环放入 skuId orderId
			for(OrderDetail od:o.getOrderDetails()) {
				skuOrderMap.remove(od.getSkuId(), od.getOrderId());
			}
			putCondition.signalAll();
		}finally {
			putlock.unlock();
		}
	}

	public boolean isCanPut(Order o) {
		putlock.lock();
		 try {
		   for(OrderDetail od:o.getOrderDetails()) {
			   while (skuOrderMap.get(od.getSkuId()).size() != 0) {
				   putCondition.await();
			   }
		   }	 
		 } catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			 putlock.unlock();
	    }
		return true;
	}
	
	
	
	
}
