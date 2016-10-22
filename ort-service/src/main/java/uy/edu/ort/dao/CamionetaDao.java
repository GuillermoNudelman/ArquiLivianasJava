package uy.edu.ort.dao;

import java.util.List;
import uy.edu.ort.model.Camioneta;

public interface CamionetaDao {

    void addCamioneta(Camioneta camioneta);

    void removeCamioneta(Camioneta camioneta);
    
    Camioneta buscarCamioneta(String codigo);

    Camioneta buscarCamionetaPorCodigo(int idCamioneta);
    
    List<Camioneta> listCamionetas();
    
    void editarCamioneta(Camioneta camioneta);
}
