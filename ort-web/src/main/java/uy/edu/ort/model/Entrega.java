package uy.edu.ort.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Entrega extends EntidadGenerica {

    @Column
    private String codigo;

    @Column
    private Date fechaEntrega;

    private String fechaEntregaString;

    @OneToOne
    private Camioneta camioneta;
    private int idCamioneta;

    @OneToOne
    private Chofer chofer;
    private int idChofer;

    @Column
    private int distanciaRecorrerKm;

    @Column
    private int importeEntrega;

    private String listaPaquetesString;

    public Entrega() {
    }

    public Entrega(String codigo, Date fechaEntrega, Camioneta camioneta, int distanciaRecorrerKm, int importeEntrega, Chofer cofer) {
        this.codigo = codigo;
        this.fechaEntrega = fechaEntrega;
        this.camioneta = camioneta;
        this.distanciaRecorrerKm = distanciaRecorrerKm;
        this.importeEntrega = importeEntrega;
        this.chofer = chofer;      
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

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public String getFechaEntregaString() {
        return fechaEntregaString;
    }

    public void setFechaEntregaString(String fechaEntregaString) {
        this.fechaEntregaString = fechaEntregaString;
    }

    public int getIdCamioneta() {
        return idCamioneta;
    }

    public void setIdCamioneta(int idCamioneta) {
        this.idCamioneta = idCamioneta;
    }

    public int getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(int idChofer) {
        this.idChofer = idChofer;
    }

    public String getListaPaquetesString() {
        return listaPaquetesString;
    }

    public void setListaPaquetesString(String listaPaquetesString) {
        this.listaPaquetesString = listaPaquetesString;
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
