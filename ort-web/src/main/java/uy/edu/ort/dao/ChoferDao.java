/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.dao;

import java.util.List;
import uy.edu.ort.model.Chofer;

/**
 *
 * @author ptrecca
 */
public interface ChoferDao {
    
    void addChofer(Chofer chofer);

    void removeChofer(Chofer chofer);

    List<Chofer> listChofer();
    
    Chofer buscarChoferPorCodigo(String codigo);
    
    Chofer buscarChoferPorId(Long id);
    
    void editarChofer(Chofer chofer);
    
    
}
