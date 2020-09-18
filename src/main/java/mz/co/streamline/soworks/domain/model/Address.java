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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Entity(name = "Address")
@Table(name = "address")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Address implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @NotBlank(message = "Este campo não deve estar em branco")
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Length(min = 3, max = 95, 
            message = "O bairro deve conter entre {min} e {max} caracteres")
    @Column(name = "neighborhood", length = 95, nullable = false)
    protected String neighborhood;
    
    @NotBlank(message = "Este campo não deve estar em branco")
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Length(min = 1, max = 15, 
            message = "O número deve conter entre {min} e {max} caracteres")
    @Column(name = "houseNo", length = 15, nullable = false)
    protected String houseNo;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id", 
            nullable = false)
    protected Province province;

    public Address() {
    }

    public Address(@NotNull @NotBlank String neighborhood, 
            @NotNull @NotBlank String houseNo) {
        this.neighborhood = neighborhood;
        this.houseNo = houseNo;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(@NotNull @NotBlank String houseNo) {
        this.houseNo = houseNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(@NotNull Province province) {
        this.province = province;
    }

    
    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(@NotNull @NotBlank String neighborhood) {
        this.neighborhood = neighborhood;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.neighborhood, this.houseNo);
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
        final Address other = (Address) obj;
        return Objects.equals(this.id, other.id);
    }

}
