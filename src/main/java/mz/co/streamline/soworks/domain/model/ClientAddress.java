/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks.domain.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "ClientAddress")
@Table(name = "clientAddress")
public class ClientAddress extends Address implements Serializable {
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", 
            nullable = false)
    private Client client;

    public ClientAddress() {
    }

    public ClientAddress(@NotNull Client client, @NotNull @NotBlank String 
            neighborhood, @NotNull @NotBlank String houseNo) {
        super(neighborhood, houseNo);
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(@NotNull Client client) {
        this.client = client;
    }

}
