package company.sameer.dataProvider;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import company.sameer.testBases.TestBaby;


public class DataDriven{
	
    @DataProvider(name="SearchProvider")
    public static Object[][] getDataFromDataprovider(){
        return new Object[][] {
            { "GuruSearch", "India" },
            { "KrishnaSearch", "UK" },
            { "BhupeshSearch", "USA" }
        };
   }

    
    @DataProvider(name="testData")
	public static Object[][] dataSource(){
    	
    	TestBaby tb=new TestBaby();
		return tb.getData("TestData.xlsx", "LoginTestData");
	}
	
    
    @DataProvider(name="methodDataProvier")
    public static Object[][] getDataFromDataprovider(Method m){
    	
    	if(m.getName().equalsIgnoreCase("testMethod1")){
    return new Object[][] 
    	{
            { "Guru99", "India" },
            { "Krishna", "UK" },
            { "Bhupesh", "USA" }
        };

    }
    	else
    	   
    		{
    		return new Object[][] 
    	        	{
    	                { "AZHAR", "sACHIN" },
    	                { "KHALI", "SRK" },
    	                { "FAIZ", "HASHMI" }
    	            };

    	        }
    		
    		
    }

    
}