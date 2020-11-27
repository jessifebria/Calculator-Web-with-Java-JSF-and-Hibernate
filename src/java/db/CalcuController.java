/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
//import javax.annotation.ManagedBean;

/**
 *
 * @author x.x
 */
@ManagedBean(name = "cal", eager = true)
@SessionScoped
public class CalcuController {

    private String angka1 = "";
    private String angka2 = "";
    private String hitung = "-";
    private String hasil = "-";

    public String getAngka1() {
        return angka1;
    }

    public void setAngka1(String angka1) {
        this.angka1 = angka1;
    }

    public String getAngka2() {
        return angka2;
    }

    public void setAngka2(String angka2) {
        this.angka2 = angka2;
    }

    public String getHitung() {
        return hitung;
    }

    public void setHitung(String hitung) {
        this.hitung = hitung;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public void clicked(ActionEvent event) {
        String buttonId = event.getComponent().getId();
        Float a = null, b = null;
        try {
            a = Float.valueOf(angka1);
            b = Float.valueOf(angka2);
            if (buttonId.equalsIgnoreCase("btn1")) {
                setHitung(angka1 + " + " + angka2);
                setHasil(String.valueOf(a + b));
            } else if (buttonId.equalsIgnoreCase("btn2")) {
                setHitung(angka1 + " - " + angka2);
                setHasil(String.valueOf(a - b));
            } else if (buttonId.equalsIgnoreCase("btn3")) {
                setHitung(angka1 + " * " + angka2);
                setHasil(String.valueOf(a * b));
            } else if (buttonId.equalsIgnoreCase("btn4")) {
                setHitung(angka1 + " / " + angka2);
                setHasil(String.valueOf(a / b));
            } else if (buttonId.equalsIgnoreCase("btn5")) {
                setHitung(angka1 + " ^ " + angka2);
                setHasil(String.valueOf(Math.pow(a, b)));
            } else if (buttonId.equalsIgnoreCase("btn6")) {
                setHitung(angka1 + "! , " + angka2 + "!");
                setHasil(String.valueOf(faktorial(Integer.parseInt(angka1)) + ", " + faktorial(Integer.parseInt(angka2))));
            } else if (buttonId.equalsIgnoreCase("btn7")) {
                setHitung("√" + angka1 + " , √" + angka2);
                setHasil(String.valueOf(Math.sqrt(a) + ", " + Math.sqrt(b)));
            } else if (buttonId.equalsIgnoreCase("btn8")) {
                setHitung("3√" + angka1 + " , 3√" + angka2);
                setHasil(String.valueOf(Math.cbrt(a) + ", " + Math.cbrt(b)));
            }
        } catch (NumberFormatException e) {
            setHitung("Masukkan Angka, Bukan String!");
            setHasil("Error");
        }

    }

    public int faktorial(int a) {
        int result = 1;
        for (int i = 1; i <= a; i++) {
            result *= i;
        }
        return result;
    }

}
