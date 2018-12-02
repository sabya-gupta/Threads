package threads.neo.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author Sabyasachi.Gupta
 * This is a very simple example for executor service...
 * Basically you just 
 *
 */

public class TheExecutorExampleMain {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		try {
			executor.execute(new CommunicatorSimple1());
			executor.execute(new CommunicatorSimple2());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			executor.shutdown();
		}
		System.out.println("In the meantime the Main goes on in its own thread....!");
	}
}

class CommunicatorSimple1 implements Runnable{

	@Override
	public void run(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("1 finished");
	}
	
}

class CommunicatorSimple2 implements Runnable{

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("2 finished");
	}
	
}

