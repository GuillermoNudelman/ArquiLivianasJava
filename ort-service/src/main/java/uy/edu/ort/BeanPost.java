package uy.edu.ort;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.BeansException;
import uy.edu.ort.utilities.ServicioBean;

public class BeanPost implements BeanPostProcessor {
    
   private ServicioBean servicioBean;
   
   public void setServicioBean(ServicioBean servicioBean) {
        this.servicioBean = servicioBean;
    }
 
   @Override
   public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {      
      return bean;  // you can return any other object as well
   }

   @Override
   public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
      servicioBean.addBean("Nombre: "+beanName+" Clase: "+ bean.getClass().getName());
      System.out.println("AfterInitialization : " + beanName+" Clase:"+bean.getClass().getName() );
      return bean;  // you can return any other object as well
   }

}