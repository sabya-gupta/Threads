package threads.old.threadpool;
/**
 * With blocking queuess, it is now so easy to write simple producer consumer.
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerWithBlockingQueue {

	public static void main(String[] args) {

		BlockingQueue<BlockingQueueJob> bq = new LinkedBlockingQueue<>();

		ExecutorService es = Executors.newFixedThreadPool(4);

		for(int i=0; i<2; i++) {
			BlockingProducer bp1 = new BlockingProducer();
			bp1.bq = bq;
			es.execute(bp1);
		}
		
		for(int i=0; i<30; i++) {
			BlockingConsumer bc1 = new BlockingConsumer();
			bc1.bq = bq;
			es.execute(bc1);
		}
	}
}

class BlockingQueueJob {
	public String jobName;

	public void doJob() {
		System.out.println("Doing " + jobName);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class BlockingProducer implements Runnable {
	public BlockingQueue<BlockingQueueJob> bq;

	@Override
	public void run() {
		int cntr = 0;
		while (true) {
			String jobName = "[" + Thread.currentThread().getName() + " - " + (cntr++) + "]";
			BlockingQueueJob bqj = new BlockingQueueJob();
			bqj.jobName = jobName;
			try {
				System.out.println("Putting " + jobName);
				bq.put(bqj);
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}

class BlockingConsumer implements Runnable {
	public BlockingQueue<BlockingQueueJob> bq;

	@Override
	public void run() {
		while (true) {
			BlockingQueueJob bqj;
			try {
				bqj = bq.take();
				bqj.doJob();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}