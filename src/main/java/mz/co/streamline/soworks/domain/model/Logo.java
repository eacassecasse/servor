/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks.domain.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "Logo")
@Table(name = "logo")
public class Logo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve ser em branco")
    @Length(min = 3, max = 105, message = "O nome da imagem deve conter entre "
            + "{min} e {max} caracteres")
    @Column(name = "name", length = 105, nullable = false)
    private String name;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve ser em branco")
    @Length(min = 3, max = 150, message = "A descrição deve conter entre "
            + "{min} e {max} caracteres")
    @Column(name = "description", length = 150, nullable = false)
    private String description;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "file", nullable = false)
    @Lob
    private byte[] file;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id", 
            nullable = false)
    private Company company;

    public Logo() {
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

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(@NotNull byte[] file) {
        this.file = file;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(@NotNull Company company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.file, this.name);
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
        final Logo other = (Logo) obj;
        return Objects.equals(this.id, other.id);
    }

}
