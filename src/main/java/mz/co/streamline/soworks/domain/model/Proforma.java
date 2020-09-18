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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDate;

/**
 *
 * @author edmilson.cassecasse
 */
@Entity(name = "Proforma")
@Table(name = "proforma")
public class Proforma implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id", 
            nullable = false)
    private Currency currency;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "exchange", columnDefinition = "decimal(5,8)", 
            nullable = false)
    private float exchange;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "issueDare", columnDefinition = "date", nullable = false)
    private LocalDate issueDate;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "expireDate", columnDefinition = "date", nullable = false)
    private LocalDate expireDate;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "clientDiscount", columnDefinition = "decimal(3,2)", 
            nullable = false)
    private float clientDiscount;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "financialDiscountPercentage", columnDefinition = "decimal(3,2)", 
            nullable = false)
    private float financialDiscountpercentage;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @NotBlank(message = "Este campo não deve estar em branco")
    @Length(min = 3, max = 65, message = "A condição de Pagamento ")
    @Column(name = "paymentCondition", length = 65, nullable = false)
    private String paymentCondition;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "totalPrice", columnDefinition = "decimal(14,2)", 
            nullable = false)
    private float totalPrice;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "commercialDiscount", columnDefinition = "decimal(14,2)", 
            nullable = false)
    private float commercialDiscount;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "financialDiscount", columnDefinition = "decimal(14,2)", 
            nullable = false)
    private float financialDiscount;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "shippingPrice", columnDefinition = "decimal(14,2)", 
            nullable = false)
    private float shippingPrice;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "otherServicePrice", columnDefinition = "decimal(14,2)", 
            nullable = false)
    private float otherServicesPrice;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "advancesPrice", columnDefinition = "decimal(14,2)", 
            nullable = false)
    private float advancesPrice;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "exciseDutyPrice", columnDefinition = "decimal(14,2)", 
            nullable = false)
    private float exciseDutyPrice;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "vat", columnDefinition = "decimal(14,2)", nullable = false)
    private float vat;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "hitPrice", columnDefinition = "decimal(14,2)", 
            nullable = false)
    private float hitPrice;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @Column(name = "netPrice", columnDefinition = "decimal(14,2)", 
            nullable = false)
    private float netPrice;
    
    @NotNull(message = "Campo obrigatório, deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id", 
            nullable = false)
    private UserSession session;
    
    @OneToMany(mappedBy = "proforma", fetch = FetchType.LAZY)
    private List<ServiceOrder> orders = new ArrayList<>();
    
    @OneToMany(mappedBy = "proforma", fetch = FetchType.LAZY)
    private List<ProformaTax> taxes = new ArrayList<>();
    

    public Proforma() {
    }

    
    public void addOrder(ServiceOrder order) {
        order.setProforma(this);
        this.netPrice += order.getTotalPrice();
        this.vat += order.getVat() * order.getTotalPrice();
    }
    
    public void removeOrder(int index) {
        ServiceOrder order = this.orders.get(index);
        this.netPrice -= order.getTotalPrice();
        this.vat -= order.getVat() * order.getTotalPrice();
    }
    
    
    public void addTax(ProformaTax tax) {
        tax.setProforma(this);
        this.taxes.add(tax);
    }
    
    public void removeTax(int index) {
        this.taxes.remove(index);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getExchange() {
        return exchange;
    }

    public void setExchange(float exchange) {
        this.exchange = exchange;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public float getClientDiscount() {
        return clientDiscount;
    }

    public void setClientDiscount(float clientDiscount) {
        this.clientDiscount = clientDiscount;
    }

    public float getFinancialDiscountpercentage() {
        return financialDiscountpercentage;
    }

    public void setFinancialDiscountpercentage(float financialDiscountpercentage) {
        this.financialDiscountpercentage = financialDiscountpercentage;
    }

    public String getPaymentCondition() {
        return paymentCondition;
    }

    public void setPaymentCondition(String paymentCondition) {
        this.paymentCondition = paymentCondition;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getCommercialDiscount() {
        return commercialDiscount;
    }

    public void setCommercialDiscount(float commercialDiscount) {
        this.commercialDiscount = commercialDiscount;
    }

    public float getFinancialDiscount() {
        return financialDiscount;
    }

    public void setFinancialDiscount(float financialDiscount) {
        this.financialDiscount = financialDiscount;
    }

    public float getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(float shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public float getOtherServicesPrice() {
        return otherServicesPrice;
    }

    public void setOtherServicesPrice(float otherServicesPrice) {
        this.otherServicesPrice = otherServicesPrice;
    }

    public float getAdvancesPrice() {
        return advancesPrice;
    }

    public void setAdvancesPrice(float advancesPrice) {
        this.advancesPrice = advancesPrice;
    }

    public float getExciseDutyPrice() {
        return exciseDutyPrice;
    }

    public void setExciseDutyPrice(float exciseDutyPrice) {
        this.exciseDutyPrice = exciseDutyPrice;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    public float getHitPrice() {
        return hitPrice;
    }

    public void setHitPrice(float hitPrice) {
        this.hitPrice = hitPrice;
    }

    public float getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(float netPrice) {
        this.netPrice = netPrice;
    }

    public UserSession getSession() {
        return session;
    }

    public void setSession(UserSession session) {
        this.session = session;
    }

    public List<ServiceOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ServiceOrder> orders) {
        this.orders = orders;
    }

    public List<ProformaTax> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<ProformaTax> taxes) {
        this.taxes = taxes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.advancesPrice, this.clientDiscount, 
                this.commercialDiscount, this.currency, this.exchange, 
                this.exciseDutyPrice, this.expireDate, this.financialDiscount,
                this.financialDiscountpercentage, this.hitPrice, this.issueDate,
                this.netPrice, this.otherServicesPrice, this.paymentCondition);
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
        final Proforma other = (Proforma) obj;
        return Objects.equals(this.id, other.id);
    }
 
}
