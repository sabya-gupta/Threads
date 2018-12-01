package threads.framework.ParallelCallWithTimeout;

import java.util.Random;

public class FrameworkCallableInterfaceExampleSimpleImpl<R, I>
				extends BaseFrameworkCallableInterfaceExample<R, I> {
	
	@Override
	public R call() throws Exception {
		int timetoSleep = new Random().nextInt(10000);
		System.out.println("Going to sleep for "+timetoSleep+" against "+this.time);
		Thread.sleep(timetoSleep);
		return (R)input;
	}
	

	@Override
	public void setInput(I i) {
		this.input = i;
	}
	
}
