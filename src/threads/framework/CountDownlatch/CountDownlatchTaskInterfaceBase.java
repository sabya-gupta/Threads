package threads.framework.CountDownlatch;

import java.util.concurrent.CountDownLatch;

public abstract class CountDownlatchTaskInterfaceBase <Result, Input> implements CountDownLatchTaskInterface<Result, Input> {

	Result result;
	Input input;
	CountDownLatch cdl;
	String error=null;
	
	@Override
	public void setresult(Result r) {
		this.result = r;
	}

	@Override
	public Result getresult() {
		return this.result;
	}

	@Override
	public void setInput(Input i) {
		this.input = i;
	}

	@Override
	public void setlatch(CountDownLatch cdl) {
		this.cdl = cdl;
	}

	@Override
	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String getError() {
		return this.error;
	}

}
