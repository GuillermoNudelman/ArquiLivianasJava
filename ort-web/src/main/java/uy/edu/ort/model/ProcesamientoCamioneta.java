/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 *
 * Representa el resultado de la auditoria de camionetas
 */
@Entity
public class ProcesamientoCamioneta extends EntidadGenerica {
    
    @Column
    private String periodo;
    
    @ManyToOne(fetch=FetchType.EAGER)
    private Camioneta camioneta;
    
    @Column
    private Long kilometrosRecorridos;
    
    @Column
    private Long pesoTransportado;
    
    @Column
    private boolean valido;
    
    public ProcesamientoCamioneta(){};

    public ProcesamientoCamioneta(String periodo, Camioneta camioneta, Long kilometrosRecorridos, Long pesoTransportado, boolean vaildo) {
        this.periodo = periodo;
        this.camioneta = camioneta;
        this.kilometrosRecorridos = kilometrosRecorridos;
        this.pesoTransportado = pesoTransportado;
        this.valido = valido;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Camioneta getCamioneta() {
        return camioneta;
    }

    public void setCamioneta(Camioneta camioneta) {
        this.camioneta = camioneta;
    }

    public Long getKilometrosRecorridos() {
        return kilometrosRecorridos;
    }

    public void setKilometrosRecorridos(Long kilometrosRecorridos) {
        this.kilometrosRecorridos = kilometrosRecorridos;
    }

    public Long getPesoTransportado() {
        return pesoTransportado;
    }

    public void setPesoTransportado(Long pesoTransportado) {
        this.pesoTransportado = pesoTransportado;
    }   
    
    
}
