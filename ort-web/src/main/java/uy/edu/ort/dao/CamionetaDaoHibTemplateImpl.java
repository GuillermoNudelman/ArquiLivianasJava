package uy.edu.ort.dao;

import java.util.List;

import org.springframework.orm.hibernate4.HibernateTemplate;
import uy.edu.ort.model.Camioneta;

/**
 *  Implementacion de CamionetaDao, 
 * a una base de datos mysql utilizando hibernate
 */
public class CamionetaDaoHibTemplateImpl implements CamionetaDao {

    private HibernateTemplate hibernateTemplate;
    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
        this.hibernateTemplate.setCheckWriteOperations(false);
    }

    @Override
    public void addCamioneta(Camioneta camioneta) {
        this.hibernateTemplate.save(camioneta);
    }

    @Override
    public void removeCamioneta(Camioneta camioneta) {
        this.hibernateTemplate.delete(camioneta);
    }

    @Override
    public List<Camioneta> listCamionetas() {
        List<Camioneta> camionetas = (List<Camioneta>) hibernateTemplate.find("select c from Camioneta c");
        return camionetas;
    }

    @Override
    public Camioneta buscarCamioneta(String codigo) {
        Object[] params  = {codigo};        
        List<Camioneta> camionetas = (List<Camioneta>) hibernateTemplate.find("select c from Camioneta c where c.codigo = ?", params);
        return camionetas.isEmpty() ? null : camionetas.get(0);
    }
    
    @Override
    public Camioneta buscarCamionetaPorId(Long id) {
        int idEnInt = id.intValue();
        Object[] params  = {idEnInt};        
        List<Camioneta> camionetas = (List<Camioneta>) hibernateTemplate.find("select c from Camioneta c where c.id = ?", params);
        return camionetas.isEmpty() ? null : camionetas.get(0);
    }
    
    @Override
    public void editarCamioneta(Camioneta camioneta) {
        //TODO ESTO ESTABA EN UPDATE PERO NO GUARDABA, LLEGABA BIEN EL DATO NUEVO PERO NO ANDABA
        this.hibernateTemplate.saveOrUpdate(camioneta);
    }
}
