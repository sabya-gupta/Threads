package threads.framework.CyclicbarrierFramework;

import java.util.concurrent.CyclicBarrier;

public interface CyclicBarrierWorkerInterface<Result, Input, NextTaskInput> extends Runnable{

	public void setCyclicBarrier(CyclicBarrier cb);
	
	public void setInput(Input i);

	public Input getInput();
	
	public Result getResult ();
	
	public void setError (String error);
	
	public String getError();
	
	public void setNexttaskInput(NextTaskInput nti);

}
