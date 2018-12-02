package threads.framework.CyclicbarrierFramework;

import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicbarrierServiceWithStrings {

	List<CyclicBarrierWorkerInterface<String, String, String>> listOfWorkers;
	
	CyclicBarrier cb = new CyclicBarrier(4, new Runnable() {
		@Override
		public void run() {
			String aggregatedResult = "";
			System.out.println("Now calling the cyclic barrier runnamble where aggregation happens");
			for (CyclicBarrierWorkerInterface<String, String, String> worker : listOfWorkers) {
				if (worker.getError() == null) {
					aggregatedResult += worker.getResult();
				}
			}

			try {
				// Put the result into DB
				String resultId = "new result Id : " + System.currentTimeMillis();
				Thread.sleep(2000);
				System.out.println("AGGREGATED RESULT = "+aggregatedResult);
				System.out.println("The next input for the Cyclic barrier Threads " + resultId);
				for (CyclicBarrierWorkerInterface<String, String, String> worker : listOfWorkers) {
					worker.setNexttaskInput(resultId);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	});

	public void dotask(List<CyclicBarrierWorkerInterface<String, String, String>> listOfWorkers) {

		ExecutorService es = Executors.newFixedThreadPool(listOfWorkers.size());
		
		this.listOfWorkers = listOfWorkers;

		for (CyclicBarrierWorkerInterface<String, String, String> worker : listOfWorkers) {
			
			System.out.println("Setting cb for "+worker.getInput());
			worker.setCyclicBarrier(cb);
			es.execute(worker);
		}

		es.shutdown();

	}

}
