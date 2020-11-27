/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
//import javax.annotation.ManagedBean;

/**
 *
 * @author x.x
 */
@ManagedBean(name = "userp", eager = true)
@ViewScoped
public class UserParser {

    private String nama;
    
    @ManagedProperty("#{userc}")
    private UserController userc;

    @PostConstruct
    public void init() {
    }

    public String getNama() {
        return userc.getNama();
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setUserc(UserController userc) {
        this.userc = userc;
    }

  
}
