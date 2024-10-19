package com.egov.customerlservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
//@ComponentScan(basePackages = "com")
public class CustomerServiceApplication
{

    public static void main(String[] args)
    {
        //SpringApplication.run(CustomerServiceApplication.class, args);

        SpringApplication application = new SpringApplication(CustomerServiceApplication.class);
        // Set the active profile programmatically
        application.setAdditionalProfiles("dev");
        application.run(args);
    }

}
