/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "Service")
@Table(name = "service")
public class Service implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 95, message = "A descrição deve conter entre {min} e "
            + "{max} caracteres")
    @Column(name = "description", length = 95, nullable = false)
    private String description;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "price", columnDefinition = "decimal(14,2)", nullable = false)
    private float price;
    
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "clientWishes", joinColumns = 
            @JoinColumn(name = "service_id", referencedColumnName = "id", 
                    nullable = false),
            inverseJoinColumns = 
                    @JoinColumn(name = "client_id", referencedColumnName = "id",
                            nullable = false),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"client_id", 
                    "service_id"})})
    private List<Client> clients = new ArrayList<>();

    public Service() {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(@NotNull float price) {
        this.price = price;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.price);
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
        final Service other = (Service) obj;
        return Objects.equals(this.id, other.id);
    }

}
