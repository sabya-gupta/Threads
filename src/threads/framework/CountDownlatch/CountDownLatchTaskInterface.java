package threads.framework.CountDownlatch;

import java.util.concurrent.CountDownLatch;

public interface CountDownLatchTaskInterface <Result, Input> extends Runnable{
	
	public void setresult (Result r);

	public Result getresult ();

	public void setInput(Input i);
	
	public void setlatch(CountDownLatch cdl);
	
	public void setError (String error);
	
	public String getError();
}
