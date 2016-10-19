package uy.edu.ort;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uy.edu.ort.service.BusinessService;

public class MainBefore {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context-before.xml");
        BusinessService businessService = (BusinessService) applicationContext.getBean("businessService");
        businessService.serviceMethod("carlos");
    }
}
