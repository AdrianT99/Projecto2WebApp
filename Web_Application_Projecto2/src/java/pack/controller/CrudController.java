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
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "crudView")
@ApplicationScoped
public class CrudController implements Serializable {

    private ProductoTO selectedProduct = new ProductoTO();
    private ProductoTO productoEditable= getSelectedProduct();

    @ManagedProperty("#{productService}")
    private ServicioProducto servicioProducto;

    private List<ProductoTO> products = new ArrayList();

    int idProducto;
    String nombreProducto;
    double precioVenta;
    byte[] imagen;
    double costoTotal;
    String tipo;
    String descripcion;
    
    
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
    
    public void saveProductsCart() {
       servicioProducto.insertarCarrito(selectedProduct);
        
        //this.products.add(this.);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Agregado al Carrito"));

        this.init();

        PrimeFaces.current().executeScript("PF('ConfirmarAgregar').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");

    }

    public void deleteProductsCart() {
        servicioProducto.deleteCarrito(this.selectedProduct);
        this.selectedProduct = null;        
        PrimeFaces.current().executeScript("PF('deleteProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        servicioProducto.sumaCostoTotal();

    }

    public ProductoTO getProductoEditable() {
        return productoEditable;
    }

    public void setProductoEditable(ProductoTO productoEditable) {
        this.productoEditable = productoEditable;
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
    
    
    
    

}
