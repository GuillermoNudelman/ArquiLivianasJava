package uy.edu.ort.service;

import java.util.List;
import uy.edu.ort.model.Entrega;

public interface EntregaService {
    
    public void addEntrega(Entrega entrega);

    public void removeEntrega(Entrega entrega);

    public List<Entrega> listEntrega();
}
