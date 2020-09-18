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

@Entity(name = "Currency")
@Table(name = "currency")
public class Currency implements Serializable {
    
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
    @Length(min = 3, max = 6, message = "O ISO deve conter entre {min} e"
            + " {max} caracteres")
    @Column(name = "iso", length = 6, nullable = false)
    private String iso;

    public Currency() {
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

    public String getIso() {
        return iso;
    }

    public void setIso(@NotNull @NotBlank String iso) {
        this.iso = iso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.iso);
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
        final Currency other = (Currency) obj;
        return Objects.equals(this.id, other.id);
    }

}
