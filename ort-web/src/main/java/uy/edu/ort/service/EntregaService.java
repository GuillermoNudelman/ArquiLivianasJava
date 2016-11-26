package uy.edu.ort.service;

import java.util.List;
import uy.edu.ort.model.Entrega;

/**
 * Esta interfaz es utilizada para manejar la entidad Entrega
 * 
 */
public interface EntregaService {
    
    public void addEntrega(Entrega entrega);

    public void removeEntrega(Entrega entrega);

    public List<Entrega> listEntrega();
    
    public Entrega buscarEntrega(String codigo);
    
    public void editarEntrega(Entrega entrega);

    public List<Entrega> listEntregaPorMes(int mes);

    public List<Entrega> listEntregaPorMesYCamioneta(int mes, String codigoCamioneta);
}
