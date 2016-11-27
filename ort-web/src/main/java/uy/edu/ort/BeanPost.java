package uy.edu.ort;

import java.util.Date;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.BeansException;
import uy.edu.ort.model.Beans;
import uy.edu.ort.utilities.ClusterService;
import uy.edu.ort.utilities.ServicioBean;

/**
 * Esta clase implementa la interfaz BeanPostProcessor, en el metodo after bean
 * initialization se guarda en un servicio el nombre del Bean y se imprime el
 * mismo
 *
 */
public class BeanPost implements BeanPostProcessor {

    private ServicioBean servicioBean;

    public void setServicioBean(ServicioBean servicioBean) {
        this.servicioBean = servicioBean;
    }
    
    private ClusterService clusterService;

    public void setClusterService(ClusterService clusterService) {
        this.clusterService = clusterService;
    }
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;  // you can return any other object as well
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Date date = new Date();
        Beans beanObj = new Beans();
        beanObj.setClase(bean.getClass().getName());
        beanObj.setNombre(beanName);
        beanObj.setFechaOperacion(date);
        beanObj.setNombreCluster(this.clusterService.getNombre());
        servicioBean.addBean(beanObj);
        System.out.println("Bean inicializado : " + beanName + " Clase:" + bean.getClass().getName());
        return bean;  // you can return any other object as well
    }

}
