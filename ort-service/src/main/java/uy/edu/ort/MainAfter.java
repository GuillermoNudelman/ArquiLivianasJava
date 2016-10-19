package uy.edu.ort;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uy.edu.ort.service.BusinessService;

public class MainAfter {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context-after.xml");
        BusinessService businessService = (BusinessService) applicationContext.getBean("businessService");
        String str = businessService.serviceMethod(123);
        System.out.println(str);
    }
}
