package uy.edu.ort.dao;

import java.util.List;
import uy.edu.ort.model.Camioneta;

/**
 * Interfaz de la entidad camioneta para 
 * que sea persistida en una base de datos
 */
public interface CamionetaDao {

    void addCamioneta(Camioneta camioneta);

    void removeCamioneta(Camioneta camioneta);
    
    Camioneta buscarCamioneta(String codigo);
    
    Camioneta buscarCamionetaPorId(Long id);

    List<Camioneta> listCamionetas();
    
    void editarCamioneta(Camioneta camioneta);
}
