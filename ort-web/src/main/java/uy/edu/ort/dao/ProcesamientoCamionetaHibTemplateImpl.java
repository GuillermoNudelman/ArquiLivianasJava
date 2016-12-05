/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.dao;

import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import uy.edu.ort.model.ProcesamientoCamioneta;

/**
 *
 * @author ptrecca
 */
public class ProcesamientoCamionetaHibTemplateImpl implements ProcesamientoCamionetaDao {

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void addProcesamientoCamioneta(ProcesamientoCamioneta pcamioneta) {
        this.hibernateTemplate.save(pcamioneta);
    }

    @Override
    public List<ProcesamientoCamioneta> listaProcesamientoCamioneta() {
        List<ProcesamientoCamioneta> auditoria = (List<ProcesamientoCamioneta>) hibernateTemplate.find("select pc from ProcesamientoCamioneta pc");
        return auditoria;
    }

}
