/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.dao;

import java.util.List;
import uy.edu.ort.model.ProcesamientoCamioneta;

/**
 *
 * @author ptrecca
 */
public interface ProcesamientoCamionetaDao {
    
    void addProcesamientoCamioneta(ProcesamientoCamioneta pcamioneta);
    
    List<ProcesamientoCamioneta> listaProcesamientoCamioneta();
}
