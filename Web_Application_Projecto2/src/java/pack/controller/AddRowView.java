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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.file.UploadedFile;
import pack.service.DetalleProductoTO;
import pack.service.ProductoTO;
import pack.service.ServicioDetalle;
import pack.service.ServicioProducto;

@ManagedBean(name = "CostosController")
@SessionScoped
public class AddRowView implements Serializable {
    
    int costoTotal;
    int margen;
    int precioSugerido;
    int precioFinal;
    int temp;
    String tipo;
    String nombreProducto;
    String descripcion;
    byte[] imagen;
    ProductoTO productoTO = new ProductoTO();
    DetalleProductoTO detalleProductoTO = new DetalleProductoTO();
    ServicioProducto sp = new ServicioProducto();
    ServicioDetalle sd =  new ServicioDetalle();
    DetalleProductoTO selectedDetalle;
     private UploadedFile file;
    @ManagedProperty("#{detalleProducto}")
    
    private List<DetalleProductoTO> detalleProducto;

    @PostConstruct
    public void init() {
        this.detalleProducto = new ArrayList();
    }

    public DetalleProductoTO getSelectedDetalle() {
        return selectedDetalle;
    }

    public void setSelectedDetalle(DetalleProductoTO selectedDetalle) {
        this.selectedDetalle = selectedDetalle;
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

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ProductoTO getProductoTO() {
        return productoTO;
    }

    public void setProductoTO(ProductoTO productoTO) {
        this.productoTO = productoTO;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    
    
      public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void insertarProducto()
    {
        productoTO = new ProductoTO();
        productoTO.setNombreProducto(nombreProducto);
        productoTO.setTipo(tipo);
        productoTO.setPrecioVenta(precioFinal);
        productoTO.setCostoTotal(costoTotal);
        productoTO.setTipo(tipo);
        productoTO.setDescripcion(descripcion);
        productoTO.setImagen(imagen);
        setProductoTO(productoTO);
        
        sp.insertarProducto(productoTO);
        insertarDetalles();
        detalleProducto.clear();
    }
    
    public void insertarDetalles()
    {
        int idPro=sp.demeIdProducto();
        for(DetalleProductoTO selectedDetalle : detalleProducto)
        {
            selectedDetalle.setIdProducto(idPro);
            selectedDetalle.setCantidad(selectedDetalle.getCantidad());
            selectedDetalle.setDescripcion(selectedDetalle.getDescripcion());
            selectedDetalle.setCostoUnitario(selectedDetalle.getCostoUnitario());
            selectedDetalle.setTotal(selectedDetalle.getTotal());
            setSelectedDetalle(selectedDetalle);
            sd.insertarDetalle(selectedDetalle);
        }
        
    }
    public void demeDetalles()
    {
        int idProducto = selectedDetalle.getIdProducto();
        
        setDetalleProducto(sd.listaDetalleProducto(idProducto));
    }

    public List<DetalleProductoTO> getDetalleProducto() {
        return detalleProducto;
    }

    public void setDetalleProducto(List<DetalleProductoTO> detalleProducto) {
        this.detalleProducto = detalleProducto;
    }

    public void onRowEdit(RowEditEvent<DetalleProductoTO> event) {
        
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
        // Add one new product to the table:
        selectedDetalle = new DetalleProductoTO(0,0,"",0);
        detalleProducto.add(selectedDetalle);

    }
    
     public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            this.imagen = file.getContent();
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
     
     public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public int sumaCostoTotal() {

        costoTotal = 0;

        for (DetalleProductoTO selectedDetalle : this.detalleProducto) {

            temp = (int) selectedDetalle.getTotal();
            costoTotal += temp;
        }
        return costoTotal;
    }

    public void calcularPrecioSugerido() {

        this.precioSugerido = ((costoTotal * margen) / 100) + costoTotal;
    }
    
    public void mensaje()
    {
        ProductoTO p=this.productoTO;
        System.out.println("Nombre "+p.getNombreProducto()+" Tipo: "+p.getTipo()+" Descripcion: "+p.getDescripcion());
    }

}