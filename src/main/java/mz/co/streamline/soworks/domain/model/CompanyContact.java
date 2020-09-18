/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks.domain.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity(name = "CompanyContact")
@Table(name = "companyContact")
public class CompanyContact extends Contact implements Serializable {
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private Company company;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 2, max = 35, message = "O tipo de conter entre {min} e {max} "
            + "caracteres")
    @Column(name = "type", length = 35, nullable = false)
    private String type;

    public CompanyContact() {
    }

    public CompanyContact(@NotNull Company company, @NotNull @NotBlank String 
            type, @NotNull @NotBlank String description, @NotNull Country 
                    country) {
        super(description, country);
        this.company = company;
        this.type = type;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(@NotNull Company company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(@NotNull @NotBlank String type) {
        this.type = type;
    }
 
}
