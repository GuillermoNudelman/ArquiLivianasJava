package uy.edu.ort.service;

import java.util.List;
import uy.edu.ort.model.Cliente;

public interface ClienteService {
    
    public void addCliente(Cliente cliente);

    public void removeCliente(Cliente cliente);

    public List<Cliente> listCliente();
    
}
