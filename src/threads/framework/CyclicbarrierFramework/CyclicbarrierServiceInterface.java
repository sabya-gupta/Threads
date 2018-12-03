package threads.framework.CyclicbarrierFramework;

import java.util.List;

public interface CyclicbarrierServiceInterface<Result, Input, NextTaskInput> {
	public void dotask(List<CyclicBarrierWorkerInterface<Result, Input, NextTaskInput>> listOfWorkers);
}
