import com.jsimone.controller.*;
import com.jsimone.service.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class MockConfig {

    // Services ...
    @Bean
    public FactorNumberService factorNumberService() {
        return new FactorNumberService();
    }

    @Bean
    public ArmstrongNumberService armstrongNumberService() {
        return new ArmstrongNumberService();
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
    public PalindromicNumberService palindromicNumberService() {
        return new PalindromicNumberService();
    }


    // Controllers ...
    @Bean
    public PalindromicNumberController palindromicNumberController() {
        return new PalindromicNumberController();
    }

    @Bean
    public ArmstrongNumberController parmstrongNumberController() {
        return new ArmstrongNumberController();
    }

    @Bean
    public AmicableNumberController amicableNumberController() {
        return new AmicableNumberController();
    }

    @Bean
    public HelloWorldController helloWorldControlller() {
        return new HelloWorldController();
    }

    @Bean
    public PerfectNumberController perfectNumberController() {
        return new PerfectNumberController();
    }

    @Bean
    public PrimeNumberController primeNumberController() {
        return new PrimeNumberController();
    }

}
