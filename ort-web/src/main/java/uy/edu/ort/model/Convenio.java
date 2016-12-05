package uy.edu.ort.model;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Convenio extends EntidadGenerica{

    
    @Column
    private String codigo;
    
    @Column
    private Date fechaCreacion;
    
    private String fechaCreacionString;
    
    @Column
    private int importeInicialConvenio;
    
    @Column
    private int importeActualConvenio;
    
    @Column
    private boolean estaEnUso;
        
    @ManyToOne(fetch=FetchType.EAGER)
    private Cliente cliente;
    
    private int idCliente;
    
    public Convenio() {
    }

    public Convenio(String codigo, Date fechaCreacion, Cliente cliente, int importeInicialConvenio, boolean estaEnUso, int importeActualConvenio) {
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
        this.cliente = cliente;
        this.importeInicialConvenio = importeInicialConvenio;
        this.importeActualConvenio = importeActualConvenio;
        this.estaEnUso = estaEnUso;
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

    public int getImporteInicialConvenio() {
        return importeInicialConvenio;
    }

    public void setImporteInicialConvenio(int importeInicialConvenio) {
        this.importeInicialConvenio = importeInicialConvenio;
    }

    public int getImporteActualConvenio() {
        return importeActualConvenio;
    }

    public void setImporteActualConvenio(int importeActualConvenio) {
        this.importeActualConvenio = importeActualConvenio;
    }
    
    public String toString() {
        return "Code  : " + codigo;
    }

    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    

    public boolean isEstaEnUso() {
        return estaEnUso;
    }

    public void setEstaEnUso(boolean estaEnUso) {
        this.estaEnUso = estaEnUso;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public String getFechaCreacionString() {
        return fechaCreacionString;
    }

    public void setFechaCreacionString(String fechaCreacionString) {
        this.fechaCreacionString = fechaCreacionString;
    }
}
