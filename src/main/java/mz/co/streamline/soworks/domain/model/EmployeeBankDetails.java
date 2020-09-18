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

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "EmployeeBankDetails")
@Table(name = "employeeBankDetails")
public class EmployeeBankDetails extends BankDetails implements Serializable {
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;

    public EmployeeBankDetails() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
