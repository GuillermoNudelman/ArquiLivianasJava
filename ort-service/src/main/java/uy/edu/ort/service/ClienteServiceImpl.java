package uy.edu.ort.service;

import java.util.List;
import uy.edu.ort.dao.ClienteDao;
import uy.edu.ort.model.Cliente;

import javax.transaction.Transactional;

public class ClienteServiceImpl implements ClienteService{

    private ClienteDao clienteDao;

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }
    @Override
    @Transactional
    public void addCliente(Cliente cliente) {
        System.out.println("addCliente " + cliente.getNombreContacto());
        this.clienteDao.addCliente(cliente);
    }

    @Override
    @Transactional
    public void removeCliente(Cliente cliente) {
        System.out.println("removeCliente " + cliente.getNombreContacto());
        this.clienteDao.removeCliente(cliente);
    }

    @Override
    @Transactional
    public List<Cliente> listCliente() {
        System.out.println("listClientes ");
        return this.clienteDao.listCliente();
    }
    
    @Override
    @Transactional
    public Cliente buscarCliente(String codigo) {
        return this.clienteDao.buscarCliente(codigo);
    }
    
    
    @Override
    public void editarCliente(Cliente cliente) {
          this.clienteDao.editarCliente(cliente);
    }
}
