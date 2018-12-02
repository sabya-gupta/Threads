package threads.framework.CyclicbarrierFramework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

public class CyclicBarrierMain {

	public static void main(String[] args) {
		List<CyclicBarrierWorkerInterface<String, String, String>> listOfWorkers = new ArrayList<>();

		CyclicBarrierWorkerRESTStringImpl cb1 = new CyclicBarrierWorkerRESTStringImpl();
		cb1.setInput("REST ");
		listOfWorkers.add(cb1);

		CyclicBarrierWorkerSOAPStringImpl cb2 = new CyclicBarrierWorkerSOAPStringImpl();
		cb2.setInput("SOAP ");
		listOfWorkers.add(cb2);

		CyclicBarrierWorkerDB1StringImpl cb3 = new CyclicBarrierWorkerDB1StringImpl();
		cb3.setInput("DB1 ");
		listOfWorkers.add(cb3);

		CyclicBarrierWorkerDB2StringImpl cb4 = new CyclicBarrierWorkerDB2StringImpl();
		cb4.setInput("DB2 ");
		listOfWorkers.add(cb4);

		CyclicbarrierServiceWithStrings cbsws = new CyclicbarrierServiceWithStrings();
		cbsws.dotask(listOfWorkers);

	}

}

class CyclicBarrierWorkerRESTStringImpl extends CyclicBarrierWorkerBase<String, String, String> {

	@Override
	public void run() {
		try {
			// Make a call to the rest service
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			error = e.toString();
		}
		result = this.ip + "<<COMPLETED>>";

		System.out.println(result + " >> next input = " + this.nti);
		try {
			cb.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("IN REST AFTER AWAIT next input = " + this.nti);
		// Now again call Rest to update result after reading from DB with the nti id
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("FINALLY REST IS DONE!!!");
	}
}

class CyclicBarrierWorkerSOAPStringImpl extends CyclicBarrierWorkerBase<String, String, String> {
	@Override
	public void run() {
		try {
			// Make a call to the rest service
			Thread.sleep(2000);
			result = this.ip + "<<COMPLETED>>";
		} catch (Exception e) {
			e.printStackTrace();
			error = e.toString();
		}
		System.out.println(result + " >> next input = " + this.nti);
		try {
			cb.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		System.out.println("IN SOAP AFTER AWAIT next input = " + this.nti);
		// Now again call Rest to update result after reading from DB with the nti id
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("FINALLY SOAP IS DONE!!!");
	}
}

class CyclicBarrierWorkerDB1StringImpl extends CyclicBarrierWorkerBase<String, String, String> {
	@Override
	public void run() {
		try {
			// Make a call to the rest service
			Thread.sleep(500);
			result = this.ip + "<<COMPLETED>>";
		} catch (Exception e) {
			e.printStackTrace();
			error = e.toString();
		}
		System.out.println(result + " >> next input = " + this.nti);
		try {
			cb.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		System.out.println("IN DB1 AFTER AWAIT next input = " + this.nti);
		// Now again call Rest to update result after reading from DB with the nti id
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("FINALLY DB1 IS DONE!!!");
	}

}

class CyclicBarrierWorkerDB2StringImpl extends CyclicBarrierWorkerBase<String, String, String> {
	@Override
	public void run() {
		try {
			// Make a call to the rest service
			Thread.sleep(100);
			result = this.ip + "<<COMPLETED>>";
		} catch (Exception e) {
			e.printStackTrace();
			error = e.toString();
		}
		System.out.println(result + " >> next input = " + this.nti);
		try {
			cb.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("IN DB2 AFTER AWAIT next input = " + this.nti);
		// Now again call Rest to update result after reading from DB with the nti id
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("FINALLY DB2 IS DONE!!!!!");
	}

}
