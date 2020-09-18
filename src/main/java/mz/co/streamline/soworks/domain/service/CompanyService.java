/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks.domain.service;

import mz.co.streamline.soworks.domain.exception.BusinessException;
import mz.co.streamline.soworks.domain.exception.EntityNotFoundException;
import mz.co.streamline.soworks.domain.model.Client;
import mz.co.streamline.soworks.domain.model.Company;
import mz.co.streamline.soworks.domain.model.CompanyBankDetails;
import mz.co.streamline.soworks.domain.model.CompanyContact;
import mz.co.streamline.soworks.domain.model.CompanyEmail;
import mz.co.streamline.soworks.domain.model.Employee;
import mz.co.streamline.soworks.domain.repository.Repository;

/**
 *
 * @author edmilson.cassecasse
 */
public class CompanyService {
    
    private final Repository<Company> repository;
    
    public CompanyService() {
        repository = new Repository<>(Company.class);
    }
    
    public boolean create(Company company) {
        
        Company foundByVatNumber = (Company)repository
                .findByVatNumber(company.getVatNumber());
        
        Company foundByLicenceNo = (Company)repository
                .findByLicenceNo(company.getLicenceNo());
        
        
        if (foundByVatNumber != null && !foundByVatNumber.equals(company))
            throw new BusinessException("Já existe uma empresa com este número "
                    + "de contribuinte");
        
        if (foundByLicenceNo != null && !foundByLicenceNo.equals(company)) 
            throw new BusinessException("Já existe uma empresa com este número "
                    + "de licença");
        
        return repository.save(company);
    }
    
    public boolean addEmployee(Long id, Employee employee) {
        Company company = findById(id);
        
        company.getEmployees().stream().map((e) -> {
            if (e.getIdNumber().equalsIgnoreCase(employee.getIdNumber())) {
                throw new BusinessException("Já existe um funcionário com este "
                        + "número de identificação");
            }
            return e;
        }).filter((e) -> (e.getVatNumber().equals(employee.getVatNumber())))
                .forEachOrdered((_item) -> {
            throw new BusinessException("Já existe um funcionário com este "
                    + "número de concorrente");
        });
        
        company.addEmployee(employee);
        
        return repository.save(company);
    }
    
    public boolean removeEmployee(Long id, Employee employee) {
        int index = -1;
        Company company = findById(id);
        
        if (company.getEmployees().isEmpty())
            throw new BusinessException("Não há funcionários por apresentar");
        
        for (Employee empl: company.getEmployees()) {
            if (empl.equals(employee)) {
                index = company.getEmployees().indexOf(empl);
            }
        }
        company.removeEmployee(index);
        
        return repository.save(company);
    }
    
    public boolean addContact(Long id, CompanyContact contact) {
        Company company = findById(id);
        
        company.addContact(contact);
        
        return repository.save(company);
    }
    
    public boolean removeContact(Long id, CompanyContact contact) {
        int index = -1;
        Company company = findById(id);
        
        if (company.getContacts().isEmpty())
            throw new BusinessException("Não há contactos por apresentar");
        
        for (CompanyContact cc: company.getContacts()) {
            if (cc.equals(contact)) {
                index = company.getContacts().indexOf(cc);
            }
        }
       company.removeContact(index);
       
        return repository.save(company);
    }
    
    public boolean addEmail(Long id, CompanyEmail email) {
        Company company = findById(id);
        
        company.addEmail(email);
        
        return repository.save(company);
    }
    
    public boolean removeEmail(Long id, CompanyEmail email) {
        int index = -1;
        Company company = findById(id);
        
        if (company.getEmails().isEmpty())
            throw new BusinessException("Não há emails por apresentar");
        
        for (CompanyEmail ce: company.getEmails()) {
            if (ce.equals(email)) {
                index = company.getEmails().indexOf(ce);
            }
        }
        company.removeEmail(index);
        
        return repository.save(company);
    }
    
    public boolean addBankDetails(Long id, CompanyBankDetails bankDetails) {
        Company company = findById(id);
        
        company.getBankDetails().add(bankDetails);
        
        return repository.save(company);
    }
    
    public boolean removeBankDetails(Long id, CompanyBankDetails bankDetails) {
        int index = -1;
        Company company = findById(id);
        
        if (company.getBankDetails().isEmpty())
            throw new BusinessException("Não há detalhes bancários por apresentar");
        
        for (CompanyBankDetails details: company.getBankDetails()) {
            if (details.equals(bankDetails)) {
                index = company.getBankDetails().indexOf(details);
            }
        }
        company.removeBankDetails(index);
        
        return repository.save(company);
    }
    
    
    public boolean addClient(Long id, Client client) {
        Company company = findById(id);
        
        company.getClients().add(client);
        
        return repository.save(company);
    }
    
    public boolean removeClient(Long id, Client client) {
        Company company = findById(id);
        
        if (company.getClients().isEmpty())
            throw new BusinessException("Não há clientes por apresentar");
        
        company.getClients().stream().filter((c) -> (c.equals(client)))
                .forEachOrdered((c) -> {
            company.getClients().remove(c);
        });
        
        return repository.save(company);
    }
    
    public boolean delete(Long id) {
        
        return repository.deleteById(id);
    }
    
    private Company findById(Long id) {
        Company company = (Company)repository.findById(id);
        
        if (company == null)
            throw new EntityNotFoundException("Empresa não encontrada");
        
        return company;
    }
    
    
}
