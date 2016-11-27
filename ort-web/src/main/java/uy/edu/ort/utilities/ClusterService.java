/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.utilities;

/**
 *
 * Este servicio sirve para guardar el nombre del cluster que se esta ejecutando
 */
public interface ClusterService {
    
    void guardarNombre(String nombre);
    
    String getNombre();
    
}
