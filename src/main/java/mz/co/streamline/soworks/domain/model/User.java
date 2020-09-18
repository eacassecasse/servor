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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "User")
@Table(name = "user")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 95, message = "O nome do usuário deve conter entre "
            + "{min} e {max} caracteres")
    @Column(name = "username", length = 95, nullable = false)
    private String username;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 30, message = "A palavra passe deve conter entre "
            + "{min} e {max} caracteres")
    @Column(name = "password", length = 30, nullable = false)
    private String password;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 35, message = "O privilégio deve conter entre "
            + "{min} e {max} caracteres")
    @Column(name = "privilege", length = 35, nullable = false)
    private String privilege;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('ACTIVO','BLOQUEIADO', 'DESACTIVO')",
            nullable = false)
    private UserStatus status;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id", 
            nullable = false)
    private Employee employee;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<UserSession> sessions = new ArrayList<>();
    
   
    public User() {
    }

    public User(String username, String password, String privilege, UserStatus status, Employee employee) {
        this.username = username;
        this.password = password;
        this.privilege = privilege;
        this.status = status;
        this.employee = employee;
    }

    
    public void addSession(UserSession session) {
        session.setUser(this);
        this.sessions.add(session);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<UserSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<UserSession> sessions) {
        this.sessions = sessions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.username, this.password, 
                this.privilege, this.status, this.employee);
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
        final User other = (User) obj;
        return Objects.equals(this.id, other.id);
    }

    
    
    
    @Override
    public String toString() {
        return "User {" + "id = " + this.id + ", username = '" + this.username +
                '\'' + ", password = '" + this.password + '\'' + 
                ", privilege = '" + this.privilege + '\'' + ", status = '" + 
                this.status + '\'' + '}';
    }
     
}
