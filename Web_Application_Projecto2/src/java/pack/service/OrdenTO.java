
package pack.service;

import java.io.Serializable;


public class OrdenTO implements Serializable {
        
   private String fechaOrden;
   private int idOrden;
   private int idCliente;
   private int montoTotal;
    

    public OrdenTO() {
        this.fechaOrden = fechaOrden;
        this.montoTotal = montoTotal;
        this.idOrden = idOrden;
        this.idCliente = idCliente;
    }
    
    
    public String getFechaOrden() {
        return fechaOrden;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }
        

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }


    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
    }  

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
     
}
