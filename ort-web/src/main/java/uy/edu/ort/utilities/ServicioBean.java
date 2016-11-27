package uy.edu.ort.utilities;

import java.util.List;
import uy.edu.ort.model.Beans;

/**
 *
 * Guarda la lista de beans inicializados en el sistems
 */
public interface ServicioBean {
    
    void addBean(Beans bean);
    
    List<Beans> getBeans();    
    
}
