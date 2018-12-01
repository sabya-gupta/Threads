package threads.old.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JobPoolWithLock {

	List<Job> jobList = new ArrayList<>();
	Lock lock = new ReentrantLock(true);
	Condition condition = lock.newCondition();

	public Job getJob() {
		try {
			lock.lock();
			if(jobList.size()==0) {
				condition.await();
			}else {
				jobList.remove(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {	
			lock.unlock();
		}
		return null;
	}
	
	public void putJob(Job job) {
		try {
			lock.lock();
			jobList.add(job);
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	
}
