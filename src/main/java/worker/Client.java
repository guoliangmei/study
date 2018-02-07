package worker;

public class Client {
	private static OrderClient orderClient;
	public static void main(String[] args) throws InterruptedException {
		for(int i = 0;i<1000;i++){
			
			orderClient.oneOrderOneSkuListQueue.get(i%10).put("oneOrderOneSku" + i);
			orderClient.oneOrderManySkuListQueue.get(0).put("oneOrderManySku" + i);
			Thread.sleep(5000L);
		}
	}
}
