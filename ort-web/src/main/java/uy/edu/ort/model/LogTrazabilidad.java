package uy.edu.ort.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class LogTrazabilidad extends EntidadGenerica {

    @Column
    private String usuario;

    @Column
    @Temporal(value = TemporalType.DATE)
    private Date fechaOperacion;

    @Column
    private String nombreOperacion;

    @Column
    private String parametros;

    @Column
    private long tiempoEjecucion;

    public LogTrazabilidad() {
    }

    public LogTrazabilidad(String usuario, Date fechaOperacion, String nombreOperacion, long tiempoEjecucion, String parametros) {
        this.usuario = usuario;
        this.fechaOperacion = fechaOperacion;
        this.nombreOperacion = nombreOperacion;
        this.tiempoEjecucion = tiempoEjecucion;
        this.parametros = parametros;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getParametros() {
        return parametros;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public long getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(long tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public String toString() {
        return "Operacion: " + nombreOperacion + " Usuario: " + usuario;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        return true;
    }

}
