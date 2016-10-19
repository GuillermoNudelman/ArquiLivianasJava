package uy.edu.ort.model;

import javax.persistence.*;

@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nombreContacto;

    @Column
    private String nombreEmpresa;
    
    @Column
    private String direccion;
    
    @Column
    private int telefono;

    public Cliente() {
    }

    public Cliente(String nombreContacto, String nombreEmpresa, String direccion, int telefono) {
        this.nombreContacto = nombreContacto;
        this.nombreEmpresa = nombreEmpresa;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }  

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
