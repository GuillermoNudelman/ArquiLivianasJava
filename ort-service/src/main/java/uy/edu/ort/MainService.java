package uy.edu.ort;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uy.edu.ort.model.*;
import uy.edu.ort.service.*;

/**
 * Clase ejecutable del sistema una vez ejecutada queda esperando por los request a los servicios expuestos
 * declarados en el application-context.xml
 * 
 */
public class MainService {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");       
    }
}
