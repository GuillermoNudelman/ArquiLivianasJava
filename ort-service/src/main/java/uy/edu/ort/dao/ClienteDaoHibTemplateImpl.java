package uy.edu.ort.dao;

import java.util.List;

import org.springframework.orm.hibernate4.HibernateTemplate;
import uy.edu.ort.model.Cliente;
import uy.edu.ort.model.Convenio;

public class ClienteDaoHibTemplateImpl implements ClienteDao{
     private HibernateTemplate hibernateTemplate;
     
     public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void addCliente(Cliente cliente) {
        this.hibernateTemplate.save(cliente);
    }

    @Override
    public void removeCliente(Cliente cliente) {
        this.hibernateTemplate.delete(cliente);
    }

    @Override
    public List<Cliente> listCliente() {
        List<Cliente> clientes = (List<Cliente>) hibernateTemplate.find("select c from Cliente c");
        return clientes;
    }
    

    @Override
    public void editarCliente(Cliente cliente) {
        /*for (Convenio convenio: cliente.getListaConvenios()) {
            Object[] params  = {cliente.getNombreEmpresa(),convenio};
            hibernateTemplate.sa
        }   */
        
        
        //this.hibernateTemplate.update(cliente);
        removeCliente(cliente);
        addCliente(cliente);
    }
    
    @Override
    public Cliente buscarClientePorNombreEmpresa(String codigo) {
        Object[] params  = {codigo};        
        List<Cliente> clientes = (List<Cliente>) hibernateTemplate.find("select c from Cliente c where c.nombreEmpresa = ?", params);
        return clientes.isEmpty() ? null : clientes.get(0);
    }

    /*@Override
    public void addConvenio(Cliente cliente, Convenio convenio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}