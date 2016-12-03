package uy.edu.ort.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import uy.edu.ort.model.Entrega;

/**
 * Implementacion de EntregaDao, a una base de datos mysql utilizando hibernate
 *
 */
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
        Object[] params = {codigo};
        List<Entrega> entregas = (List<Entrega>) hibernateTemplate.find("select e from Entrega e where e.codigo = ?", params);
        return entregas.isEmpty() ? null : entregas.get(0);
    }

    @Override
    public void editarEntrega(Entrega entrega) {
        this.hibernateTemplate.update(entrega);
    }

    @Override
    public List<Entrega> listEntregaPorMes(int mes) {
        Object[] params = {mes};
        List<Entrega> entregas = (List<Entrega>) hibernateTemplate.find("select e from Entrega e where month(e.fechaEntrega) = ?", params);
        return entregas;
    }

    @Override
    public List<Entrega> listEntregaPorMesYCamioneta(int mes, String codigoCamioneta) {
        Object[] params = {mes, codigoCamioneta};
        List<Entrega> entregas = (List<Entrega>) hibernateTemplate.find("SELECT e FROM Entrega e where month(e.fechaEntrega) = ? and e.camioneta in (select c from Camioneta c	where c.codigo = ?)", params);
        return entregas;
    }

    @Override
    public List<Entrega> listEntregaPorMesCamionetaYChofer(int mes, String chofer) {
        Object[] params = {mes, chofer};
        List<Entrega> entregas = (List<Entrega>) hibernateTemplate.find("SELECT e FROM Entrega e where month(e.fechaEntrega) = ? and e.chofer in (select c from Chofer c where c.codigo = ?)", params);
        return entregas;
    }
}
