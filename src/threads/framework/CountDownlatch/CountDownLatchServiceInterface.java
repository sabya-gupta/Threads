package threads.framework.CountDownlatch;

import java.util.List;

public interface CountDownLatchServiceInterface <ResultOfThisclass, Result, Input>{
	public ResultOfThisclass dotask(List<CountDownLatchTaskInterface<Result, Input>> taskList);

}
