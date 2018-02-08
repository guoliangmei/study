package worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderQueue<E> {
	
	private final AtomicInteger count = new AtomicInteger();
	public  List<LinkedBlockingQueue<E>> oneOrderOneSkuListQueue;
	
	private Lock lock = new ReentrantLock(); 
	public Condition condition = lock.newCondition();
	
	public OrderQueue(int qsize) {
		oneOrderOneSkuListQueue = new ArrayList<LinkedBlockingQueue<E>>(qsize);
	}
	
	public void put(int whichQ,E o) throws InterruptedException {
		oneOrderOneSkuListQueue.get(whichQ).put(o);
		count.getAndIncrement();
	}
	
	public E take(int whichQ) throws InterruptedException {
		E o = oneOrderOneSkuListQueue.get(whichQ).take();
		if(count.getAndDecrement() == 0) {
			condition.signal();
		}
		return o;
	}
	
	public boolean isEmpty() throws InterruptedException {
	     while (count.get() != 0) {
	    	 condition.await();
         }
	     return true;
	}
	
	
}
