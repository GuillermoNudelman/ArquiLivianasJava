package uy.edu.ort.service;

import java.util.List;
import javax.transaction.Transactional;
import uy.edu.ort.dao.PaqueteDao;
import uy.edu.ort.model.Paquete;

/**
 * Implementacion de la interfaz PaqueteService
 * 
 */
public class PaqueteServiceImpl implements PaqueteService {

    private PaqueteDao paqueteDao;

    public void setPaqueteDao(PaqueteDao paqueteDao) {
        this.paqueteDao = paqueteDao;
    }
    
    @Override
    @Transactional
    public void addPaquete(Paquete paquete) {
        System.out.println("addPaquete " + paquete.getCodigo());
        this.paqueteDao.addPaquete(paquete);
    }

    @Override
    @Transactional
    public void removePaquete(Paquete paquete) {
        System.out.println("removePaquete " + paquete.getCodigo());
        this.paqueteDao.removePaquete(paquete);
    }

    @Override
    @Transactional
    public List<Paquete> listPaquetes() {
        System.out.println("listPaquetes ");
        return this.paqueteDao.listPaquetes();
    }
    
    @Override
    @Transactional
    public Paquete buscarPaquete(String codigo) {
        return this.paqueteDao.buscarPaquete(codigo);
    }
    
    
    @Override
    @Transactional
    public void editarPaquete(Paquete paquete) {
          this.paqueteDao.editarPaquete(paquete);
    }
    
}
