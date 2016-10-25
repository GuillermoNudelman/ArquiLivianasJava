package uy.edu.ort.dao;

import java.util.List;

import org.springframework.orm.hibernate4.HibernateTemplate;
import uy.edu.ort.model.Camioneta;

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
    
    //TODO CHEQUEAR QUE ESTE NO SE USA, O QUE SE USA BIEN, BUSCA POR ID NO POR CODIGO
    @Override
    public Camioneta buscarCamionetaPorCodigo(int idCamioneta) {
        Object[] params  = {idCamioneta};        
        List<Camioneta> camionetas = (List<Camioneta>) hibernateTemplate.find("select c from Camioneta c where c.id = ?", params);
        return camionetas.isEmpty() ? null : camionetas.get(0);
    }

    @Override
    public void editarCamioneta(Camioneta camioneta) {
        this.hibernateTemplate.update(camioneta);
    }
}
