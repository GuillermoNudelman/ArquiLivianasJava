/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import uy.edu.ort.dao.ChoferDao;
import uy.edu.ort.model.Chofer;

/**
 *
 * @author ptrecca
 */
public class ChoferServiceImpl implements ChoferService {

    private ChoferDao choferDao;

    public void setChoferDao(ChoferDao choferDao) {
        this.choferDao = choferDao;
    }

    @Override
    @Transactional
    @CacheEvict(value = "chofer", allEntries = true)
    public void addChofer(Chofer chofer) {
        this.choferDao.addChofer(chofer);
    }

    @Override
    @Transactional
    @CacheEvict(value = "chofer", allEntries = true)
    public void removeChofer(Chofer chofer) {
        this.choferDao.removeChofer(chofer);
    }

    @Override
    @Cacheable("chofer")
    public List<Chofer> listChofer() {
        return this.choferDao.listChofer();
    }

    @Override
    public Chofer buscarChoferPorCodigo(String codigo) {
        return this.choferDao.buscarChoferPorCodigo(codigo);
    }

    @Override
    public Chofer buscarChoferPorId(Long id) {
        return this.choferDao.buscarChoferPorId(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "chofer", allEntries = true)
    public void editarChofer(Chofer chofer) {
        this.choferDao.editarChofer(chofer);
    }

}
