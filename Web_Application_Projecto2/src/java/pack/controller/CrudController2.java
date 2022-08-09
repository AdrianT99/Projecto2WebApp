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
import pack.service.ClienteTO;
import pack.service.ServicioCliente;

@ManagedBean(name = "crudView2")
@ViewScoped
public class CrudController2 implements Serializable {

    private ClienteTO selectedCliente = new ClienteTO();

    @ManagedProperty("#{clienteService}")
    private ServicioCliente servicioCliente;

    private List<ClienteTO> clientes = new ArrayList();

    public CrudController2() {
    }

    @PostConstruct
    public void init() {
        this.clientes = servicioCliente.getClientes();
    }

    public void openNew() {
        this.selectedCliente = new ClienteTO();
    }

    public void saveProduct() {
        if (this.selectedCliente.getIdUser()== 0) {
            servicioCliente.insertarCliente(selectedCliente);
            //this.products.add(this.);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Client Added"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Client Updated"));
        }
        this.init();

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public ClienteTO getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(ClienteTO selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public ServicioCliente getServicioCliente() {
        return servicioCliente;
    }

    public void setServicioCliente(ServicioCliente servicioCliente) {
        this.servicioCliente = servicioCliente;
    }

    public List<ClienteTO> getClientes() {
        return clientes;
    }

}
