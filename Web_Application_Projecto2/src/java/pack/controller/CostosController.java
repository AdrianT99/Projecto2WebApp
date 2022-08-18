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
import pack.service.ServicioProducto;

@ManagedBean(name = "CostosController")
@ViewScoped
public class CostosController implements Serializable {

    int costoTotal;
    int margen;
    int precioSugerido;
    int precioFinal;
    int temp;
    String tipo;
    ProductoTO productoTO = new ProductoTO();
    DetalleProductoTO detalleProductoTO = new DetalleProductoTO();
    ServicioProducto sp = new ServicioProducto();
    ServicioDetalle sd =  new ServicioDetalle();
    DetalleProductoTO selectedDetalle;

    @ManagedProperty("#{detalleProducto}")

    private List<DetalleProductoTO> detalleProducto;

    public CostosController() {
    }

    public CostosController(int costoTotal, int margen, int precioSugerido, int precioFinal) {
        this.costoTotal = costoTotal;
        this.margen = margen;
        this.precioSugerido = precioSugerido;
        this.precioFinal = precioFinal;
    }

    @PostConstruct
    public void init() {

        detalleProducto = new ArrayList();   
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

    public int getPrecioSugerido() {
        return precioSugerido;
    }

    public void setPrecioSugerido(int precioSugerido) {
        this.precioSugerido = precioSugerido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void onRowEdit(RowEditEvent<DetalleProductoTO> event) {

        System.out.println(this.detalleProducto.size());
        DetalleProductoTO prodEdit = event.getObject();
        prodEdit.setTotal(prodEdit.getCantidad() * prodEdit.getCostoUnitario());
        this.sumaCostoTotal();
        FacesMessage msg = new FacesMessage("Product Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
   
    }

    public void onRowCancel(RowEditEvent<DetalleProductoTO> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
        
        selectedDetalle = new DetalleProductoTO(0, 0, "", 0);
        detalleProducto.add(selectedDetalle);

        FacesMessage msg = new FacesMessage("New Product added");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public int sumaCostoTotal() {

        costoTotal = 0;

        for (DetalleProductoTO selectedDetalle : this.detalleProducto) {

            temp = selectedDetalle.getTotal();
            costoTotal += temp;
        }
        return costoTotal;
    }

    public void calcularPrecioSugerido() {

        this.precioSugerido = ((costoTotal * margen) / 100) + costoTotal;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public ProductoTO getProductoTO() {
        return productoTO;
    }

    public void setProductoTO(ProductoTO productoTO) {
        
        this.productoTO = productoTO;
    }

    public void insertarProducto()
    {
        productoTO = new ProductoTO();
        productoTO.setPrecioVenta(precioFinal);
        productoTO.setCostoTotal(costoTotal);
        productoTO.setTipo(tipo);
        setProductoTO(productoTO);
        
        sp.insertarProducto(productoTO);
        insertarDetalles();
    }
    
    public void insertarDetalles()
    {
        int idPro=sp.demeIdProducto();
        for(DetalleProductoTO dp : this.detalleProducto)
        {
            detalleProductoTO.setIdProducto(idPro);
            detalleProductoTO.setCantidad(detalleProductoTO.getCantidad());
            detalleProductoTO.setDescripcion(detalleProductoTO.getDescripcion());
            detalleProductoTO.setCostoUnitario(detalleProductoTO.getCostoUnitario());
            detalleProductoTO.setTotal(detalleProductoTO.getTotal());
            this.detalleProductoTO = selectedDetalle;
            sd.insertarDetalle(detalleProductoTO);
        }
        
    }
    
    public DetalleProductoTO getSelectedDetalle() {
        return selectedDetalle;
    }

    public void setSelectedDetalle(DetalleProductoTO selectedDetalle) {
        this.selectedDetalle = selectedDetalle;
    }
    
    

}
