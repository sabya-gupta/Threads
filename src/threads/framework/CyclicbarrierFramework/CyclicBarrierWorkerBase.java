package threads.framework.CyclicbarrierFramework;

import java.util.concurrent.CyclicBarrier;

public abstract class CyclicBarrierWorkerBase<Result, Input, NextTaskInput> implements CyclicBarrierWorkerInterface<Result, Input, NextTaskInput> {

	CyclicBarrier cb;
	Input ip;
	Result result;
	String error = null;
	NextTaskInput nti;
	
	@Override
	public void setCyclicBarrier(CyclicBarrier cb) {
		this.cb = cb;
	}

	@Override
	public void setInput(Input i) {
		this.ip = i;
	}

	@Override
	public Result getResult() {
		return result;
	}

	@Override
	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String getError() {
		return error;
	}
	
	@Override
	public void setNexttaskInput(NextTaskInput nti) {
		this.nti = nti;
	}
	
	@Override
	public Input getInput() {
		return ip;
	}


}
