package uy.edu.ort.service;

import java.util.List;
import uy.edu.ort.model.Camioneta;

public interface CamionetaService {

    public void addCamioneta(Camioneta camioneta);

    public void removeCamioneta(Camioneta camioneta);

    public List<Camioneta> listCamioneta();
    
    public Camioneta buscarCamioneta(String codigo);
    
    void editarCamioneta(Camioneta camioneta);
}
