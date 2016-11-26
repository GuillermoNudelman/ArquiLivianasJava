/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.service;

import java.util.List;
import uy.edu.ort.model.Chofer;

/**
 *
 * @author ptrecca
 */
public interface ChoferService {
    
    public void addChofer(Chofer chofer);

    public void removeChofer(Chofer chofer);

    public List<Chofer> listChofer();
    
    public Chofer buscarChoferPorCodigo(String codigo);
    
    Chofer buscarChoferPorId(Long id);
    
    public void editarChofer(Chofer chofer);
    
}
