package uy.edu.ort.dao;

import java.util.List;

import org.springframework.orm.hibernate4.HibernateTemplate;
import uy.edu.ort.model.Convenio;

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
}

