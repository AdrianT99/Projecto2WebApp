package pack.controller;

import pack.service.ProductoTO;
import pack.service.ServicioProducto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import pack.service.DetalleProductoTO;
import pack.service.ServicioDetalle;

@ManagedBean(name = "crudView")
@ApplicationScoped
public class CrudController implements Serializable {

    private ProductoTO selectedProduct = new ProductoTO();
    private ProductoTO productoEditable = getSelectedProduct();

    @ManagedProperty("#{productService}")
    private ServicioProducto servicioProducto;
    
    @ManagedProperty("#{detalleService}")
    private ServicioDetalle servicioDetalle;
    
  

    private List<ProductoTO> products = new ArrayList();
    private List<DetalleProductoTO> detalles = new ArrayList();

    int idProducto;
    String nombreProducto;
    double precioVenta;
    byte[] imagen;
    double costoTotal;
    String tipo;
    String descripcion;
    int estado;
    
    
 
    
    public CrudController() {
    }

    @PostConstruct
    public void init() {
        this.products = servicioProducto.demeProductos();
      
    }

    public void openNew() {
        this.selectedProduct = new ProductoTO();
    }
    
    
    public void saveProduct() {
        if (this.selectedProduct.getIdProducto() == 0) {
           
            //this.products.add(this.);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        } else {
            servicioProducto.actualizarProducto(selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }
        this.init();

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }


    public ProductoTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductoTO selectedProduct) {
        this.selectedProduct = selectedProduct;
     
    }

    public ServicioProducto getServicioProducto() {
        return servicioProducto;
    }

    public void setServicioProducto(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    public List<ProductoTO> getProducts() {
        return products;
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

    public ProductoTO getProductoEditable() {
        return productoEditable;
    }

    public void setProductoEditable(ProductoTO productoEditable) {
        this.productoEditable = productoEditable;
    }

    public ServicioDetalle getServicioDetalle() {
        return servicioDetalle;
    }

    public void setServicioDetalle(ServicioDetalle servicioDetalle) {
        this.servicioDetalle = servicioDetalle;
    }

    public List<DetalleProductoTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleProductoTO> detalles) {
        this.detalles = detalles;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

  
    
    public void actualizarProducto(){
        servicioProducto.actualizarProducto(selectedProduct);
    }
    
   
}
