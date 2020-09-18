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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "Company")
@Table(name = "company")
@SuppressWarnings("PersistenceUnitPresent")
public class Company implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 2, max = 75, message = "O nome deve conter entre {min} e "
            + "{max} caracteres")
    @Column(name = "name", length = 75, nullable = false)
    private String name;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 35, message = "O número de licença deve conter entre "
            + "{min} e {max} caracteres")
    @Column(name = "licenceNo", length = 35, nullable = false, unique = true)
    private String licenceNo;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "vatNumber", columnDefinition = "int(9)", nullable = false,
            unique = true)
    private Integer vatNumber;
    
    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL, 
            orphanRemoval = true)
    private Logo logo;
    
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = 
            CascadeType.ALL, orphanRemoval = true)
    private List<CompanyAddress> addresses = new ArrayList<>();
    
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = 
            CascadeType.ALL, orphanRemoval = true)
    private List<CompanyContact> contacts = new ArrayList<>();
    
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = 
            CascadeType.ALL, orphanRemoval = true)
    private List<CompanyEmail> emails = new ArrayList<>();
    
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = 
            CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();
    
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = 
            CascadeType.ALL, orphanRemoval = true)
    private List<CompanyBankDetails> bankDetails = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "company_clients", joinColumns = 
            @JoinColumn(name = "company_id", referencedColumnName = "id",
                    nullable = false),
            inverseJoinColumns = 
                    @JoinColumn(name = "client_id", referencedColumnName = "id",
                            nullable = false),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"company_id",
            "client_id"})})
    private List<Client> clients = new ArrayList<>();

    public Company() {
    }

    public Company(@NotNull @NotBlank String name, @NotNull @NotBlank String 
            licenceNo, @NotNull Integer vatNumber) {
        this.name = name;
        this.licenceNo = licenceNo;
        this.vatNumber = vatNumber;
    }

    public void addAddress(@NotNull CompanyAddress address) {
        address.setCompany(this);
        this.addresses.add(address);
    }
    
    public void removeAddress(@NotNull int index) {
        this.addresses.remove(index);
    }
    
    public void addContact(@NotNull CompanyContact contact) {
        contact.setCompany(this);
        this.contacts.add(contact);
    }
    
    public void removeContact(@NotNull int index) {
        this.contacts.remove(index);
    }
    
    public void addEmail(@NotNull CompanyEmail email) {
        email.setCompany(this);
        this.emails.add(email);
    }
    
    public void removeEmail(@NotNull int index) {
        this.emails.remove(index);
    }
    
    public void addEmployee(Employee employee) {
        employee.setCompany(this);
        this.employees.add(employee);
    }
    
    public void removeEmployee(int index) {
        this.employees.remove(index);
    }
    
    public void addBankDetail(CompanyBankDetails bankDetails) {
        bankDetails.setCompany(this);
        this.bankDetails.add(bankDetails);
    }
    
    public void removeBankDetails(int index) {
        this.bankDetails.remove(index);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull @NotBlank String name) {
        this.name = name;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(@NotNull @NotBlank String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public Integer getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(@NotNull Integer vatNumber) {
        this.vatNumber = vatNumber;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(@NotNull Logo logo) {
        this.logo = logo;
    }

    
    public List<CompanyAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<CompanyAddress> addresses) {
        this.addresses = addresses;
    }

    public List<CompanyContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<CompanyContact> contacts) {
        this.contacts = contacts;
    }

    public List<CompanyEmail> getEmails() {
        return emails;
    }

    public void setEmails(List<CompanyEmail> emails) {
        this.emails = emails;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<CompanyBankDetails> getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(List<CompanyBankDetails> bankDetails) {
        this.bankDetails = bankDetails;
    }

    
    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.licenceNo, this.vatNumber);
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
        final Company other = (Company) obj;
        return Objects.equals(this.id, other.id);
    }
}
