/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author x.x
 */
@ManagedBean(name = "userc", eager = true)
@SessionScoped
public class UserController implements Serializable {

    private UserHelper helper;
    private User user;
    private String email;
    private String password;
    private String secondpassword;
    private String nama;
    private String validation = "";
    private String passwordvalidation;
    private String namavalidation;
    private String emailvalidation;

    public String getPasswordvalidation() {
        if (email != null && password != null && secondpassword != null && nama != null) {
            if (password.length() < 8) {
                return "Password harus lebih dari 8 karakter!";
            } else if (!(password.equalsIgnoreCase(secondpassword))) {
                return "Password tidak sama!";
            }
        }
        return null;
    }

    public void setPasswordvalidation(String passwordvalidation) {
        this.passwordvalidation = passwordvalidation;
    }

    public String getNamavalidation() {
        if (email != null && password != null && secondpassword != null && nama != null) {
            if (nama.indexOf(" ") >= 0) {
                return "Nama tidak boleh ada spasi!";
            }
        }
        return null;
    }

    public void setNamavalidation(String namavalidation) {
        this.namavalidation = namavalidation;
    }

    public String getEmailvalidation() {
        if (email != null && password != null && secondpassword != null && nama != null) {
            if ((email.indexOf(".") - email.indexOf("@") < 1) || (email.indexOf("@") == 0)) {
                return "Email tidak valid!";
            } else if (helper.IsUserExist(email)) {
                return "Sudah ada akun dengan Email ini! Silahkan sign in";
            }
        }
        return null;
    }

    public void setEmailvalidation(String emailvalidation) {
        this.emailvalidation = emailvalidation;
    }

    public String getSecondpassword() {
        return secondpassword;
    }

    public void setSecondpassword(String secondpassword) {
        this.secondpassword = secondpassword;
    }

    public String getValidation() {
        if (email != null && password != null && helper.CheckUser(email, password) == null) {
            return "Login gagal! Email dan Password tidak cocok dalam database!";
        } else {
            return "";
        }
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserController() {
        helper = new UserHelper();
    }

    public String nextpage() {
        user = helper.CheckUser(email, password);
        if (user != null) {
            setNama(user.getNama());
            return "start.xhtml";
        } else {
        }
        return "index.xhtml";
    }

    public String tocalc() {
        return "calc.xhtml";
    }

    public String savemember() {
        try {
            if (getEmailvalidation() == null && getPasswordvalidation() == null && getNamavalidation() == null) {
                helper.SaveUser(new User(nama, email, password));
                return "start.xhtml";
            }
            return "signup.xhtml";
        } catch (Exception e) {
            return "signup.xhtml";
        }
    }

}
