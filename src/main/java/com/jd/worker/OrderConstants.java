package com.jd.worker;

import java.util.concurrent.CountDownLatch;

public class OrderConstants {
    public static CountDownLatch start;
    public static CountDownLatch end;
    
    public static final int ONE_ORDER_ONE_SKU_QUEUE_CONUT = 10;
    
	public static CountDownLatch getStart() {
		return start;
	}
	public static void setStart(CountDownLatch start) {
		OrderConstants.start = start;
	}
	public static CountDownLatch getEnd() {
		return end;
	}
	public static void setEnd(CountDownLatch end) {
		OrderConstants.end = end;
	}
    
}
