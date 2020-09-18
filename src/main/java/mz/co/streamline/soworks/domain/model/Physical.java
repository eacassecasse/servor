/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks.domain.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Entity(name = "Physical")
@Table(name = "physicalClient")
public class Physical extends Client implements Serializable {
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 35, message = "O primeiro nome deve conter entre {min}"
            + " e {max} caracteres")
    @Column(name = "firstName", length = 35, nullable = true)
    private String firstName;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 25, message = "O apelido deve conter entre {min} e "
            + "{max} caracteres")
    @Column(name = "lastName", length = 25, nullable = true)
    private String lastName;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 21, message = "O número de identificação deve conter "
            + "entre {min} e {max} caracteres")
    @Column(name = "idNumber", length = 21, nullable = false, unique = true)
    private String idNumber;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('M', 'F')", nullable = false)
    private Gender gender;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Past(message = "A data de nascimento deve ser inferior a data de hoje")
    @Column(name = "birthdate", columnDefinition = "date", nullable = false)
    private LocalDate birthdate;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "age", columnDefinition = "int(3)", nullable = false)
    private int age;

    public Physical() {
    }

    public Physical(@NotNull @NotBlank String firstName, @NotNull @NotBlank 
            String lastName, @NotNull @NotBlank String idNumber, @NotNull Gender 
                    gender, @NotNull @Past LocalDate birthdate, @NotNull int age, 
                    @NotNull int vatNumber) {
        super(vatNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.gender = gender;
        this.birthdate = birthdate;
        this.age = age;
    }

    @Override
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public void setName(String name) {
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
}
