package uy.edu.ort.service;

import java.util.List;

import javax.transaction.Transactional;
import uy.edu.ort.dao.EntregaDao;
import uy.edu.ort.model.Entrega;

/**
 * Implementacion de la interfaz EntregaService
 * 
 */
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
    public List<Entrega> listEntregaPorMes(int mes) {
        return this.entregaDao.listEntregaPorMes(mes);
    }

    @Override
    public List<Entrega> listEntregaPorMesYCamioneta(int mes, String codigoCamioneta) {
        return this.entregaDao.listEntregaPorMesYCamioneta(mes, codigoCamioneta);
    }

    @Override
    public List<Entrega> listEntregaPorMesCamionetaYChofer(int mes, String camioneta, String chofer) {
       return this.entregaDao.listEntregaPorMesCamionetaYChofer(mes, camioneta, chofer);
    }
}

