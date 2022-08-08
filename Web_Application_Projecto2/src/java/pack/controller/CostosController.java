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

@ManagedBean(name = "CostosController")
@ViewScoped
public class CostosController implements Serializable {

    int costoTotal;
    int margen;
    int precioSugerido;
    int precioFinal;
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

    public int getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }

    public int getMargen() {
        return margen;
    }

    public void setMargen(int margen) {
        this.margen = margen;
    }

    public int getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(int precioFinal) {
        this.precioFinal = precioFinal;
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
      

        FacesMessage msg = new FacesMessage("New Product added");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void calcularTotal() {

        for (DetalleProductoTO selectedDetalle : this.detalleProducto) {
            selectedDetalle.setTotal(selectedDetalle.getCantidad() * selectedDetalle.getCostoUnitario());
        }
    }

    public int sumaCostoTotal() {

        int previousSize = detalleProducto.size() - 1;
        int currentSize = detalleProducto.size();

        for (DetalleProductoTO selectedDetalle : this.detalleProducto) {

            if (previousSize != currentSize) {
                temp = selectedDetalle.getTotal();
                costoTotal += temp;
            } else {
                return costoTotal;
            }
        }
        return costoTotal;
    }

    public int calcularPrecioSugerido() {
        precioSugerido = costoTotal * margen / 100 + costoTotal;
        return precioSugerido;
    }

}
