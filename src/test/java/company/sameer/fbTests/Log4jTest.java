package company.sameer.fbTests;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import company.sameer.allHelper.LoggerHelper;
import company.sameer.testBases.TestBaby;

public class Log4jTest {
	
private final Logger log = LoggerHelper.getLogger(Log4jTest.class);
	

@Test
public void a(){


log.debug("debufg");
log.info("info");
log.warn("warn");
log.error("error");
log.fatal("fatal");
}
}



