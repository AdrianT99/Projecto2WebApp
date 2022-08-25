/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import pack.service.ProductoTO;

@ManagedBean(name = "EditarProductoController")
@ViewScoped
public class editarProductoController {
    int idProducto;
    String nombreProducto;
    double precioVenta;
    byte[] imagen;
    double costoTotal;
    String tipo;
    String descripcion;
    
    ProductoTO selectedProduct;
    CrudController crudController;

    @ManagedProperty("#{crudView}")
    private CrudController crudView;

    @ManagedProperty("#{Producto}")
    private ProductoTO productoTO;
    
    
    @PostConstruct
    public void init() {
        this.productoTO = crudView.getProductoEditable();

    }
    public editarProductoController() {
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

    public ProductoTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductoTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public CrudController getCrudController() {
        return crudController;
    }

    public void setCrudController(CrudController crudController) {
        this.crudController = crudController;
    }

    public CrudController getCrudView() {
        return crudView;
    }

    public void setCrudView(CrudController crudView) {
        this.crudView = crudView;
    }

    public ProductoTO getProductoTO() {
        return productoTO;
    }

    public void setProductoTO(ProductoTO productoTO) {
        this.productoTO = productoTO;
    }

    
}
