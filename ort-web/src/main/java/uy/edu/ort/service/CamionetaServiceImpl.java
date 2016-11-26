package uy.edu.ort.service;

import java.util.List;
import uy.edu.ort.dao.CamionetaDao;
import uy.edu.ort.model.Camioneta;

import javax.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * Implementacion de la interfaz CamionetaService
 *
 */
public class CamionetaServiceImpl implements CamionetaService {

    private CamionetaDao camionetaDao;

    public void setCamionetaDao(CamionetaDao camionetaDao) {
        this.camionetaDao = camionetaDao;
    }

    @Override
    @Transactional
    @CacheEvict(value = "camioneta", allEntries = true)
    public void addCamioneta(Camioneta camioneta) {
        this.camionetaDao.addCamioneta(camioneta);
    }

    @Override
    @Transactional
    @CacheEvict(value = "camioneta", allEntries = true)
    public void removeCamioneta(Camioneta camioneta) {
        this.camionetaDao.removeCamioneta(camioneta);
    }

    @Override
    @Transactional
    @Cacheable("camioneta")
    public List<Camioneta> listCamioneta() {
        return this.camionetaDao.listCamionetas();
    }

    @Override
    @Transactional
    public Camioneta buscarCamioneta(String codigo) {
        return this.camionetaDao.buscarCamioneta(codigo);
    }

    @Override
    @Transactional
    public Camioneta buscarCamionetaPorId(Long id) {
        return this.camionetaDao.buscarCamionetaPorId(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "camioneta", allEntries = true)
    public void editarCamioneta(Camioneta camioneta) {
        this.camionetaDao.editarCamioneta(camioneta);
    }
}
