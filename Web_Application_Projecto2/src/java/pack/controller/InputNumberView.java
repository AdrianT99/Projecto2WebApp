package pack.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pack.service.DetalleProductoTO;
import pack.service.ServicioDetalle;

@ManagedBean(name = "inputNumberView")
@ViewScoped
public class InputNumberView implements Serializable {

    private int cantidad;
    private int costoUnitario;
    private int total;
    private int input4;
    private int input5;
    private int input6;
    private int input7;
    private int input8;
    private int input9;
    private int input10;
    private int input11;
    private int input12;
    private int costoTotal;
    private int margen;
    private int precioSugerido;
    private int precioFinal;

    private DetalleProductoTO detalleProducto = new DetalleProductoTO();

    ServicioDetalle servicioDetalle = new ServicioDetalle();

    public InputNumberView() {
    }

    public InputNumberView(int cantidad, int costoUnitario, int total, int input4, int input5, int input6, int input7, int input8, int input9, int input10, int input11, int input12, int costoTotal,
            int margen, int precioSugerido, int precioFinal) {
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
        this.total = total;
        this.input4 = input4;
        this.input5 = input5;
        this.input6 = input6;
        this.input7 = input7;
        this.input8 = input8;
        this.input9 = input9;
        this.input10 = input10;
        this.input11 = input11;
        this.input12 = input12;
        this.costoTotal = costoTotal;
        this.margen = margen;
        this.precioSugerido = precioSugerido;
        this.precioFinal = precioFinal;
    }

      public DetalleProductoTO getDetalleProducto() {
        return detalleProducto;
    }

    public void setDetalleProducto(DetalleProductoTO detalleProducto) {
        this.detalleProducto = detalleProducto;
    }
    
    
    @PostConstruct
    public void openNew() {
        this.detalleProducto = new DetalleProductoTO();
    }

    public void save() {

       
        
        servicioDetalle.insertarDetalle(detalleProducto);

        FacesMessage msg = new FacesMessage("Producto Insertado!");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(int costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ServicioDetalle getServicioDetalle() {
        return servicioDetalle;
    }

    public void setServicioDetalle(ServicioDetalle servicioDetalle) {
        this.servicioDetalle = servicioDetalle;
    }


    public int getInput4() {
        return input4;
    }

    public void setInput4(int input4) {
        this.input4 = input4;
    }

    public int getInput5() {
        return input5;
    }

    public void setInput5(int input5) {
        this.input5 = input5;
    }

    public int getInput6() {
        return input6;
    }

    public int getInput7() {
        return input7;
    }

    public void setInput7(int input7) {
        this.input7 = input7;
    }

    public int getInput8() {
        return input8;
    }

    public void setInput8(int input8) {
        this.input8 = input8;
    }

    public int getInput9() {
        return input9;
    }

    public void setInput9(int input9) {
        this.input9 = input9;
    }

    public int getInput10() {
        return input10;
    }

    public void setInput10(int input10) {
        this.input10 = input10;
    }

    public int getInput11() {
        return input11;
    }

    public void setInput11(int input11) {
        this.input11 = input11;
    }

    public int getInput12() {
        return input12;
    }

    public void setInput12(int input12) {
        this.input12 = input12;
    }

    public void setInput6(int input6) {
        this.input6 = input6;
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

    public int getPrecioSugerido() {
        return precioSugerido;
    }

    public void setPrecioSugerido(int precioSugerido) {
        this.precioSugerido = precioSugerido;
    }

    public int getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(int precioFinal) {
        this.precioFinal = precioFinal;
    }

    public void calculoCostoTotal() {
        this.total  = detalleProducto.getCantidad() * detalleProducto.getCostoUnitario();
        detalleProducto.setTotal(total);
        this.input6 = input4 * input5;
        this.input9 = input7 * input8;
        this.input12 = input10 * input11;
        this.costoTotal = total + input6 + input9 + input12;
        this.precioSugerido = costoTotal * margen / 100 + costoTotal;
        
    }
    
    public void construccionDetalle() {
        
    }

}
