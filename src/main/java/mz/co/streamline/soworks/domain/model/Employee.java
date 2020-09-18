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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDate;

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "Employee")
@Table(name = "employee")
public class Employee implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 35, message = "O primeiro nome deve conter entre {min}"
            + " e {max} caracteres")
    @Column(name = "firstName", length = 35, nullable = false)
    private String firstName;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 25, message = "O apelido deve conter entre {min} e "
            + "{max} caracteres")
    @Column(name = "lastName", length = 25, nullable = false)
    private String lastName;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 21, message = "O número de identificação deve conter "
            + "entre {min} e {max} caracteres")
    @Column(name = "idNumber", length = 21, nullable = false, unique = true)
    private String idNumber;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('F', 'M')", nullable = false)
    private Gender gender;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "vatNumber", columnDefinition = "int(9)", nullable = false,
            unique = true)
    private Integer vatNumber;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Past(message = "O data de nasicmento deve ser inferior a data de hoje")
    @Column(name = "birthdate", columnDefinition = "date", nullable = false)
    private LocalDate birthdate;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "age", columnDefinition = "int", nullable = false)
    private int age;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 2, max = 35, message = "A função deve conter entre {min} e "
            + "{max} caracteres")
    @Column(name = "employeerole", length = 35, nullable = false)
    private String employeerole;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "salary", columnDefinition = "decimal(14,2)", nullable = false)
    private float salary;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('ACTIVO', 'DEMITIDO', 'DESACTIVO')", 
            nullable = false)
    private EmployeeStatus status;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", 
            nullable = false)
    private Company company;
    
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, 
            orphanRemoval = true)
    private User user;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = 
            FetchType.LAZY, orphanRemoval = true)
    private List<EmployeeAddress> addresses = new ArrayList<>();
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = 
            FetchType.LAZY, orphanRemoval = true)
    private List<EmployeeContact> contacts = new ArrayList<>();
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = 
            FetchType.LAZY, orphanRemoval = true)
    private List<EmployeeEmail> emails = new ArrayList<>();
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = 
            FetchType.LAZY, orphanRemoval = true)
    private List<EmployeeBankDetails> bankDetails = new ArrayList<>();

    public Employee() {
    }

    public Employee(@NotNull @NotBlank String firstName, @NotNull @NotBlank 
            String lastName, @NotNull @NotBlank String idNumber, @NotNull Integer 
                    vatNumber, @NotNull @Past LocalDate birthdate, @NotNull int 
                            age, @NotNull @NotBlank String role, @NotNull float 
                                    salary, @NotNull EmployeeStatus status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.vatNumber = vatNumber;
        this.birthdate = birthdate;
        this.age = age;
        this.employeerole = role;
        this.salary = salary;
        this.status = status;
    }

    public void addAddress(@NotNull EmployeeAddress address) {
        address.setEmployee(this);
        this.addresses.add(address);
    }
    
    public void removeAddress(@NotNull int index) {
        this.addresses.remove(index);
    }
    
    public void addContact(@NotNull EmployeeContact contact) {
        contact.setEmployee(this);
        this.contacts.add(contact);
    }
    
    public void removeContact(@NotNull int index) {
        this.contacts.remove(index);
    }
    
    public void addEmail(@NotNull EmployeeEmail email) {
        email.setEmployee(this);
        this.emails.add(email);
    }
    
    public void removeEmail(@NotNull int index) {
        this.emails.remove(index);
    }
    
    public void addBankDetails(EmployeeBankDetails bankDetails) {
        bankDetails.setEmployee(this);
        this.bankDetails.add(bankDetails);
    }
    
    public void removeBankDetails(int index) {
      this.bankDetails.remove(index);
    }
    
    public void addUser(User user) {
        user.setEmployee(this);
        this.user = user;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }
    
    public void setName(@NotNull @NotBlank String name) {
        String [] splitter = name.split(" ");
        this.firstName = splitter[0];
        this.lastName = splitter[1];
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull @NotBlank String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull @NotBlank String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(@NotNull @NotBlank String idNumber) {
        this.idNumber = idNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(@NotNull Gender gender) {
        this.gender = gender;
    }
    

    public Integer getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(@NotNull Integer vatNumber) {
        this.vatNumber = vatNumber;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(@NotNull @Past LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(@NotNull int age) {
        this.age = age;
    }
    
    public String getEmployeerole() {
        return employeerole;
    }

    public void setEmployeerole(@NotNull @NotBlank String employeerole) {
        this.employeerole = employeerole;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(@NotNull float salary) {
        this.salary = salary;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull EmployeeStatus status) {
        this.status = status;
    }

    
    public Company getCompany() {
        return company;
    }

    public void setCompany(@NotNull Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(@NotNull User user) {
        this.user = user;
    }

    
    public List<EmployeeAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<EmployeeAddress> addresses) {
        this.addresses = addresses;
    }

    public List<EmployeeContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<EmployeeContact> contacts) {
        this.contacts = contacts;
    }

    public List<EmployeeEmail> getEmails() {
        return emails;
    }

    public void setEmails(List<EmployeeEmail> emails) {
        this.emails = emails;
    }

    public List<EmployeeBankDetails> getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(List<EmployeeBankDetails> bankDetails) {
        this.bankDetails = bankDetails;
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.idNumber,
                this.vatNumber, this.employeerole);
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
        final Employee other = (Employee) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Employee {" + "id = " + this.id + ", firstName = '" + 
                this.firstName + '\'' + ", lastName = '" + this.lastName + 
                '\'' + ", idNumber = '" + this.idNumber + '\'' + ", vatNumber=" + 
                this.vatNumber + ", birthdate = " + this.birthdate + ", age = " +
                this.age + ", role = '" + this.employeerole + '\'' + 
                ", salary = " + this.salary + ", status = '" + this.status + 
                '\'' + '}';
    }

}
