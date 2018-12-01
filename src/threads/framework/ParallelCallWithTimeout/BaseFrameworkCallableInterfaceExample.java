package threads.framework.ParallelCallWithTimeout;

import java.util.concurrent.TimeUnit;

public abstract class BaseFrameworkCallableInterfaceExample<V, I> implements FrameworkCallableInterface<V, I> {

	int time;
	TimeUnit tu;
	String err = "No Error";
	V result = null;
	I input;
	
	@Override
	public TimeUnit getTimeUnit() {
		return tu;
	}

	@Override
	public int getTime() {
		return time;
	}


	@Override
	public void setError(String error) {
		err = error;
	}

	@Override
	public V getResult() {
		return result;
	}

	@Override
	public void setTimeUnit(TimeUnit tu) {
		this.tu = tu;
	}
	
	@Override
	public void setTime(int time) {
		this.time = time;
	}
	
	@Override
	public String getError() {
		return this.err;
	}
	
	@Override
	public I getInput() {
		return this.input;
	}

	public void setResult(V v) {
		this.result = v;
	}

	
}
