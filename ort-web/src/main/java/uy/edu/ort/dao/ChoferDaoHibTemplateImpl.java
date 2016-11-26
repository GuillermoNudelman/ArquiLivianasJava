/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.dao;

import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import uy.edu.ort.model.Chofer;

/**
 *
 * @author ptrecca
 */
public class ChoferDaoHibTemplateImpl implements ChoferDao{
    
     private HibernateTemplate hibernateTemplate;
     
     public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
     
    @Override
    public void addChofer(Chofer chofer) {
        this.hibernateTemplate.save(chofer);
    }

    @Override
    public void removeChofer(Chofer chofer) {
        this.hibernateTemplate.delete(chofer);
    }

    @Override
    public List<Chofer> listChofer() {
        List<Chofer> choferes = (List<Chofer>) hibernateTemplate.find("select c from Chofer c");
        return choferes;
    }

    @Override
    public Chofer buscarChoferPorCodigo(String codigo) {
        Object[] params  = {codigo};        
        List<Chofer> choferes = (List<Chofer>) hibernateTemplate.find("select c from Chofer c where c.codigo = ?", params);
        return choferes.isEmpty() ? null : choferes.get(0);
    }

    @Override
    public void editarChofer(Chofer chofer) {
         this.hibernateTemplate.update(chofer);
    }
    
}
