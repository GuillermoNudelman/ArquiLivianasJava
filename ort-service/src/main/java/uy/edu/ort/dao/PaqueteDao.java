package uy.edu.ort.dao;

import java.util.List;
import uy.edu.ort.model.Paquete;

public interface PaqueteDao {
    
    void addPaquete(Paquete paquete);

    void removePaquete(Paquete paquete);

    List<Paquete> listPaquetes();
    
}
