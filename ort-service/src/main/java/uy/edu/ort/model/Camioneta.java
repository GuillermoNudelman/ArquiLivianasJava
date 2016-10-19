package uy.edu.ort.model;

import javax.persistence.*;

@Entity
public class Camioneta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String codigo;

    @Column
    private String placa;
    
    @Column
    private Long capacidadKgs;
    
    @Column
    private Long kmsRecorridos;
    
    @Column
    private Long kmsProxService;
    
    /*
    @OneToOne
    @JoinColumn(name="user_fk")
    private User user;
*/
    public Camioneta() {
    }

    public Camioneta(String codigo, String placa, Long capacidadKgs, Long kmsRecorridos, Long kmsProxService) {
        this.codigo = codigo;
        this.placa = placa;
        this.capacidadKgs = capacidadKgs;
        this.kmsRecorridos = kmsRecorridos;
        this.kmsProxService = kmsProxService;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Long getCapacidadKgs() {
        return capacidadKgs;
    }

    public void setCapacidadKgs(Long capacidadKgs) {
        this.capacidadKgs = capacidadKgs;
    }

    public Long getKmsRecorridos() {
        return kmsRecorridos;
    }

    public void setKmsRecorridos(Long kmsRecorridos) {
        this.kmsRecorridos = kmsRecorridos;
    }

    public Long getKmsProxService() {
        return kmsProxService;
    }

    public void setKmsProxService(Long kmsProxService) {
        this.kmsProxService = kmsProxService;
    }

    

    
    public String toString() {
        return "Código  : " + codigo + "\nPlaca : " + placa;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Camioneta other = (Camioneta) obj;
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

    /*@Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
        return hash;
    }*/
}
