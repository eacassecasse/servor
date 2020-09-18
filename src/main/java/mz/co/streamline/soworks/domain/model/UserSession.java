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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.joda.time.LocalDateTime;

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "UserSession")
@Table(name = "userSession")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserSession implements Serializable {
    
    @Id
    private Long id;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "openDate", columnDefinition = "datetime", nullable = false)
    private LocalDateTime openDate;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "enum('ABERTA', 'FECHADA')",
            nullable = false)
    private SessionStatus status;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    
    public UserSession() {
    }

    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public LocalDateTime getOpenDate() {
        return openDate;
    }

    public void setOpenDate(@NotNull LocalDateTime openDate) {
        this.openDate = openDate;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull SessionStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(@NotNull User user) {
        this.user = user;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.openDate, this.status, this.user);
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
        final UserSession other = (UserSession) obj;
        return Objects.equals(this.id, other.id);
    }

    
}
