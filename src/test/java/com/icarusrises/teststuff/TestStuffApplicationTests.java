package com.icarusrises.teststuff;

import com.icarusrises.teststuff.gateway.GatewayServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStuffApplicationTests {

	private final static String DEFAULT_URL = "http://www.hot.net.il/heb/Internet/speed/";

	@Autowired
	private GatewayServiceImp gatewayServiceImp;

	@Test
	public void contextLoads() throws Exception{
		assertEquals(DEFAULT_URL, gatewayServiceImp.getNextSpeedTestWebSite().getUrlAddress());
	}

	@Test
	public void upload() throws Exception{
		assertEquals("dango&esfir", gatewayServiceImp.upload());
	}

}
