/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.dao;

import java.util.List;
import uy.edu.ort.model.Beans;

/**
 * nterfaz de la entidad beans para 
 * que sea persistida en una base de datos
 */
public interface BeansDao {
    
     void addBean(Beans bean);
     
     List<Beans> listBeans();
    
}
