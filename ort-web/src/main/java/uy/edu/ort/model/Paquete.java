package uy.edu.ort.model;

import java.util.Date;
import javax.persistence.*;
import uy.edu.ort.model.Cliente;

@Entity
public class Paquete extends EntidadGenerica {

    @Column
    private String codigo;

    @Column
    private Date fechaCreacion;

    private String fechaCreacionString;
    
    @Column
    private int costo;

    @Column
    private int peso;

    @Column
    private String descripcion;

    @Column
    private int descuento;

    @OneToOne
    @JoinColumn(name = "cliente_fk")
    private Cliente cliente;

    private int idCliente;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Entrega entrega;

    @OneToOne
    @JoinColumn(name = "convenio_fk")
    private Convenio convenio;

    public Paquete(String codigo, Date fechaCreacion, int costo, int peso, String descripcion, int descuento, Cliente cliente, Entrega entrega, Convenio convenio) {
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
        this.costo = costo;
        this.peso = peso;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.cliente = cliente;
        this.entrega = entrega;
        this.convenio = convenio;
    }

    public Paquete() {
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

    public String getFechaCreacionString() {
        return fechaCreacionString;
    }

    public void setFechaCreacionString(String fechaCreacionString) {
        this.fechaCreacionString = fechaCreacionString;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    @Override
    public String toString() {
        return "Codigo  : " + codigo + "\nCosto : " + costo + "\nPeso : " + peso + "\nFecha Creacion : " + fechaCreacion + "\nDescripcion : " + descripcion + "\nNombreEmpresa : " + cliente.getNombreEmpresa();
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
