package worker;

import java.util.concurrent.LinkedBlockingQueue;

public class OrderOneOrderOneSkuExecutor implements Runnable{
	
    private int num;
    
    public OrderOneOrderOneSkuExecutor(int num){
    	this.num = num;
    }
	@Override
	public void run() {
		LinkedBlockingQueue queue = OrderClient.oneOrderOneSkuListQueue.get(num);
		try {
			while(true){
				System.out.println("OrderOneOrderOneSkuExecutor-->"+num+"---->" +  queue.take().toString());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
