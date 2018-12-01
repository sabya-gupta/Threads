package threads.old.threadpool;

public class JobImpl implements Job {

	public String thejob;
	@Override
	public void doJob() {
		System.out.println("Job done is " + thejob);
	}
	public JobImpl(String thejob) {
		super();
		this.thejob = thejob;
		System.out.println("Doing job...."+thejob);
	}

}
