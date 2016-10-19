package uy.edu.ort.dao;

import java.util.List;

import org.springframework.orm.hibernate4.HibernateTemplate;
import uy.edu.ort.model.Cliente;

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
}