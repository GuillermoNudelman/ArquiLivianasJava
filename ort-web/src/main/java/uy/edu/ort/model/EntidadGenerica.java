package uy.edu.ort.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.*;

@MappedSuperclass
public class EntidadGenerica implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    public int getId(){
        return id;
    }
}