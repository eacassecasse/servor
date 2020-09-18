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
@Entity(name = "EmployeeContact")
@Table(name = "employeeContact")
public class EmployeeContact extends Contact implements Serializable {
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;

    public EmployeeContact() {
    }

    public EmployeeContact(@NotNull Employee employee, @NotNull @NotBlank String 
            description, @NotNull Country country) {
        super(description, country);
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(@NotNull Employee employee) {
        this.employee = employee;
    }

}
