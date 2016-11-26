/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Esta clase sirve para representar los choferes del sistema
 * 
 */
@Entity
public class Chofer extends EntidadGenerica{    
   
    @Column
    private String codigo;

    @Column
    private String nombre;
    
    @Column
    private String libretaDeConducir;

    public Chofer(String codigo, String nombre, String libretaDeConducir) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.libretaDeConducir = libretaDeConducir;
    }
    
    public Chofer(){}

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLibretaDeConducir() {
        return libretaDeConducir;
    }

    public void setLibretaDeConducir(String libretaDeConducir) {
        this.libretaDeConducir = libretaDeConducir;
    }
    
    
    public String toString() {
        return "Codigo  : " + codigo + "\nNombre : " + nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Chofer other = (Chofer) obj;
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

    
    
        
}
