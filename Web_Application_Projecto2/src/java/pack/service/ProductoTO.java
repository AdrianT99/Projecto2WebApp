
package pack.service;

import java.io.Serializable;


public class ProductoTO implements Serializable {
    
    int idProducto;
    String nombreProducto;
    double precioVenta;
    byte[] imagen;
    double costoTotal;
    String tipo;
    String descripcion;
    
   
    public ProductoTO() {
   
    }

    public ProductoTO(int idProducto, String nombreProducto, double precioVenta, byte[] imagen, double costoTotal, String tipo, String descripcion) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioVenta = precioVenta;
        this.imagen = imagen;
        this.costoTotal = costoTotal;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   
}
