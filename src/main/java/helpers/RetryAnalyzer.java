package helpers;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int retryCount = 0;
    private static  int maxValue =
            Integer.parseInt(PropertiesReaderXML.getProperty("maxValue", "XML_FILE_PATH"));


    @Override
    public boolean retry(ITestResult iTestResult) {
       if(retryCount<maxValue) {
           retryCount++;
           return true;
       } 
        return false;
    }
}
