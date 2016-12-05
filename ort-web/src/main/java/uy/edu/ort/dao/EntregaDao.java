package uy.edu.ort.dao;

import java.util.List;
import uy.edu.ort.model.Entrega;

/**
 * Interfaz de la entrega para 
 * que sea persistida en una base de datos
 */
public interface EntregaDao {

    void addEntrega(Entrega entrega);

    void removeEntrega(Entrega entrega);

    List<Entrega> listEntregas();
    
    Entrega buscarEntrega(String codigo);
    
    void editarEntrega(Entrega entrega);

    public List<Entrega> listEntregaPorMes(int mes);

    public List<Entrega> listEntregaPorMesYCamioneta(int mes, int codigoCamioneta);

    public List<Entrega> listEntregaPorMesYChofer(int mes, int idChofer);
}
