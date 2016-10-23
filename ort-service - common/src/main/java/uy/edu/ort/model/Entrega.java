package uy.edu.ort.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Entrega extends EntidadGenerica{

    @Column
    private String codigo;
    
    @Column
    private Date fechaEntrega;
    
    @OneToOne
    @JoinColumn(name="camioneta_fk")
    private Camioneta camioneta;
    
    @OneToMany
    private List<Paquete> listaPaquetes;

    @Column
    private int distanciaRecorrerKm;
    
    @Column
    private int importeEntrega;
    
    public Entrega() {
    }

    public Entrega(String codigo, Date fechaEntrega, Camioneta camioneta, List<Paquete> listaPaquetes,  int distanciaRecorrerKm, int importeEntrega) {
        this.codigo = codigo;
        this.fechaEntrega = fechaEntrega;
        this.camioneta = camioneta;
        this.listaPaquetes = listaPaquetes; 
        this.distanciaRecorrerKm = distanciaRecorrerKm;
        this.importeEntrega = importeEntrega;
        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Camioneta getCamioneta() {
        return camioneta;
    }

    public void setCamioneta(Camioneta camioneta) {
        this.camioneta = camioneta;
    }

    public int getDistanciaRecorrerKm() {
        return distanciaRecorrerKm;
    }

    public void setDistanciaRecorrerKm(int distanciaRecorrerKm) {
        this.distanciaRecorrerKm = distanciaRecorrerKm;
    }

    public int getImporteEntrega() {
        return importeEntrega;
    }

    public void setImporteEntrega(int importeEntrega) {
        this.importeEntrega = importeEntrega;
    }

    

    public String toString() {
        return "Code  : " + codigo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entrega other = (Entrega) obj;
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

    /*
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.code != null ? this.code.hashCode() : 0);
        return hash;
    }
*/
}

