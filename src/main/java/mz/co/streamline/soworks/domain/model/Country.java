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
@Entity(name = "Country")
@Table(name = "country")
public class Country implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 2, max = 65, message = "O nome deve conter entre {min} e "
            + "{max} caracteres")
    @Column(name = "name", length = 65, nullable = false)
    private String name;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 2, max = 6, message = "O ISO deve conter entre {min} e "
            + "{max} caracteres")
    @Column(name = "ISO", length = 5, nullable = false)
    private String iso;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 2, max = 8, message = "A extensão deve conter entre {min} e "
            + "{max} caracteres")
    @Column(name = "extension", length = 8, nullable = false, unique = true)
    private String extension;

    public Country() {
    }

    public Country(@NotNull @NotBlank String name, @NotNull @NotBlank String iso, 
            @NotNull @NotBlank String extension) {
        this.name = name;
        this.iso = iso;
        this.extension = extension;
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

    public String getIso() {
        return iso;
    }

    public void setIso(@NotNull @NotBlank String iso) {
        this.iso = iso;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(@NotNull @NotBlank String extension) {
        this.extension = extension;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.iso, this.extension);
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
        final Country other = (Country) obj;
        return Objects.equals(this.id, other.id);
    }

}
