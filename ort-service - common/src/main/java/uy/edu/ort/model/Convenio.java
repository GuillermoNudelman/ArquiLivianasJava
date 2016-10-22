package uy.edu.ort.model;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Convenio extends EntidadGenerica{

    
    @Column
    private String codigo;
    
    @Column
    private Date fechaCreacion;
    
    @OneToOne
    @JoinColumn(name="cliente_fk")
    private Cliente cliente;
    
    @Column
    private int importeInicialConvenio;
    
    @Column
    private int importeActualConvenio;
    
    public Convenio() {
    }

    public Convenio(String codigo, Date fechaCreacion, /*Cliente cliente, */ int importeInicialConvenio, int importeActualConvenio) {
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
        this.cliente = cliente;
        this.importeInicialConvenio = importeInicialConvenio;
        this.importeActualConvenio = importeActualConvenio;
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
}
