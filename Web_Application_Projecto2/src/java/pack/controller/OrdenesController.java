package pack.controller;

import pack.service.OrdenTO;
import pack.service.ServicioOrden;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ordenController")
@ViewScoped
public class OrdenesController implements Serializable {

    private List<OrdenTO> listaOrdenes = new ArrayList<>();
    
    @ManagedProperty("#{loginController}")
    private LoginController loginController;

    public List<OrdenTO> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setListaOrdenes(List<OrdenTO> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }
@PostConstruct
    public void cargarDatos() {

        ServicioOrden servicioOrden = new ServicioOrden();

        this.listaOrdenes = servicioOrden.demeOrdenesCliente(loginController.getClienteTO().getIdUser());

    }

    public OrdenesController() {
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
    
    

}

