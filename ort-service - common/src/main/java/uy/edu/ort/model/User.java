package uy.edu.ort.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class User extends EntidadGenerica{

    @Column
    private String name;

    @Column
    private String code;

    public User() {
    }

    public User(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Code  : " + code + "\nName : " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.code != null ? this.code.hashCode() : 0);
        return hash;
    }
}
