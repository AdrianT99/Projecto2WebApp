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

@ManagedBean(name = "crudView")
@ViewScoped
public class CrudController implements Serializable {

    private ProductoTO selectedProduct = new ProductoTO();

    @ManagedProperty("#{productService}")
    private ServicioProducto servicioProducto;

    private List<ProductoTO> products = new ArrayList();

    public CrudController() {
    }

    @PostConstruct
    public void init() {
        this.products = servicioProducto.getProducts();
    }

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

}
