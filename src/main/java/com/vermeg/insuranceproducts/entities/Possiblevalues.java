/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vermeg.insuranceproducts.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mdsaadlaoui
 */
@Entity
@Table(name = "possiblevalues", catalog = "insurance", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Possiblevalues.findAll", query = "SELECT p FROM Possiblevalues p")
    , @NamedQuery(name = "Possiblevalues.findByIdentifier", query = "SELECT p FROM Possiblevalues p WHERE p.identifier = :identifier")
    , @NamedQuery(name = "Possiblevalues.findByValue", query = "SELECT p FROM Possiblevalues p WHERE p.value = :value")
    , @NamedQuery(name = "Possiblevalues.findByDisplayLabel", query = "SELECT p FROM Possiblevalues p WHERE p.displayLabel = :displayLabel")})
public class Possiblevalues implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identifier", nullable = false)
    private Integer identifier;
    @Size(max = 50)
    @Column(name = "value", length = 50)
    private String value;
    @Size(max = 500)
    @Column(name = "display_label", length = 500)
    private String displayLabel;
    @JoinColumn(name = "question", referencedColumnName = "ref")
    @ManyToOne
    private Question question;

    public Possiblevalues() {
    }

    public Possiblevalues(Integer identifier) {
        this.identifier = identifier;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplayLabel() {
        return displayLabel;
    }

    public void setDisplayLabel(String displayLabel) {
        this.displayLabel = displayLabel;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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
        if (!(object instanceof Possiblevalues)) {
            return false;
        }
        Possiblevalues other = (Possiblevalues) object;
        if ((this.identifier == null && other.identifier != null) || (this.identifier != null && !this.identifier.equals(other.identifier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vermeg.insuranceproducts.entities.Possiblevalues[ identifier=" + identifier + " ]";
    }
    
}
