/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vermeg.insuranceproducts.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mdsaadlaoui
 */
@Entity
@Table(name = "policy", catalog = "insurance", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Policy.findAll", query = "SELECT p FROM Policy p")
    , @NamedQuery(name = "Policy.findByIdentifier", query = "SELECT p FROM Policy p WHERE p.identifier = :identifier")
    , @NamedQuery(name = "Policy.findByCreationDate", query = "SELECT p FROM Policy p WHERE p.creationDate = :creationDate")
    , @NamedQuery(name = "Policy.findByStartDate", query = "SELECT p FROM Policy p WHERE p.startDate = :startDate")
    , @NamedQuery(name = "Policy.findByEndDate", query = "SELECT p FROM Policy p WHERE p.endDate = :endDate")
    , @NamedQuery(name = "Policy.findByTotalAmount", query = "SELECT p FROM Policy p WHERE p.totalAmount = :totalAmount")
    , @NamedQuery(name = "Policy.findByCurrentAmount", query = "SELECT p FROM Policy p WHERE p.currentAmount = :currentAmount")
    , @NamedQuery(name = "Policy.findByStatus", query = "SELECT p FROM Policy p WHERE p.status = :status")
    , @NamedQuery(name = "Policy.findByIsActive", query = "SELECT p FROM Policy p WHERE p.isActive = :isActive")
    , @NamedQuery(name = "Policy.findByBillAmount", query = "SELECT p FROM Policy p WHERE p.billAmount = :billAmount")
    , @NamedQuery(name = "Policy.findByBullsNumber", query = "SELECT p FROM Policy p WHERE p.bullsNumber = :bullsNumber")
    , @NamedQuery(name = "Policy.findByNextBillDate", query = "SELECT p FROM Policy p WHERE p.nextBillDate = :nextBillDate")
    , @NamedQuery(name = "Policy.findByInsuranceType", query = "SELECT p FROM Policy p WHERE p.insuranceType = :insuranceType")
    , @NamedQuery(name = "Policy.findByCarModel", query = "SELECT p FROM Policy p WHERE p.carModel = :carModel")
    , @NamedQuery(name = "Policy.findByCarFiscalPower", query = "SELECT p FROM Policy p WHERE p.carFiscalPower = :carFiscalPower")
    , @NamedQuery(name = "Policy.findByCarGreycardNumber", query = "SELECT p FROM Policy p WHERE p.carGreycardNumber = :carGreycardNumber")
    , @NamedQuery(name = "Policy.findByCarConstructionYear", query = "SELECT p FROM Policy p WHERE p.carConstructionYear = :carConstructionYear")
    , @NamedQuery(name = "Policy.findByLifeInsuredRetirementAge", query = "SELECT p FROM Policy p WHERE p.lifeInsuredRetirementAge = :lifeInsuredRetirementAge")
    , @NamedQuery(name = "Policy.findByLifeInsuredBirthDate", query = "SELECT p FROM Policy p WHERE p.lifeInsuredBirthDate = :lifeInsuredBirthDate")
    , @NamedQuery(name = "Policy.findByLifeInsuredIsSmoker", query = "SELECT p FROM Policy p WHERE p.lifeInsuredIsSmoker = :lifeInsuredIsSmoker")
    , @NamedQuery(name = "Policy.findByLifeInsuredHealthCovered", query = "SELECT p FROM Policy p WHERE p.lifeInsuredHealthCovered = :lifeInsuredHealthCovered")
    , @NamedQuery(name = "Policy.findByLifeInsuredLoanEligible", query = "SELECT p FROM Policy p WHERE p.lifeInsuredLoanEligible = :lifeInsuredLoanEligible")
    , @NamedQuery(name = "Policy.findByLifeInsuredMaxLoanAmount", query = "SELECT p FROM Policy p WHERE p.lifeInsuredMaxLoanAmount = :lifeInsuredMaxLoanAmount")
    , @NamedQuery(name = "Policy.findByLifeInsuredCapital", query = "SELECT p FROM Policy p WHERE p.lifeInsuredCapital = :lifeInsuredCapital")})
public class Policy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identifier", nullable = false)
    private Integer identifier;
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_amount", precision = 22)
    private Double totalAmount;
    @Column(name = "current_amount", precision = 22)
    private Double currentAmount;
    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "bill_amount", precision = 22)
    private Double billAmount;
    @Column(name = "bulls_number")
    private Integer bullsNumber;
    @Column(name = "next_bill_date")
    @Temporal(TemporalType.DATE)
    private Date nextBillDate;
    @Size(max = 50)
    @Column(name = "insurance_type", length = 50)
    private String insuranceType;
    @Size(max = 50)
    @Column(name = "car_model", length = 50)
    private String carModel;
    @Size(max = 50)
    @Column(name = "car_fiscal_power", length = 50)
    private String carFiscalPower;
    @Size(max = 50)
    @Column(name = "car_greycard_number", length = 50)
    private String carGreycardNumber;
    @Column(name = "car_construction_year")
    @Temporal(TemporalType.DATE)
    private Date carConstructionYear;
    @Column(name = "life_insured_retirement_age")
    private Integer lifeInsuredRetirementAge;
    @Column(name = "life_insured_birth_date")
    @Temporal(TemporalType.DATE)
    private Date lifeInsuredBirthDate;
    @Column(name = "life_insured_is_smoker")
    private Boolean lifeInsuredIsSmoker;
    @Column(name = "life_insured_health_covered")
    private Boolean lifeInsuredHealthCovered;
    @Column(name = "life_insured_loan_eligible")
    private Boolean lifeInsuredLoanEligible;
    @Column(name = "life_insured_max_loan_amount", precision = 22)
    private Double lifeInsuredMaxLoanAmount;
    @Column(name = "life_insured_capital", precision = 22)
    private Double lifeInsuredCapital;
    @OneToMany(mappedBy = "policy")
    private List<Answer> answerList;
    @JoinColumn(name = "broker", referencedColumnName = "identifier")
    @ManyToOne
    private Broker broker;
    @JoinColumn(name = "client", referencedColumnName = "nationalidentifier")
    @ManyToOne
    private Client client;

    public Policy() {
    }

    public Policy(Integer identifier) {
        this.identifier = identifier;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public Integer getBullsNumber() {
        return bullsNumber;
    }

    public void setBullsNumber(Integer bullsNumber) {
        this.bullsNumber = bullsNumber;
    }

    public Date getNextBillDate() {
        return nextBillDate;
    }

    public void setNextBillDate(Date nextBillDate) {
        this.nextBillDate = nextBillDate;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarFiscalPower() {
        return carFiscalPower;
    }

    public void setCarFiscalPower(String carFiscalPower) {
        this.carFiscalPower = carFiscalPower;
    }

    public String getCarGreycardNumber() {
        return carGreycardNumber;
    }

    public void setCarGreycardNumber(String carGreycardNumber) {
        this.carGreycardNumber = carGreycardNumber;
    }

    public Date getCarConstructionYear() {
        return carConstructionYear;
    }

    public void setCarConstructionYear(Date carConstructionYear) {
        this.carConstructionYear = carConstructionYear;
    }

    public Integer getLifeInsuredRetirementAge() {
        return lifeInsuredRetirementAge;
    }

    public void setLifeInsuredRetirementAge(Integer lifeInsuredRetirementAge) {
        this.lifeInsuredRetirementAge = lifeInsuredRetirementAge;
    }

    public Date getLifeInsuredBirthDate() {
        return lifeInsuredBirthDate;
    }

    public void setLifeInsuredBirthDate(Date lifeInsuredBirthDate) {
        this.lifeInsuredBirthDate = lifeInsuredBirthDate;
    }

    public Boolean getLifeInsuredIsSmoker() {
        return lifeInsuredIsSmoker;
    }

    public void setLifeInsuredIsSmoker(Boolean lifeInsuredIsSmoker) {
        this.lifeInsuredIsSmoker = lifeInsuredIsSmoker;
    }

    public Boolean getLifeInsuredHealthCovered() {
        return lifeInsuredHealthCovered;
    }

    public void setLifeInsuredHealthCovered(Boolean lifeInsuredHealthCovered) {
        this.lifeInsuredHealthCovered = lifeInsuredHealthCovered;
    }

    public Boolean getLifeInsuredLoanEligible() {
        return lifeInsuredLoanEligible;
    }

    public void setLifeInsuredLoanEligible(Boolean lifeInsuredLoanEligible) {
        this.lifeInsuredLoanEligible = lifeInsuredLoanEligible;
    }

    public Double getLifeInsuredMaxLoanAmount() {
        return lifeInsuredMaxLoanAmount;
    }

    public void setLifeInsuredMaxLoanAmount(Double lifeInsuredMaxLoanAmount) {
        this.lifeInsuredMaxLoanAmount = lifeInsuredMaxLoanAmount;
    }

    public Double getLifeInsuredCapital() {
        return lifeInsuredCapital;
    }

    public void setLifeInsuredCapital(Double lifeInsuredCapital) {
        this.lifeInsuredCapital = lifeInsuredCapital;
    }

    @XmlTransient
    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identifier != null ? identifier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Policy)) {
            return false;
        }
        Policy other = (Policy) object;
        if ((this.identifier == null && other.identifier != null) || (this.identifier != null && !this.identifier.equals(other.identifier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vermeg.insuranceproducts.entities.Policy[ identifier=" + identifier + " ]";
    }
    
}
