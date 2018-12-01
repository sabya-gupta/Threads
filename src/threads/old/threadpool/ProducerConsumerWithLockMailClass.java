package threads.old.threadpool;

/**
 * 
 * @author Sabyasachi.Gu
 *
 * The producer consumer with Lock.... simple
 * 
 */

public class ProducerConsumerWithLockMailClass {

	public static void main(String[] args) {
		JobPoolWithLock tp = new JobPoolWithLock();
		//Instantiate the Runner threads and start them
		for(int i=0; i<10; i++) {
			Thread tc = new Thread(new ConsumerForLock(tp));
			tc.setName("Consumer "+i);
			tc.start();
		}
		
		for(int i=0; i<5; i++) {
			Thread tpp = new Thread(new ProducerForLock(tp));
			tpp.setName("Producer "+i);
			tpp.start();
		}
	}
}


class ProducerForLock implements Runnable{
	public ProducerForLock(JobPoolWithLock jp) {
		super();
		this.jp = jp;
	}
	JobPoolWithLock jp;
	int cntr;
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			jp.putJob(new JobImpl("Thrd: <"+Thread.currentThread().getName() + ">"+(cntr++)));
		}
	}
}
	
class ConsumerForLock implements Runnable{
	public ConsumerForLock(JobPoolWithLock jp) {
		super();
		this.jp = jp;
	}
	JobPoolWithLock jp;
	public void run() {
		while(true) {
			Job job = jp.getJob();
			if(job != null) {
				job.doJob();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
	
