package threads.neo.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 
 * @author Sabyasachi.Gu
 * The problem statement is that:
 * 1. You have to communicate with other systems aparallely
 * 2. get the response
 * 3. Wait for a specified time and if response is not available then come out
 */

public class TheExecutorWithFuture {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		Future<String> fut1 = executor.submit(new Communicator1());
		Future<String> fut2 = executor.submit(new Communicator2());
		
		try {
			System.out.println(fut1.get(1, TimeUnit.SECONDS));
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			System.out.println(fut2.get(10, TimeUnit.SECONDS));
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			executor.shutdownNow();			
		}
		
		
	}
}

class Communicator1 implements Callable<String>{

	@Override
	public String call() throws Exception {
		Thread.sleep(5000);
		System.out.println("1 finished");
		return "Communicator1 finished";
	}
	
}

class Communicator2 implements Callable<String>{

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		System.out.println("2 finished");
		return "Communicator2 finished";
	}
	
}
