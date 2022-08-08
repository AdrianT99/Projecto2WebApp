
package pack.service;

import java.io.Serializable;


public class DetalleProductoTO implements Serializable{
    int idProducto;
    int cantidad;
    int costoUnitario;
    String descripcion;
    int total;
    
    public DetalleProductoTO() {
    }

    public DetalleProductoTO(int idProducto, int cantidad, String descripcion, int total) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.total = total;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(int costoUnitario) {
        this.costoUnitario = costoUnitario;
    }
    
    
    
    
}
