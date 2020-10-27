package nl.rnsd.organisation;

import org.axonframework.springboot.autoconfig.MetricsAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {MetricsAutoConfiguration.class})
public class AxonAggregateDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxonAggregateDemoApplication.class, args);
    }

}