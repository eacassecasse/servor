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
@Entity(name = "ClientEmail")
@Table(name = "clientEmail")
public class ClientEmail extends Email implements Serializable {
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", 
            nullable = false)
    private Client client;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 65, message = "O tipo deve conter entre {min} e "
            + "{max} caracteres")
    @Column(name = "type", length = 65, nullable = false)
    private String type;

    public ClientEmail() {
    }

    public ClientEmail(@NotNull Client client, @NotNull @NotBlank String 
            description, @NotNull @NotBlank String type) {
        super(description);
        this.client = client;
        this.type = type;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(@NotNull Client client) {
        this.client = client;
    }

    public String getType() {
        return type;
    }

    public void setType(@NotNull @NotBlank String type) {
        this.type = type;
    }
    
}
