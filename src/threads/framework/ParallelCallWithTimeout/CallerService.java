package threads.framework.ParallelCallWithTimeout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

public class CallerService {

	int numberOfThreads=0;
	
	public <ReturnType, InputType> void doService(List<FrameworkCallableInterface<ReturnType, InputType>>
		callableObjectList) {
		
		ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
		
		List<Future<ReturnType>> listOfFutures = new ArrayList<>();
				
		for(int i=0; i<numberOfThreads; i++) {
			Future<ReturnType> future = executor.submit(callableObjectList.get(i));
			listOfFutures.add(future);
		}
		
		int i=0;
		for (Future<ReturnType> future : listOfFutures){
			FrameworkCallableInterface<ReturnType, InputType> callableObj = callableObjectList.get(i);
			try {
				ReturnType result = future.get(callableObj.getTime(), callableObj.getTimeUnit());
				callableObj.setResult(result);
				i++;
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				callableObj.setError(e.toString());
				i++;
			}
		}
		
		executor.shutdownNow();
		
	}

	public int getNumberOfThreads() {
		return numberOfThreads;
	}

	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}
	
	
	
}
