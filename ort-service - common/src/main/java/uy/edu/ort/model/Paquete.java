package uy.edu.ort.model;

import java.util.Date;
import javax.persistence.*;
import uy.edu.ort.model.Cliente;

@Entity
public class Paquete extends EntidadGenerica{
    
       
    @Column
    private String codigo;

    @Column
    private Date fechaCreacion;
    
    @Column
    private int costo;
    
    @Column
    private int peso;
    
    @Column
    private String descripcion;
    
    @OneToOne
    @JoinColumn(name="cliente_fk")
    private Cliente cliente;

    public Paquete(String codigo, Date fechaCreacion, int costo, int peso, String descripcion, Cliente cliente) {
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
        this.costo = costo;
        this.peso = peso;
        this.descripcion = descripcion;
        this.cliente = cliente;
    }
    
    public Paquete(){
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public String toString() {
        return "Codigo  : " + codigo + "\nCosto : " + costo+ "\nPeso : " + peso+ "\nFecha Creacion : " + fechaCreacion+ "\nDescripcion : " + descripcion+ "\nNombreEmpresa : " + cliente.getNombreEmpresa();
    }
        
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paquete other = (Paquete) obj;
        if (this.codigo != other.getCodigo()) {
            return false;
        }
        return true;
    }   
    
    
}
