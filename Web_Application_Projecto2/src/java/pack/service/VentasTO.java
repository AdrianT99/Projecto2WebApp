/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack.service;

/**
 *
 * @author njoji
 */
public class VentasTO {
    
    private int idVenta, user,cvv;
    private String num_tarjeta;
    private String vencimiento;
    private double totalPagar;
    private String fecha;
    
    public VentasTO(){
    }

    public VentasTO(int idVenta, int user, String num_tarjeta, String vencimiento, int cvv, double totalPagar, String fecha) {
        this.idVenta = idVenta;
        this.user = user;
        this.num_tarjeta = num_tarjeta;
        this.vencimiento = vencimiento;
        this.cvv = cvv;
        this.totalPagar = totalPagar;
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

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
