package uy.edu.ort.dao;

import java.util.List;
import uy.edu.ort.model.Cliente;
import uy.edu.ort.model.Convenio;

/**
 * Interfaz del cliente para 
 * que sea persistida en una base de datos
 */
public interface ClienteDao {
    
    void addCliente(Cliente cliente);

    void removeCliente(Cliente cliente);

    List<Cliente> listCliente();
    
    Cliente buscarClientePorNombreEmpresa(String nombreEmpresa);
    
    Cliente buscarClientePorId(Long id);
    
    void editarCliente(Cliente cliente);
    
    //void addConvenio(Cliente cliente, Convenio convenio);
}
