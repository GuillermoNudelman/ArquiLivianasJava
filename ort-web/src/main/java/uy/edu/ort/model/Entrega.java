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
    private Camioneta camioneta;
       
    @OneToOne
    private Chofer chofer;
    
    @Column
    private int distanciaRecorrerKm;
    
    @Column
    private int importeEntrega;
    
    @OneToMany
    @JoinColumn(name="entrega")
    private List<Paquete> listaPaquetes;
    
    public Entrega() {
    }

    public Entrega(String codigo, Date fechaEntrega, Camioneta camioneta, int distanciaRecorrerKm, int importeEntrega, Chofer cofer, List<Paquete> listaPaquetes) {
        this.codigo = codigo;
        this.fechaEntrega = fechaEntrega;
        this.camioneta = camioneta;
        this.distanciaRecorrerKm = distanciaRecorrerKm;
        this.importeEntrega = importeEntrega;
        this.chofer = chofer;
        this.listaPaquetes = listaPaquetes;
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

    public List<Paquete> getListaPaquetes() {
        return listaPaquetes;
    }

    public void setListaPaquetes(List<Paquete> listaPaquetes) {
        this.listaPaquetes = listaPaquetes;
    }    

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
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

}

