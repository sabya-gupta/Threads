package threads.framework.ParallelCallWithTimeout;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public interface FrameworkCallableInterface<V, I> extends Callable<V>{

	public TimeUnit getTimeUnit();
	
	public void setTimeUnit(TimeUnit tu);
	
	public int getTime();

	public void setTime(int time);

	public V getResult();

	public void setResult(V v);
	
	public void setError (String error);

	public String getError ();
	
	public void setInput(I i);

	public I getInput();

}
