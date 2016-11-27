/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.utilities;

/**
 *
 * @author ptrecca
 */
public class ClusterServiceImp implements ClusterService{

    private String nombre;

    @Override
    public void guardarNombre(String nombreUsuario) {        
        nombre = nombreUsuario;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
    
}
