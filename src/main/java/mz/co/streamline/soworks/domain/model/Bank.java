/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks.domain.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "Bank")
@Table(name = "bank")
public class Bank implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 65, message = "A descrição deve conter entre {min} e"
            + " {max} caracteres")
    @Column(name = "description", length = 65, nullable = false)
    private String description;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 10, message = "A abreviatura deve conter entre {min}"
            + " e {max} caracteres")
    @Column(name = "abreheviation", length = 10, nullable = false)
    private String abreheviation;

    public Bank() {
    }

    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(@NotNull @NotBlank String description) {
        this.description = description;
    }

    public String getAbreheviation() {
        return abreheviation;
    }

    public void setAbreheviation(@NotNull @NotBlank String abreheviation) {
        this.abreheviation = abreheviation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.abreheviation);
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
        final Bank other = (Bank) obj;
        return Objects.equals(this.id, other.id);
    }

}
