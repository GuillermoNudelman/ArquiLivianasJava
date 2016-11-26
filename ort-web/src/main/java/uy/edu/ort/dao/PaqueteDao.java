package uy.edu.ort.dao;

import java.util.List;
import uy.edu.ort.model.Paquete;

/**
 *  Interfaz de la entidad paquete para 
 * que sea persistida en una base de datos
 */
public interface PaqueteDao {
    
    void addPaquete(Paquete paquete);

    void removePaquete(Paquete paquete);

    List<Paquete> listPaquetes();
    
    Paquete buscarPaquete(String codigo);
    
    void editarPaquete(Paquete paquete);
}
