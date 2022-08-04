
package pack.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import pack.service.DetalleProductoTO;
import pack.service.ProductoTO;
import pack.service.ServicioDetalle;

@ManagedBean(name ="CostosController")
@ViewScoped
public class CostosController implements Serializable {
        
     int costoTotal;
     int temp;
     DetalleProductoTO selectedDetalle;
     

    @ManagedProperty("#{detalleProducto}")
   
    private List<DetalleProductoTO> detalleProducto;

    public CostosController() {
    }
    
    

    @PostConstruct
    public void init() {
        
        this.detalleProducto = new ArrayList();
        onAddNew();
        
    }
    

    public List<DetalleProductoTO> getdetalleProducto() {
        return detalleProducto;
    }

    

    public List<DetalleProductoTO> getDetalleProducto() {
        return detalleProducto;
    }

    public void setDetalleProducto(List<DetalleProductoTO> detalleProducto) {
        this.detalleProducto = detalleProducto;
    }

    
  
    

    public void onRowEdit(RowEditEvent<DetalleProductoTO> event) {
        
        
        FacesMessage msg = new FacesMessage("Product Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<DetalleProductoTO> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
        // Add one new product to the table:
        this.selectedDetalle = new DetalleProductoTO();
        selectedDetalle.setCantidad(0);
        selectedDetalle.setCostoUnitario(0);
        selectedDetalle.setDescripcion("");
        selectedDetalle.setTotal(0);
        detalleProducto.add(selectedDetalle);
        System.out.println(detalleProducto.size());

        FacesMessage msg = new FacesMessage("New Product added");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    public void calcularTotal(){
        
        for(DetalleProductoTO selectedDetalle : this.detalleProducto) {
        selectedDetalle.setTotal(selectedDetalle.getCantidad() * selectedDetalle.getCostoUnitario());
        } 
    }
    
    public void sumaCostoTal() {
        
        for(DetalleProductoTO selectedDetalle : this.detalleProducto) {
            
            temp = selectedDetalle.getTotal() + selectedDetalle.getTotal();
            costoTotal += temp;
        }
        
    }
    
}
