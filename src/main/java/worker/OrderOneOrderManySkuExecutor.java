package worker;

import java.util.concurrent.LinkedBlockingQueue;

public class OrderOneOrderManySkuExecutor implements Runnable{
	
    private int num;
    
    public OrderOneOrderManySkuExecutor(int num){
    	this.num = num;
    }
	@Override
	public void run() {
		LinkedBlockingQueue queue = OrderClient.oneOrderManySkuListQueue.get(num);
		try {
			while(true){
				System.out.println("OrderOneOrderManySkuExecutor-->"+num+"---->" + queue.take().toString());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
