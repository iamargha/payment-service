package com.egov.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
//@ComponentScan(basePackages = "com")
public class PaymentServiceApplication
{

    public static void main(String[] args)
    {
        //SpringApplication.run(CustomerServiceApplication.class, args);

        SpringApplication application = new SpringApplication(PaymentServiceApplication.class);
        // Set the active profile programmatically
        application.setAdditionalProfiles("dev");
        application.run(args);
    }

}
