import com.jsimone.controller.HelloWorldController;
import com.jsimone.controller.PalindromicNumberController;
import com.jsimone.controller.PrimeNumberController;
import com.jsimone.service.CommonNumberService;
import com.jsimone.service.PalindromicNumberService;
import com.jsimone.service.PrimeNumberService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class MockConfig {

    @Bean
    public HelloWorldController helloWorldControlller() {
        return new HelloWorldController();
    }

    @Bean
    public PrimeNumberService primeNumberService() {
        return new PrimeNumberService();
    }

    @Bean
    public CommonNumberService commonNumberService() {
        return new CommonNumberService();
    }

    @Bean
    public PrimeNumberController primeNumberController() {
        return new PrimeNumberController();
    }

    @Bean
    public PalindromicNumberService palindromicNumberService() {
        return new PalindromicNumberService();
    }

    @Bean
    public PalindromicNumberController palindromicNumberController() {
        return new PalindromicNumberController();
    }

    /*
    @Bean
    public AmicableNumberController amicableNumberController() {
        return new AmicableNumberController();
    }
    */

}
