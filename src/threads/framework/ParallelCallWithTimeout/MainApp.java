package threads.framework.ParallelCallWithTimeout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainApp {

	public static void main(String[] args) {
		
		List<FrameworkCallableInterface<String, String>> callableObjlist = new ArrayList<>();
		
		int numOfJobs = 10;
		
		for(int i=0; i<numOfJobs; i++) {
			FrameworkCallableInterfaceExampleSimpleImpl<String, String> obj = 
					new FrameworkCallableInterfaceExampleSimpleImpl<String, String>();
			obj.setTime((i+1)*1000);
			obj.setTimeUnit(TimeUnit.MILLISECONDS);
			obj.setInput("task "+i);
			callableObjlist.add(obj);
		}
		
		CallerService cs = new CallerService();
		cs.setNumberOfThreads(numOfJobs);
		cs.doService(callableObjlist);
		
		for(FrameworkCallableInterface<String, String> callableObj : callableObjlist) {
			System.out.println(callableObj.getResult() +" : "+callableObj.getError());
		}
	}

}
