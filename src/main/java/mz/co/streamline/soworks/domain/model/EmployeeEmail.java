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
@Entity(name = "EmployeeEmail")
@Table(name = "employeeEmail")
public class EmployeeEmail extends Email implements Serializable {
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 2, max = 35, message = "O tipo deve conter entre {min} e "
            + "{max} caracteres")
    @Column(name = "type", length = 35, nullable = false)
    private String type;

    public EmployeeEmail() {
    }

    public EmployeeEmail(@NotNull Employee employee, @NotNull @NotBlank String 
            description, @NotNull @NotBlank String type) {
        super(description);
        this.employee = employee;
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(@NotNull Employee employee) {
        this.employee = employee;
    }

    public String getType() {
        return type;
    }

    public void setType(@NotNull @NotBlank String type) {
        this.type = type;
    }

}
