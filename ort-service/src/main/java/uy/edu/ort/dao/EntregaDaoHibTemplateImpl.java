package uy.edu.ort.dao;

import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import uy.edu.ort.model.Entrega;

public class EntregaDaoHibTemplateImpl implements EntregaDao {

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void addEntrega(Entrega entrega) {
        this.hibernateTemplate.save(entrega);
    }

    @Override
    public void removeEntrega(Entrega entrega) {
        this.hibernateTemplate.delete(entrega);
    }

    @Override
    public List<Entrega> listEntregas() {
        List<Entrega> entregas = (List<Entrega>) hibernateTemplate.find("select e from Entrega e");
        return entregas;
    }
    
    @Override
    public Entrega buscarEntrega(String codigo) {
        Object[] params  = {codigo};        
        List<Entrega> entregas = (List<Entrega>) hibernateTemplate.find("select e from Entrega e where e.codigo = ?", params);
        return entregas.isEmpty() ? null : entregas.get(0);
    }

    @Override
    public void editarEntrega(Entrega entrega) {
        //TODO
        this.hibernateTemplate.update(entrega);
    }
    
    
    @Override
    public void sumarDistancia(Entrega entrega, int distancia){
    //TODO
    }
}

