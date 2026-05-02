package hexaware.qa.opencart.liseners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import io.qameta.allure.model.TestResult;

public class Retry implements IRetryAnalyzer {

	private int count=0;
	private static int maxTry=3;
	
	@Override
	public boolean retry(ITestResult iTestResult) {
	if(!iTestResult.isSuccess()) {  // check if test not succeed
		if(count<maxTry) {// check if max count is reached
			count++; //increase the max count by 1
			iTestResult.setStatus(ITestResult.FAILURE); // max test as failed
			return true; // tell testsng to re-run the test
			}
		else {
			iTestResult.setStatus(ITestResult.FAILURE); // if maxcount reached,test marked as failed
		}}
		
		else {
			iTestResult.setStatus(ITestResult.SUCCESS); // if maxcount reached , test marked as passed
		}
		
		return false;
	}

}
