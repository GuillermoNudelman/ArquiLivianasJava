/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * Representará a los beans del sistema
 */

@Entity
public class Beans extends EntidadGenerica{
    
    @Column
    private String nombre;

    @Column
    private String clase;
    
    @Column
    private String nombreCluster;
    
    @Column
    @Temporal(value = TemporalType.DATE)
    private Date fechaOperacion;

    public Beans(String nombre, String clase, String nombreCluster, Date fechaOperacion) {
        this.nombre = nombre;
        this.clase = clase;
        this.nombreCluster = nombreCluster;
        this.fechaOperacion = fechaOperacion;
    }
     public Beans(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getNombreCluster() {
        return nombreCluster;
    }

    public void setNombreCluster(String nombreCluster) {
        this.nombreCluster = nombreCluster;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }
     
     
    
    
    
}
