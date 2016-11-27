
package uy.edu.ort.utilities;

import uy.edu.ort.utilities.ServicioBean;
import java.util.ArrayList;
import java.util.List;
import uy.edu.ort.dao.BeansDao;
import uy.edu.ort.model.Beans;

/**
 *  Guarda la lista de beans inicialidados en el sistema
 * 
 */
public class ServicioBeanImp implements ServicioBean{
    
   private BeansDao beansDao;

    public void setBeansDao(BeansDao beansDao) {
        this.beansDao = beansDao;
    }
    
    @Override
    public void addBean(Beans bean) {
        this.beansDao.addBean(bean);
    }
    
    @Override
    public List<Beans> getBeans(){
        return this.beansDao.listBeans();
    }
    
}
