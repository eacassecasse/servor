/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks.domain.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "Province")
@Table(name = "province")
public class Province implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 35, message = "O nome deve conter entre {min} e "
            + "{max} caracteres")
    private String name;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", 
            nullable = false)
    private Country country;

    public Province() {
    }

    public Province(@NotNull @NotBlank String name, @NotNull Country country) {
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull @NotBlank String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(@NotNull Country country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Province other = (Province) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Province {" + "id = " + this.id + ", name = '" + this.name + '\'' + 
                ", country = '" + this.country + '\'' + '}';
    }
    
    
}
