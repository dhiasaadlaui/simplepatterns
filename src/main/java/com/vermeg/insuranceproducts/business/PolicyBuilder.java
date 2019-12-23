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
import java.util.List;

/**
 *
 * @author mdsaadlaoui
 */
public interface PolicyBuilder {
    
    List<Question> getQuestions();
    PolicyBuilder setClient(Client client);
    PolicyBuilder answer(Answer answer);
    PolicyBuilder prepayement(Double amount);
    Policy build();
    PolicyBuilder setQuestions(List<Question> questions);
}
