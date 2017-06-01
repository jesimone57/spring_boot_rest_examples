import com.jsimone.app.Application;
import com.jsimone.service.CommonNumberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CommonNumberServiceTest {

	@Autowired
	CommonNumberService commonNumberService;

	@Test
	public void test_leastCommonMultiple() throws Exception {

		int lcm = commonNumberService.computeLeastCommonMultiple(28, 48);
		assertEquals("lcm is incorrect", 336, lcm);

		lcm = commonNumberService.computeLeastCommonMultiple(250, 75);
		assertEquals("lcm is incorrect", 750, lcm);

		lcm = commonNumberService.computeLeastCommonMultiple(88, 40);
		assertEquals("lcm is incorrect", 440, lcm);
	}

	@Test
	public void test_greatestCommonDivisor() throws Exception {

		int gcd = commonNumberService.computeGreatestCommonDivisor(30, 45);
		assertEquals("lcm is incorrect", 15, gcd);

		gcd = commonNumberService.computeGreatestCommonDivisor(100, 80);
		assertEquals("lcm is incorrect", 20, gcd);

		gcd = commonNumberService.computeGreatestCommonDivisor(30030, 1001);
		assertEquals("lcm is incorrect", 1001, gcd);
	}
}
