/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks.domain.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "Legal")
@Table(name = "legalClient")
public class Legal extends Client implements Serializable {
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 35, message = "O número de licença deve conter entre "
            + "{min} e {max} caracteres")
    @Column(name = "licenceNo", length = 35, nullable = false, unique = true)
    private String licenceNo;

    public Legal() {
    }

    public Legal(@NotNull @NotBlank String licenceNo, @NotNull @NotBlank String 
            name, @NotNull int vatNumber) {
        super(name, vatNumber);
        this.licenceNo = licenceNo;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(@NotNull @NotBlank String licenceNo) {
        this.licenceNo = licenceNo;
    }
}
