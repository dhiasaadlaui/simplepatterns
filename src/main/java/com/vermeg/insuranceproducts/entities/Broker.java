/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vermeg.insuranceproducts.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mdsaadlaoui
 */
@Entity
@Table(name = "broker", catalog = "insurance", schema = "")
@XmlRootElement
@NamedQueries({

    @NamedQuery(name = "Broker.findAll", query = "SELECT b FROM Broker b")
    , @NamedQuery(name = "Broker.findByIdentifier", query = "SELECT b FROM Broker b WHERE b.identifier = :identifier")
    , @NamedQuery(name = "Broker.findByFullName", query = "SELECT b FROM Broker b WHERE b.fullName = :fullName")
    , @NamedQuery(name = "Broker.findByUsername", query = "SELECT b FROM Broker b WHERE b.username = :username")})

public class Broker implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identifier", nullable = false)
    private Integer identifier;
    @Size(max = 50)
    @Column(name = "full_name", length = 50)
    private String fullName;
    @Size(max = 50)
    @Column(name = "username", length = 50)
    private String username;
    @JoinColumn(name = "role", referencedColumnName = "name")
    @ManyToOne
    private Role role;
    @OneToMany(mappedBy = "broker")
    private List<Policy> policyList;

    public Broker() {
    }

    public Broker(Integer identifier) {
        this.identifier = identifier;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @XmlTransient
    public List<Policy> getPolicyList() {
        return policyList;
    }

    public void setPolicyList(List<Policy> policyList) {
        this.policyList = policyList;
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
        if (!(object instanceof Broker)) {
            return false;
        }
        Broker other = (Broker) object;
        if ((this.identifier == null && other.identifier != null) || (this.identifier != null && !this.identifier.equals(other.identifier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return username;
    }
    
}
