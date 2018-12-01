package threads.old.threadpool;

import java.util.ArrayList;
import java.util.List;

public class JobPool {
	
	Object lock = new Object();
	
	List<Job> jobList = new ArrayList<>();
//	Vector<Job> jobList = new Vector<>();
	
	public Job getJob() {
		synchronized(lock) {
			if(jobList.size()==0) {
				try {
					System.out.println("Going for wait...Thread : "+Thread.currentThread().getName());
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				return jobList.remove(0);				
			}
			return null;
		}
	}
	
	
	public void putJob(Job e) {
		
		synchronized (lock) {
			System.out.println("putting a job back");
			jobList.add(e);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			lock.notify();
		}
	}
	
	@Override
	public String toString() {
		return jobList.toString();
	}
}
