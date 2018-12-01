package threads.old.threadpool;

/*
 * Problem statement
 * =================
 * 
 * A group a producers go on producing jobs for a group of consumers 
 * who wait for the jobs.....
 */


public class ProducerConsumerMainClass {

	public static void main(String[] args) {
		JobPool tp = new JobPool();
		//Instantiate the Runner threads and start them
		for(int i=0; i<10; i++) {
			Thread tc = new Thread(new Consumer(tp));
			tc.setName("Consumer "+i);
			tc.start();
		}
		
		for(int i=0; i<5; i++) {
			Thread tpp = new Thread(new Producer(tp));
			tpp.setName("Producer "+i);
			tpp.start();
		}
	}
}


class Producer implements Runnable{
	public Producer(JobPool jp) {
		super();
		this.jp = jp;
	}
	JobPool jp;
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
	
class Consumer implements Runnable{
	public Consumer(JobPool jp) {
		super();
		this.jp = jp;
	}
	JobPool jp;
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
	


