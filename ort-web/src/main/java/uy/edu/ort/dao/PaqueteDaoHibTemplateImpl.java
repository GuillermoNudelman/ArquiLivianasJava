package uy.edu.ort.dao;

import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import uy.edu.ort.model.Paquete;

/**
 *  Implementacion de PaqueteDao, 
 * a una base de datos mysql utilizando hibernate
 */
public class PaqueteDaoHibTemplateImpl implements PaqueteDao{
    
    private HibernateTemplate hibernateTemplate;
     
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void addPaquete(Paquete paquete) {
         this.hibernateTemplate.save(paquete);
    }

    @Override
    public void removePaquete(Paquete paquete) {
          this.hibernateTemplate.delete(paquete);
    }

    @Override
    public List<Paquete> listPaquetes() {
        List<Paquete> paquetes = (List<Paquete>) hibernateTemplate.find("select p from Paquete p");
        return paquetes;
    }
    
    @Override
    public Paquete buscarPaquete(String codigo) {
        Object[] params  = {codigo};        
        List<Paquete> paquetes = (List<Paquete>) hibernateTemplate.find("select p from Paquete p where p.codigo = ?", params);
        return paquetes.isEmpty() ? null : paquetes.get(0);
    }

    @Override
    public void editarPaquete(Paquete paquete) {
        this.hibernateTemplate.update(paquete);
    }
}
