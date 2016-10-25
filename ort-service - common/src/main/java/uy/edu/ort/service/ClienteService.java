package uy.edu.ort.service;

import java.util.List;
import uy.edu.ort.model.Cliente;
import uy.edu.ort.model.Convenio;

public interface ClienteService {
    
    public void addCliente(Cliente cliente);

    public void removeCliente(Cliente cliente);

    public List<Cliente> listCliente();
    
    void editarCliente(Cliente cliente);
    
    //void addConvenio(Cliente cliente, Convenio convenio);
    
    public Cliente buscarClientePorNombreEmpresa(String codigo);
    
}
