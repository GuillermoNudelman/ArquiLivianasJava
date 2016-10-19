package uy.edu.ort;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uy.edu.ort.service.BusinessService;

public class MainAround {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context-around.xml");
        BusinessService businessService = (BusinessService) applicationContext.getBean("businessService");
        businessService.serviceMethod("cualquier cosa");
    }
}
