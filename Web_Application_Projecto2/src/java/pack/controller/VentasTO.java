/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack.controller;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author njoji
 */
public class VentasTO implements Serializable {
    
    private int idVenta, user,cvv;
    private String num_tarjeta;
    private String vencimiento;
    private double monto;
    private String fecha;
    
    public VentasTO(){
    }

    public VentasTO(int idVenta, int user, String num_tarjeta, String vencimiento, int cvv, double monto, String fecha) {
        this.idVenta = idVenta;
        this.user = user;
        this.num_tarjeta = num_tarjeta;
        this.vencimiento = vencimiento;
        this.cvv = cvv;
        this.monto = monto;
        this.fecha = fecha;
    }
    
    

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getNum_tarjeta() {
        return num_tarjeta;
    }

    public void setNum_tarjeta(String num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
