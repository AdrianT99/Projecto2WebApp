package pack.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;
import pack.service.ClienteTO;
import pack.service.DetalleVentaTO;
import pack.service.ProductoTO;
import pack.service.ServicioDetalleVenta;
import pack.service.ServicioVentas;
import pack.service.VentasTO;

@ManagedBean(name = "CheckoutController")
@SessionScoped
public class CheckoutController implements Serializable {
    
    private String nombre;
    private double costoTotal = 0;

    private List<DetalleVentaTO> detalleVenta;
    DetalleVentaTO detalleVentaTO = new DetalleVentaTO();
    ServicioVentas sv = new ServicioVentas();
    ServicioDetalleVenta sdv =  new ServicioDetalleVenta();
    DetalleVentaTO selectedDetalle;
    VentasTO ventasTO = new VentasTO();
    ClienteTO activeUser;
    List<ProductoTO> cart = new ArrayList<>();

    @ManagedProperty("#{loginController}")
    private LoginController loginController;

    public CheckoutController() {

    }

    public String getNombre() {
        return loginController.getClienteTO().getNombreUsuario();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DetalleVentaTO getDetalleVentaTO() {
        return detalleVentaTO;
    }

    public void setDetalleVentaTO(DetalleVentaTO detalleVentaTO) {
        this.detalleVentaTO = detalleVentaTO;
    }

    public ServicioVentas getSv() {
        return sv;
    }

    public void setSv(ServicioVentas sv) {
        this.sv = sv;
    }

    public ServicioDetalleVenta getSdv() {
        return sdv;
    }

    public void setSdv(ServicioDetalleVenta sdv) {
        this.sdv = sdv;
    }

    public DetalleVentaTO getSelectedDetalle() {
        return selectedDetalle;
    }

    public void setSelectedDetalle(DetalleVentaTO selectedDetalle) {
        this.selectedDetalle = selectedDetalle;
    }

    

    public void setDetalleVenta(List<DetalleVentaTO> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public VentasTO getVentasTO() {
        return ventasTO;
    }

    public void setVentasTO(VentasTO ventasTO) {
        this.ventasTO = ventasTO;
    }
    
    

    public void Salir() {

        loginController.setClienteTO(null);
        loginController.setPass(null);
        loginController.setUser(null);

        this.redireccionar("/faces/index.xhtml");
    }
    
     public void ingresarCheckOut() {

        this.redireccionar("/faces/Checkout.xhtml");

    }

    public void redireccionar(String ruta) {

        HttpServletRequest request;

        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {

        }
    }


    @PostConstruct
    public void init() {
        this.nombre = this.loginController.getClienteTO().getNombreUsuario();
        this.activeUser = this.loginController.getClienteTO();

    }

    public void onItemSelect(SelectEvent event) {

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected City", event.getObject() + "\n");

        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public double sumaCostoTotal() {
        this.costoTotal = 1000;
        
        return this.costoTotal;
    }
    
    public void insertarVenta()
    {
        String fecha="hoy";
        ventasTO = new VentasTO();
        ventasTO.setUser(activeUser.getIdUser());
        ventasTO.setFecha(fecha);
        ventasTO.setTotalPagar(sumaCostoTotal());
        
        setVentasTO(ventasTO);
        
        sv.insertarVenta(ventasTO);
        insertarProductosCarrito();
        detalleVenta.clear();
        
//        productoTO = new ProductoTO();
//        productoTO.setNombreProducto(nombreProducto);
//        productoTO.setTipo(tipo);
//        productoTO.setPrecioVenta(precioFinal);
//        productoTO.setCostoTotal(costoTotal);
//        productoTO.setTipo(tipo);
//        productoTO.setDescripcion(descripcion);
//        setProductoTO(productoTO);
//        
//        sp.insertarProducto(productoTO);
//        insertarDetalles();
//        detalleProducto.clear();
        
       
    }
    
    public void insertarProductosCarrito()
    {
        int idVen=sv.demeIdVenta();
        for(DetalleVentaTO dv : detalleVenta)
        {
            dv.setIdventa(idVen);
            dv.setNombre_Producto(dv.getNombre_Producto());
            dv.setDescripcion(dv.getDescripcion());
            dv.setPrecio(dv.getPrecio());
            setDetalleVentaTO(dv);
            sdv.insertarDetalleVenta(dv);
        }
        
    }

}