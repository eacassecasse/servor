/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks.domain.service;

import mz.co.streamline.soworks.domain.model.Employee;
import mz.co.streamline.soworks.domain.repository.Repository;

/**
 *
 * @author edmilson.cassecasse
 */
public class EmployeeService {
    
    private final Repository<Employee> repository;
    
    public EmployeeService() {
        repository = new Repository<>(Employee.class);
    }
    
}
