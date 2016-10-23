package uy.edu.ort.service;

import java.util.List;
import uy.edu.ort.model.Paquete;

public interface PaqueteService {
    
    public void addPaquete(Paquete user);

    public void removePaquete(Paquete user);

    public List<Paquete> listPaquetes();
    
    public Paquete buscarPaquete(String codigo);

    public void editarPaquete(Paquete paquete);
}
