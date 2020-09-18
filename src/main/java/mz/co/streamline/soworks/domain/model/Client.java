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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "Client")
@Table(name = "client")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 65, message = "O nome deve conter entre {min} e "
            + "{max} caracteres")
    @Column(name = "name", length = 65, nullable = false)
    protected String name;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "vatNumber", length = 9, nullable = false, unique = true)
    protected Integer vatNumber;
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = 
            FetchType.LAZY, orphanRemoval = true)
    protected List<ClientAddress> addresses  = new ArrayList<>();
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = 
            FetchType.LAZY, orphanRemoval = true)
    protected List<ClientContact> contacts = new ArrayList<>();
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = 
            FetchType.LAZY, orphanRemoval = true)
    protected List<ClientEmail> emails = new ArrayList<>();
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = 
            FetchType.LAZY, orphanRemoval = true)
    protected List<ServiceOrder> orders = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "companyClients", joinColumns = 
            @JoinColumn(name = "client_id", referencedColumnName = "id",
                    nullable = false),
            inverseJoinColumns = 
                    @JoinColumn(name = "company_id", referencedColumnName = "id",
                            nullable = false),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"company_id",
            "client_id"})})
    protected List<Company> companies = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "clientWishes", joinColumns = 
            @JoinColumn(name = "client_id", referencedColumnName = "id", 
                    nullable = false),
            inverseJoinColumns = 
                    @JoinColumn(name = "service_id", referencedColumnName = "id",
                            nullable = false),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"client_id", 
                    "service_id"})})
    protected List<Service> wishes = new ArrayList<>();

    public Client() {
    }

    public Client(@NotNull Integer vatNumber) {
        this.vatNumber = vatNumber;
    }

    
    public Client(@NotNull @NotBlank String name, @NotNull int vatNumber) {
        this.name = name;
        this.vatNumber = vatNumber;
    }

    public void addAddress(@NotNull ClientAddress address) {
        address.setClient(this);
        this.addresses.add(address);
    }
    
    public void removeAddress(@NotNull int index) {
        this.addresses.remove(index);
    }
            
    public void addContact(@NotNull ClientContact contact) {
        contact.setClient(this);
        this.contacts.add(contact);
    }
    
    public void removeContact(@NotNull int index) {
        this.contacts.remove(index);
    }
    
    public void addEmail(@NotNull ClientEmail email) {
        email.setClient(this);
        this.emails.add(email);
    }
    
    public void removeEmail(@NotNull int index) {
        this.emails.remove(index);
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

    public Integer getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(@NotNull Integer vatNumber) {
        this.vatNumber = vatNumber;
    }

    public List<ClientAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<ClientAddress> addresses) {
        this.addresses = addresses;
    }

    public List<ClientContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<ClientContact> contacts) {
        this.contacts = contacts;
    }

    public List<ClientEmail> getEmails() {
        return emails;
    }

    public void setEmails(List<ClientEmail> emails) {
        this.emails = emails;
    }
    
    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Service> getWishes() {
        return wishes;
    }

    public void setWishes(List<Service> wishes) {
        this.wishes = wishes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.vatNumber);
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
        final Client other = (Client) obj;
        return Objects.equals(this.id, other.id);
    }
    
}
