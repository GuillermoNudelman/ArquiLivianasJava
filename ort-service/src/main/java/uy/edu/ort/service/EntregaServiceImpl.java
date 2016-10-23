package uy.edu.ort.service;

import java.util.List;

import javax.transaction.Transactional;
import uy.edu.ort.dao.EntregaDao;
import uy.edu.ort.model.Entrega;

public class EntregaServiceImpl implements EntregaService {

    private EntregaDao entregaDao;

    public void setEntregaDao(EntregaDao entregaDao) {
        this.entregaDao = entregaDao;
    }

    @Override
    @Transactional
    public void addEntrega(Entrega entrega) {
        System.out.println("addEntrega " + entrega.getCodigo());
        this.entregaDao.addEntrega(entrega);
    }

    @Override
    @Transactional
    public void removeEntrega(Entrega entrega) {
        System.out.println("removeEntrega " + entrega.getCodigo());
        this.entregaDao.removeEntrega(entrega);
    }

    @Override
    @Transactional
    public List<Entrega> listEntrega() {
        System.out.println("listEntrega ");
        return this.entregaDao.listEntregas();
    }
    
    @Override
    @Transactional
    public Entrega buscarEntrega(String codigo) {
        return this.entregaDao.buscarEntrega(codigo);
    }
    
    @Override
    @Transactional
    public void editarEntrega(Entrega entrega) {
          this.entregaDao.editarEntrega(entrega);
    }

    @Override
    @Transactional
    public void sumarDistancia(Entrega entrega, int distancia){
    //TODO
    }
}

