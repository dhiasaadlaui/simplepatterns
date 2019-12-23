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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mdsaadlaoui
 */
@Entity
@Table(name = "question", catalog = "insurance", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
    , @NamedQuery(name = "Question.findByRef", query = "SELECT q FROM Question q WHERE q.ref = :ref")
    , @NamedQuery(name = "Question.findByProductType", query = "SELECT q FROM Question q WHERE q.productType = :productType")
    , @NamedQuery(name = "Question.findByAnswerType", query = "SELECT q FROM Question q WHERE q.answerType = :answerType")
    , @NamedQuery(name = "Question.findByExpression", query = "SELECT q FROM Question q WHERE q.expression = :expression")})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ref", nullable = false, length = 50)
    private String ref;
    @Size(max = 50)
    @Column(name = "product_type", length = 50)
    private String productType;
    @Size(max = 50)
    @Column(name = "answer_type", length = 50)
    private String answerType;
    @Size(max = 1000)
    @Column(name = "expression", length = 1000)
    private String expression;
    @OneToMany(mappedBy = "question")
    private List<Answer> answerList;
    @OneToMany(mappedBy = "question")
    private List<Possiblevalues> possiblevaluesList;

    public Question() {
    }

    public Question(String ref) {
        this.ref = ref;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @XmlTransient
    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    @XmlTransient
    public List<Possiblevalues> getPossiblevaluesList() {
        return possiblevaluesList;
    }

    public void setPossiblevaluesList(List<Possiblevalues> possiblevaluesList) {
        this.possiblevaluesList = possiblevaluesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ref != null ? ref.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.ref == null && other.ref != null) || (this.ref != null && !this.ref.equals(other.ref))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ref;
    }
    
}
