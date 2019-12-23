/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vermeg.insuranceproducts.business;

import com.vermeg.insuranceproducts.entities.Answer;
import com.vermeg.insuranceproducts.entities.Client;
import com.vermeg.insuranceproducts.entities.Policy;
import com.vermeg.insuranceproducts.entities.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.ejb.EJB;

/**
 *
 * @author mdsaadlaoui
 */
public class LifePolicyBuilderImpl implements LifePolicyBuilder {

    private List<Question> questions;
    Policy policy;

    public LifePolicyBuilderImpl() {
        questions = new ArrayList<>();
        policy = new Policy();
    }

    @Override
    public PolicyBuilder setClient(Client client) {
        policy.setClient(client);
        return this;
    }

    @Override
    public PolicyBuilder answer(Answer answer) {
        policy.getAnswerList().add(answer);
        return this;
    }

    @Override
    public PolicyBuilder prepayement(Double amount) {
        this.policy.setCurrentAmount(amount);
        return this;
    }

    @Override
    public Policy build() {
        return policy;
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> lifeQuestions = new ArrayList<>();
        for (Question question : questions) {
            if ("LIFE".equals(question.getProductType())) {
                lifeQuestions.add(question);
            }
        }
        return lifeQuestions;
    }

    @Override
    public PolicyBuilder setQuestions(List<Question> questions) {
        this.questions = questions;
        return this;
    }

}
