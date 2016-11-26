package uy.edu.ort.service;

import java.util.List;
import uy.edu.ort.model.Camioneta;

/**
 * Esta interfaz es utilizada para manejar la entidad camioneta
 * 
 */
public interface CamionetaService {

    public void addCamioneta(Camioneta camioneta);

    public void removeCamioneta(Camioneta camioneta);

    public List<Camioneta> listCamioneta();
    
    public Camioneta buscarCamioneta(String codigo);    
    
    public Camioneta buscarCamionetaPorId(Long id);
    
    void editarCamioneta(Camioneta camioneta);
}
