/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack.service;

/**
 *
 * @author User1
 */
public class DetalleVentaTO {
    private int idDetalleVenta;
    private int idventa;
    private String descripcion;
    private String Nombre_Producto;
    private double Precio;

    public DetalleVentaTO() {
    }

    public DetalleVentaTO(int idDetalleVenta, int idventa, String descripcion, String Nombre_Producto, double Precio) {
        this.idDetalleVenta = idDetalleVenta;
        this.idventa = idventa;
        this.descripcion = descripcion;
        this.Nombre_Producto = Nombre_Producto;
        this.Precio = Precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public String getNombre_Producto() {
        return Nombre_Producto;
    }

    public void setNombre_Producto(String Nombre_Producto) {
        this.Nombre_Producto = Nombre_Producto;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }
    
    
    
}
