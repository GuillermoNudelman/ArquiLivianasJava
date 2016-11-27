/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.dao;

import java.util.List;
import org.springframework.orm.hibernate4.HibernateTemplate;
import uy.edu.ort.model.Beans;

/**
 *
 * @author ptrecca
 */
public class BeansDaoHibTemplateImpl implements BeansDao{
    
    private HibernateTemplate hibernateTemplate;
    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
        this.hibernateTemplate.setCheckWriteOperations(false);
    }


    @Override
    public void addBean(Beans bean) {
         this.hibernateTemplate.save(bean);
    }

    @Override
    public List<Beans> listBeans() {
         List<Beans> beans = (List<Beans>) hibernateTemplate.find("select b from Beans b");
        return beans;
    }
    
    
    
}
