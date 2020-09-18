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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDateTime;

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "ServiceOrder")
@Table(name = "serviceOrder")
public class ServiceOrder implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @OneToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id", 
            nullable = false)
    private Service service;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", 
            nullable = false)
    private Client client;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "quantity", columnDefinition = "decimal(7,2)", 
            nullable = false)
    private float quantity;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 1, max = 10, message = "A unidade deve conter entre {min} e "
            + "{max} caracteres")
    @Column(name = "unit", length = 10, nullable = false)
    private String unit;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "discount", columnDefinition = "decimal(3,2)", 
            nullable = false)
    private float discount;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "vat", columnDefinition = "decimal(3,2)", 
            nullable = false)
    private float vat;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "totalPrice", columnDefinition = "decimal(14,2)", 
            nullable = false)
    private float totalPrice;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "openDate", columnDefinition = "datetime", nullable = false)
    private LocalDateTime openDate;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(columnDefinition = "enum('ABERTA', 'CANCELADA', 'ESPERA', "
            + "'FINALIZADA')", nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceOrderStatus status;

    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "proforma_id", referencedColumnName = "id", 
            nullable = false)
    private Proforma proforma;
    
    public ServiceOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    
    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
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

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDateTime openDate) {
        this.openDate = openDate;
    }

    public ServiceOrderStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceOrderStatus status) {
        this.status = status;
    }

    public Proforma getProforma() {
        return proforma;
    }

    public void setProforma(Proforma proforma) {
        this.proforma = proforma;
    }

    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.service, this.discount, this.quantity,
                this.status, this.totalPrice, this.unit, this.vat);
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
        final ServiceOrder other = (ServiceOrder) obj;
        return Objects.equals(this.id, other.id);
    }

}
