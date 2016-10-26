package uy.edu.ort.utilities;

import java.util.List;

/**
 *
 * Guarda la lista de beans inicializados en el sistems
 */
public interface ServicioBean {
    
    void addBean(String bean);
    
    List<String> getBeans();    
    
}
