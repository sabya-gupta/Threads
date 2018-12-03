package threads.framework.CountDownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CountDownLatchServiceWithAllString implements CountDownLatchServiceInterface<String, String, String> {

	public String dotask(List<CountDownLatchTaskInterface<String, String>> taskList) {
		
		CountDownLatch cdl = new CountDownLatch(taskList.size());
		
		ExecutorService executor = Executors.newFixedThreadPool(taskList.size());
		
		for(CountDownLatchTaskInterface<String, String> task : taskList) {
			task.setlatch(cdl);
			executor.execute(task);
		}
		
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		//now you continue to do the main task.
		
		//collate the results:
		String res = "";
		System.out.println("Aggregating the result");
		for(CountDownLatchTaskInterface<String, String> task : taskList) {
			if(task.getError()==null)
			res += task.getresult().toString();
		}
		System.out.println(">>>> Final result : "+res);
		
		executor.shutdown();
		
		return res;
		
	}
}
