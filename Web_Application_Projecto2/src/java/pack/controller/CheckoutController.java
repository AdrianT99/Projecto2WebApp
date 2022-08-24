package pack.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;
import pack.service.ClienteTO;
import pack.service.DetalleVentaTO;
import pack.service.ProductoTO;
import pack.service.ServicioDetalleVenta;
import pack.service.ServicioProducto;
import pack.service.ServicioVentas;
import pack.service.VentasTO;

@ManagedBean(name = "CheckoutController")
@ViewScoped
public class CheckoutController implements Serializable {

    private String nombre;
    private double costoTotal = 0;

    private List<DetalleVentaTO> detalleVenta;

    DetalleVentaTO detalleVentaTO = new DetalleVentaTO();
    ServicioVentas sv = new ServicioVentas();
    ServicioDetalleVenta sdv = new ServicioDetalleVenta();
    DetalleVentaTO selectedDetalle = new DetalleVentaTO();
    VentasTO ventasTO = new VentasTO();
    ClienteTO activeUser;
    
    ServicioProducto servicioProducto = new ServicioProducto();
    ProductoTO selectedProduct;
    
    @ManagedProperty("#{crudView}")
    private CrudController crudView;

    @ManagedProperty("#{detalleVenta}")
    private List<ProductoTO> productoTO;
    
    @ManagedProperty("#{loginController}")
    private LoginController loginController;
    
    
    @PostConstruct
    public void init() {
        this.productoTO = crudView.getServicioProducto().getCart();
        this.activeUser = this.loginController.getClienteTO();

    }

    public CheckoutController() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public ClienteTO getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(ClienteTO activeUser) {
        this.activeUser = activeUser;
    }

    public ServicioProducto getServicioProducto() {
        return servicioProducto;
    }

    public void setServicioProducto(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    public ProductoTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductoTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<ProductoTO> getProductoTO() {
        return productoTO;
    }

    public void setProductoTO(List<ProductoTO> productoTO) {
        this.productoTO = productoTO;
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

 

    public VentasTO getVentasTO() {
        return ventasTO;
    }

    public void setVentasTO(VentasTO ventasTO) {
        this.ventasTO = ventasTO;
    }

//    public void Salir() {
//
//        loginController.setClienteTO(null);
//        loginController.setPass(null);
//        loginController.setUser(null);
//
//        this.redireccionar("/faces/index.xhtml");
//    }
//
//    public void ingresarCheckOut() {
//
//        this.redireccionar("/faces/Checkout.xhtml");
//
//    }

    public void redireccionar(String ruta) {

        HttpServletRequest request;

        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {

        }
    }

  
    public void onItemSelect(SelectEvent event) {

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected City", event.getObject() + "\n");

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public double sumaCostoTotal() {
        this.costoTotal = 1000;

        return this.costoTotal;
    }

    public void insertarVenta() {
        String fecha = "hoy";
        ventasTO = new VentasTO();
        ventasTO.setUser(activeUser.getIdUser());
        ventasTO.setFecha(fecha);
        ventasTO.setTotalPagar(sumaCostoTotal());

        setVentasTO(ventasTO);

        sv.insertarVenta(ventasTO);
        insertarProductosCarrito();
        detalleVenta.clear();


    }

    public void insertarProductosCarrito() {
        int idVen = sv.demeIdVenta();
        for (ProductoTO selectedProduct : productoTO) {
            DetalleVentaTO selectedDetalle = new DetalleVentaTO();
            selectedDetalle.setIdventa(idVen);
            selectedDetalle.setDescripcion(selectedProduct.getDescripcion());
            selectedDetalle.setPrecio(selectedProduct.getPrecioVenta());
            selectedDetalle.setNombre_Producto(selectedProduct.getNombreProducto());
            setDetalleVentaTO(selectedDetalle);
            sdv.insertarDetalleVenta(selectedDetalle);
        }

    }

    public CrudController getCrudView() {
        return crudView;
    }

    public void setCrudView(CrudController crudView) {
        this.crudView = crudView;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
    
    

}
