import com.jsimone.service.PalindromicNumberService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jsimone on 6/2/17.
 */
public class PalindromicNumberServiceTest {

	@Test
	public void palindromicNumber() throws Exception {
		PalindromicNumberService palindromicNumberService = new PalindromicNumberService();
		List<Integer> numbers = palindromicNumberService.computePalindromicNumbersInRange(10, 20);
		assertEquals(1, numbers.size());
		assertEquals(Integer.valueOf(11), numbers.get(0));
	}

}
