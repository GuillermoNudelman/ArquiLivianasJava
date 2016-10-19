package uy.edu.ort.dao;

import java.util.List;
import uy.edu.ort.model.Cliente;

public interface ClienteDao {
    
    void addCliente(Cliente cliente);

    void removeCliente(Cliente cliente);

    List<Cliente> listCliente();
}
