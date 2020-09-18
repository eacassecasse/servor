/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.co.streamline.soworks.domain.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Entity(name = "BankDetails")
@Table(name = "bankDetails")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BankDetails implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 95, message = "O nome titular deve conter entre {min}"
            + " e {max} caracteres")
    @Column(name = "holder", length = 95, nullable = false)
    protected String holder;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "accountNo", columnDefinition = "bigint", nullable = false,
            unique = true)
    protected Long accountNo;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 10, max = 21, message = "O NIB deve conter entre {min} e"
            + " {max} dígitos")
    @Column(name = "NIB", columnDefinition = "bigint", nullable = false)
    protected String nib;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "bank_id", referencedColumnName = "id", nullable = false)
    protected Bank bank;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id", 
            nullable = false)
    protected Currency currency;

    public BankDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(@NotNull Long accountNo) {
        this.accountNo = accountNo;
    }

    public String getNib() {
        return nib;
    }

    public void setNib(@NotNull @NotBlank String nib) {
        this.nib = nib;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(@NotNull Bank bank) {
        this.bank = bank;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(@NotNull Currency currency) {
        this.currency = currency;
    }

    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.accountNo, this.nib, this.holder);
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
        final BankDetails other = (BankDetails) obj;
        return Objects.equals(this.id, other.id);
    }

}
