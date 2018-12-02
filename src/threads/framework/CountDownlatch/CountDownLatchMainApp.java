package threads.framework.CountDownlatch;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sabyasachi.Gu
 * 
 * Once we required to integrate and aggregate data data from various systems and DB.
 * We had an SOA architecture with Rest and SOAP. 
 * We were using a SSO and two way SSL for outside and inside servers.
 * Also we had DBs for different systems from which we required to collect the data.
 * The requirement was to collate the data... So we thought why not we collect the data in parallel 
 * and then when every thread completes its tack, we do the aggregation.
 * 
 * Below implementation shows hypothetical SOA and DB processors with CountDownLatch. 
 * There is no logic in the processors but the framework that we developed can be easily accomplished to 
 * do any number of parallel tasks which needs to be completed before proceeding with next task.
 * 
 * And of course You need to have the same kind of Input and result object
 *
 */



public class CountDownLatchMainApp {
	
	public static void main(String[] args) {
		
		List<CountDownLatchTaskInterface<String, String>> listOftask =  new ArrayList<>();

		//First we define the tasks
		MyRESTCountDownlatchInterfaceImpl<String, String> restTask = new MyRESTCountDownlatchInterfaceImpl<>();
		restTask.setInput("REST job");
		listOftask.add(restTask);
		
		//Then the SOAP job
		MySOAPCountDownlatchInterfaceImpl<String, String> soapTask = new MySOAPCountDownlatchInterfaceImpl<>();
		soapTask.setInput("SOAP job");
		listOftask.add(soapTask);
		
		//The two DB jobs
		MyDBCountDownlatchInterfaceImpl<String, String> db1Task = new MyDBCountDownlatchInterfaceImpl<>();
		db1Task.setInput("DB1 job");
		listOftask.add(db1Task);
		
		MyDBCountDownlatchInterfaceImpl<String, String> db2Task = new MyDBCountDownlatchInterfaceImpl<>();
		db2Task.setInput("DB2 job");
		listOftask.add(db2Task);
		
		
		CountDownLatchService<String, String, String> cdls = new CountDownLatchService<>();
		String theFinalresult = cdls.dotask(listOftask);
		
		System.out.println("And in Main....");
	}
}


/**
 * 
 * @author Sabyasachi.Gu
 * This is a hypothetical representation of a rest task
 * @param <Result>
 * @param <Input>
 */
class MyRESTCountDownlatchInterfaceImpl<Result, Input> extends CountDownlatchTaskInterfaceBase<Result, Input>{
	@Override
	public void run() {
		System.out.println("calling the rest service");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			this.setError(e.toString());
		}
		System.out.println("Completed the "+this.input);
		this.result = (Result)(" Completed the "+this.input+ " >>> ");
		this.cdl.countDown();
	}
	
}

/**
 * 
 * @author Sabyasachi.Gu
 * This is a hypothetical representation of a soap task
 * @param <Result>
 * @param <Input>
 */

class MySOAPCountDownlatchInterfaceImpl<Result, Input> extends CountDownlatchTaskInterfaceBase<Result, Input>{
	@Override
	public void run() {
		System.out.println("calling the soap service");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			this.setError(e.toString());
		}
		System.out.println("Completed the "+this.input);
		this.result = (Result)(" Completed the "+this.input+ " >>> ");
		this.cdl.countDown();
	}
	
}

/**
 * 
 * @author Sabyasachi.Gu
 * This is a hypothetical representation of a DB task
 * @param <Result>
 * @param <Input>
 */

class MyDBCountDownlatchInterfaceImpl<Result, Input> extends CountDownlatchTaskInterfaceBase<Result, Input>{
	@Override
	public void run() {
		System.out.println("calling the db service");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
			this.setError(e.toString());
		}
		System.out.println("Completed the "+this.input);
		this.result = (Result)(" Completed the "+this.input+ " >>> ");
		this.cdl.countDown();
	}
	
}
