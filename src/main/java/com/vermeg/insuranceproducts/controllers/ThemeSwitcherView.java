/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vermeg.insuranceproducts.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

/**
 *
 * @author mdsaadlaoui
 */
@Named
@SessionScoped
public class ThemeSwitcherView implements Serializable {
 
    private List<String> themes = Arrays.asList(new String[]{"afterdark","afternoon","afterwork","black-tie","blitzer","bluesky","bootstrap","casablanca","cruze","cupertino","dark-hive","delta","dot-luv","eggplant","excite-bike","flick","glass-x","hot-sneaks","humanity","le-frog","midnight","mint-choc","overcast","pepper-grinder","redmond","rocket","sam","smoothness","south-street","start","sunny","swanky-purse","trontastic","ui-darkness","ui-lightness","vader","home"});
    private String theme="start";

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @PostConstruct
    public void init() {
     
    }
     
    public List<String> getThemes() {
        return themes;
    } 
 
}