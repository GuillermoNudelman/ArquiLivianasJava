package uy.edu.ort.dao;

import java.util.List;

import org.springframework.orm.hibernate4.HibernateTemplate;
import uy.edu.ort.model.Convenio;

/**
 * Implementacion de ConvenioDao, 
 * a una base de datos mysql utilizando hibernate
 */
public class ConvenioDaoHibTemplateImpl implements ConvenioDao {

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void addConvenio(Convenio convenio) {
        this.hibernateTemplate.save(convenio);
    }

    @Override
    public void removeConvenio(Convenio convenio) {
        this.hibernateTemplate.delete(convenio);
    }

    @Override
    public List<Convenio> listConvenios() {
        List<Convenio> convenios = (List<Convenio>) hibernateTemplate.find("select c from Convenio c");
        return convenios;
    }
    
    @Override
    public Convenio buscarConvenio(String codigo) {
        Object[] params  = {codigo};        
        List<Convenio> convenios = (List<Convenio>) hibernateTemplate.find("select c from Convenio c where c.codigo = ?", params);
        return convenios.isEmpty() ? null : convenios.get(0);
    }

    @Override
    public void editarConvenio(Convenio convenio) {
        this.hibernateTemplate.update(convenio);
    }
}

