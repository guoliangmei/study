package foo;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;

import java.net.UnknownHostException;
import java.util.Vector;
import java.util.concurrent.Callable;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws InterruptedException {

		for(int i=0;i<10;i++){

			// Transaction t = Cat.newTransaction("Task", "state");
			Transaction t = Cat.getProducer().newTransaction("URL", "MyPage");
			try{
				Cat.logEvent("URL", "MyPage",Event.SUCCESS, "ip=${serverIp}");

				Cat.logMetricForCount("metric.key");
   			    Cat.logMetricForDuration("metric.key", 5);
				// yourBusiness();
				t.setStatus(Transaction.SUCCESS);
			}catch (Exception e){
				t.setStatus(e);
				Cat.logError(e);
			}finally {
				t.complete();
			}
		}
     //  Thread.sleep(5000000L);
	}
}
