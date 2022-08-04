package pack.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FlowEvent;
import pack.service.ClienteTO;
import pack.service.ServicioCliente;

@ManagedBean(name = "UserWizardController")
@ViewScoped
public class UserWizard implements Serializable {

    private ClienteTO clienteTO = new ClienteTO();

    private boolean skip;

    ServicioCliente servicioCliente = new ServicioCliente();

    public ClienteTO getClienteTO() {
        return clienteTO;
    }

    public void setClienteTO(ClienteTO clienteTO) {
        this.clienteTO = clienteTO;
    }

    @PostConstruct
    public void openNew() {
        this.clienteTO = new ClienteTO();
    }

    public void save() {
        String pass = clienteTO.getPassword();
        String confirmPass = clienteTO.getConfirmPassword();

        if (pass.equals(confirmPass)) {
            servicioCliente.insertarCliente(clienteTO);

            ingresarLogin();

            FacesMessage msg = new FacesMessage("Cuenta creada correctamente!", "Bienvenid@ :" + clienteTO.getNombreUsuario());
            FacesContext.getCurrentInstance().addMessage(null, msg);

            //pendiente el mensaje de confirmacion de la creacion de la cuenta...
        } else {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_WARN, "Campos invalidos", "Las contrasennas no coinciden"));
        }
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false; //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public void ingresarRegistro() {

        this.redireccionar("/faces/RegistoUsuario.xhtml");

    }

    public void ingresarLogin() {

        this.redireccionar("/faces/index.xhtml");

    }

    public void redireccionar(String ruta) {

        HttpServletRequest request;

        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {

        }
    }
}
