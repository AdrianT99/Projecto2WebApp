package pack.controller;

import pack.service.ProductoTO;
import pack.service.ServicioProducto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import pack.service.ServicioDetalle;

@ManagedBean(name = "crudView")
@ViewScoped
public class CrudController implements Serializable {

    private List<ProductoTO> products = new ArrayList();
    private ProductoTO selectedProduct = new ProductoTO();
    ServicioDetalle sd;
    private ProductoTO productoEdit = new ProductoTO();

    @ManagedProperty("#{productService}")
    private ServicioProducto servicioProducto;
    
//    @ManagedProperty("#{servicioDetalle}")
//    private List<DetalleProductoTO> detalleProductoTO;

    

    public CrudController() {
    }

    @PostConstruct
    public void init() {
        this.products = servicioProducto.getProducts();
//        this.detalleProductoTO = sd.listaDetalleProducto(selectedProduct.getIdProducto());
    }

    public ServicioDetalle getSd() {
        return sd;
    }

    public void setSd(ServicioDetalle sd) {
        this.sd = sd;
    }

//    public List<DetalleProductoTO> getDetalleProductoTO() {
//        return detalleProductoTO;
//    }
//
//    public void setDetalleProductoTO(List<DetalleProductoTO> detalleProductoTO) {
//        this.detalleProductoTO = detalleProductoTO;
//    }

    public void openNew() {
        this.selectedProduct = new ProductoTO();
    }

    public void saveProduct() {
        if (this.selectedProduct.getIdProducto() == 0) {
            servicioProducto.insertarProducto(selectedProduct);
            //this.products.add(this.);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        } else {
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

    public ProductoTO getProductoEdit() {
        return productoEdit;
    }

    public void setProducts(List<ProductoTO> products) {
        this.products = products;
    }

    public void setProductoEdit(ProductoTO productoEdit) {
        this.productoEdit = productoEdit;
    }

    public void editarProducto()
    {
        DashboardController dc = new DashboardController();
        dc.EditarProducto();
    }

}
