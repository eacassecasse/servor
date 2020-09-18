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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "Tax")
@Table(name = "tax")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Tax implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 75, message = "O nome deve conter entre {min} e {max}"
            + "caracteres")
    @Column(name = "name", length = 75, nullable = false)
    protected String name;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 2, max = 6, message = "O ISO deve conter entre {min} e {max}"
            + "caracteres")
    @Column(name = "ISO", length = 6, nullable = false)
    protected String iso;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "percentage", columnDefinition = "decimal(3,2)", 
            nullable = false)
    protected float percentage;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "incidenceBase", columnDefinition = "decimal(14,2)", 
            nullable = false)
    protected float incidenceBase;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "netPrice", columnDefinition = "decimal(14,2)", 
            nullable = false)
    protected float netPrice;

    public Tax() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    
    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public float getIncidenceBase() {
        return incidenceBase;
    }

    public void setIncidenceBase(float incidenceBase) {
        this.incidenceBase = incidenceBase;
    }

    public float getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(float netPrice) {
        this.netPrice = netPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.percentage, this.incidenceBase,
                this.netPrice);
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
        final Tax other = (Tax) obj;
        return Objects.equals(this.id, other.id);
    }

}
