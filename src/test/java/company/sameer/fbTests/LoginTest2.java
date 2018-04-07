package company.sameer.fbTests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import company.sameer.allHelper.LoggerHelper;
import company.sameer.pageObject.FaceLogin;
import company.sameer.testBases.Config;
import company.sameer.testBases.TestBaby;




/**
 * @author vaio
 *
 */
public class LoginTest2 extends TestBaby{


	private final Logger log = LoggerHelper.getLogger(LoginTest2.class);
	
	@Test
	public void testLoginToApplication() throws InterruptedException{
		log.info(LoginTest2.class.getName()+" started");
		Config config = new Config(OR);
		driver.get(config.getWebsite());
		
		FaceLogin loginPage = new FaceLogin(driver);
		
		loginPage.loginToApplication("azhar", "alam");
		
	}
	
	

	/*@Test
	public void a(){
		
		
	}
	
	@Test
	public void b() throws InterruptedException{
		
		Assert.assertTrue(false);
	}
	
	@Test
	public void c() throws InterruptedException{
		
		
	}
*/
}
