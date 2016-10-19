package uy.edu.ort.dao;

import java.util.List;
import uy.edu.ort.model.Entrega;

public interface EntregaDao {

    void addEntrega(Entrega entrega);

    void removeEntrega(Entrega entrega);

    List<Entrega> listEntregas();
}
