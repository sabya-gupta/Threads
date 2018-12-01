/**
 * Problem statement
 * =================
 * 
 * Very simple semaphore example. I want to restrict the number of threads that can do a job at the same time.
 * I remember once we used this feature to restrict the DB access from batch during peak load.
 * 
 */

package threads.old.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreExampleMain {

	public static void main(String[] args) {
		JobClass jc = new JobClass();
		for (int i=0; i<10; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						jc.doSomething();
					}
				}
			});
			t.setName("Thread<"+(1+i)+">");
			t.start();
		}
	}
}

class JobClass {
	Semaphore semaphore = new Semaphore(3, true);
	
	public void doSomething() {
		try {
			semaphore.acquire();
			System.out.println(Thread.currentThread().getName()+" is doing the job at "+System.currentTimeMillis());
			Thread.sleep((5000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaphore.release();
		}
	}
}