package pack.controller;

import pack.service.ClienteTO;
import pack.service.ServicioCliente;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String user;
    private String pass;
    private ClienteTO clienteTO = new ClienteTO();

    public LoginController() {

    }

    public void ingresar() {

        ServicioCliente servicioCliente = new ServicioCliente();
        this.clienteTO = servicioCliente.demeCliente(user, pass);
        if (this.clienteTO != null) {
            if(this.clienteTO.getTipoUsuario() == 0){
                this.redireccionar("/faces/Dashboard.xhtml");
            }else{
                
            this.redireccionar("/faces/PaginaProductos.xhtml");
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_WARN, "Campos invalidos", "El usuario o contrasena no son correctos"));
        }
        
    }

    public void redireccionar(String ruta) {

        HttpServletRequest request;

        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {

        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public ClienteTO getClienteTO() {
        return clienteTO;
    }

    public void setClienteTO(ClienteTO clienteTO) {
        this.clienteTO = clienteTO;
    }

  

    
    
    
}
