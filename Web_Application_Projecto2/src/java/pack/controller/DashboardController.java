package pack.controller;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import pack.service.ServicioDashboard;
import pack.service.ServicioDetalle;

@ManagedBean(name = "DashboardController")
@SessionScoped
public class DashboardController implements Serializable {
    
    private String hora;
    private int Usuarios;
    private int UsuariosAdmin;
    CrudController crudController = new CrudController();
    
    @ManagedProperty("#{loginController}")
    private LoginController loginController;
    

    public String getHora() {
        return hora;
    }

    public int getUsuarios() {
        return Usuarios;
    }

    public int getUsuariosAdmin() {
        return UsuariosAdmin;
    }
    
    public void Salir(){
        
        loginController.setClienteTO(null);
        loginController.setPass(null);
        loginController.setUser(null);
        
        this.redireccionar("/faces/index.xhtml");
    }
    
    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }


    public DashboardController() {
        LocalDateTime myObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myObj.format(myFormatObj);
        this.hora = formattedDate;
        
         Date date = new Date(System.currentTimeMillis());
//         this.hora = date.toString();
//         this.hora = myObj.toString();

ServicioDashboard dash = new ServicioDashboard();
        this.Usuarios = dash.CantUsuarios();
        this.UsuariosAdmin = dash.CantUsuariosAdmin();
    }
    
    public void Catalogo(){
        this.redireccionar("/faces/PaginaProductos2.xhtml");
    }
    
    public void Clientes(){
        this.redireccionar("/faces/PaginaClientes.xhtml");
        
    }
    
    public void Costos(){
         this.redireccionar("/faces/costosCasiSinErrores.xhtml");
    }
    
    public void Carrito(){
         this.redireccionar("/faces/Carrito.xhtml");
    }
    
    public void CheckOut(){
         this.redireccionar("/faces/Checkout.xhtml");
    }
    
    public void EditarDetalle(){
       
        this.redireccionar("/faces/PaginaDetalles.xhtml");
       
    }
    
    
    
     public void redireccionar(String ruta) {

        HttpServletRequest request;

        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
    
}
