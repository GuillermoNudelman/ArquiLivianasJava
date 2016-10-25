package uy.edu.ort.dao;

import java.util.List;
import uy.edu.ort.model.Cliente;
import uy.edu.ort.model.Convenio;

public interface ClienteDao {
    
    void addCliente(Cliente cliente);

    void removeCliente(Cliente cliente);

    List<Cliente> listCliente();
    
    Cliente buscarClientePorNombreEmpresa(String nombreEmpresa);
    
    void editarCliente(Cliente cliente);
    
    //void addConvenio(Cliente cliente, Convenio convenio);
}
