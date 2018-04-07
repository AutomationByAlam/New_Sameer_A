package company.sameer.dataProviderTest;

import org.testng.annotations.Test;

import company.sameer.dataProvider.DataDriven;


public class DataProviderTest {
	
	@Test(dataProvider="SearchProvider",dataProviderClass=DataDriven.class)
	public void m1(String uname,String pass)
	{}

	@Test(dataProvider="testData",dataProviderClass=DataDriven.class)
	public void m1(String uname,String pass,String rn)
	{}


	
	//methodDataProvier

	@Test(dataProvider="methodDataProvier",dataProviderClass=DataDriven.class)
	public void testMethod1(String uname,String pass)
	{
		
		
	}
	
	@Test(dataProvider="methodDataProvier",dataProviderClass=DataDriven.class)
	public void testMethod2(String uname,String pass)
	{
		
		
	}
	

}
