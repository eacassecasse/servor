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
import org.joda.time.LocalDateTime;

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "CloseOrder")
@Table(name = "closeOrder")
public class CloseOrder extends ServiceOrder implements Serializable {
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "closeDate", columnDefinition = "datetime", nullable = false)
    private LocalDateTime closeDate;

    public CloseOrder() {
    }

    
    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDateTime closeDate) {
        this.closeDate = closeDate;
    }
    
    
}
