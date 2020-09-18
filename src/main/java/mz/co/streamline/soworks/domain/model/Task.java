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
@Entity(name = "Task")
@Table(name = "task")
public class Task implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private ServiceOrder order;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 105, message = "A descrição deve conter entre {min} e "
            + "{max} caracteres")
    @Column(name = "description", length = 105, nullable = false)
    private String description;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 1, max = 25, message = "A unidade deve conter entre {min} e "
            + "{max} caracteres")
    @Column(name = "unit", length = 25, nullable = false)
    private String unit;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "quantity", columnDefinition = "numeric(14,2)", 
            nullable = false)
    private float quantity;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "unitPrice", columnDefinition = "decimal(14,2)", 
            nullable = false)
    private float unitPrice;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "vat", columnDefinition = "decimal(14,2)", nullable = false)
    private float vat;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "totalPrice", columnDefinition = "decimal(14,2)", 
            nullable = false)
    private float totalPrice;

    public Task() {
    }

    public Task(@NotNull ServiceOrder order, @NotNull @NotBlank String 
            description, @NotNull @NotBlank String unit, @NotNull float quantity, 
            @NotNull float unitPrice) {
        this.order = order;
        this.description = description;
        this.unit = unit;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public ServiceOrder getOrder() {
        return order;
    }

    public void setOrder(@NotNull ServiceOrder order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(@NotNull @NotBlank String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(@NotNull @NotBlank String unit) {
        this.unit = unit;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull float quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(@NotNull float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    
    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(@NotNull float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.quantity, this.totalPrice, 
                this.unit, this.unitPrice);
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
        final Task other = (Task) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Task{" + "id = " + this.id + ", order = '" + this.order + '\'' +
                ", description = '" + this.description + '\'' + ", unit = '" + 
                this.unit + '\'' + ", quantity = " + this.quantity + ", unitPrice = " + 
                this.unitPrice + ", vat = " + this.vat + ", totalPrice = " + 
                this.totalPrice + '}';
    }
    
    
}
