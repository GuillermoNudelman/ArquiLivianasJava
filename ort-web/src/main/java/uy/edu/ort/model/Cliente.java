package uy.edu.ort.model;

import java.util.List;
import javax.persistence.*;

@Entity
public class Cliente extends EntidadGenerica{    
   
    @Column
    private String nombreContacto;

    @Column
    private String nombreEmpresa;
    
    @Column
    private String direccion;
    
    @Column
    private String telefono;
    
    public Cliente() {
    }

    public Cliente(String nombreContacto, String nombreEmpresa, String direccion, String telefono) {
        this.nombreContacto = nombreContacto;
        this.nombreEmpresa = nombreEmpresa;
        this.direccion = direccion;
        this.telefono = telefono;
        /*
        this.listaConvenios = listaConvenios;*/
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }  
/*
    public List<Convenio> getListaConvenios() {
        return listaConvenios;
    }

    public void setListaConvenios(List<Convenio> listaConvenios) {
        this.listaConvenios = listaConvenios;
    }
    
    public void addConvenio(Convenio convenio) {
        this.listaConvenios.add(convenio);
    }
    
    public void removeConvenio(Convenio convenio) {
        this.listaConvenios.remove(convenio);
    }*/

    @Override
    public String toString() {
        return "Nombre  : " + nombreContacto + "\nEmpresa : " + nombreEmpresa;
    }
        
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.nombreEmpresa != other.getNombreEmpresa() || (this.nombreContacto != other.getNombreContacto())) {
            return false;
        }
        return true;
    }   
}
