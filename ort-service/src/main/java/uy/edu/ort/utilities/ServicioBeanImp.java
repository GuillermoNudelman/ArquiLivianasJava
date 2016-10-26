
package uy.edu.ort.utilities;

import uy.edu.ort.utilities.ServicioBean;
import java.util.ArrayList;
import java.util.List;

/**
 *  guarda la lista de beans inicialidados en el sistema
 * 
 */
public class ServicioBeanImp implements ServicioBean{
    
    private List<String> beans = new ArrayList<>();
    
    @Override
    public void addBean(String bean) {
        beans.add(bean);
    }
    
    @Override
    public List<String> getBeans(){
        return beans;
    }
    
}
