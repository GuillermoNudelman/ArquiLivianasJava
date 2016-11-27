package uy.edu.ort.service;

import java.util.List;
import uy.edu.ort.model.Paquete;

/**
 * Esta interfaz es utilizada para manejar la entidad paquete
 * 
 */
public interface PaqueteService {
    
    public void addPaquete(Paquete user);

    public void removePaquete(Paquete user);

    public List<Paquete> listPaquetes();
    
    public Paquete buscarPaquete(String codigo);
    
    public Paquete buscarPaquetePorId(Long idPaquete);
    
    public void editarPaquete(Paquete paquete);
}
