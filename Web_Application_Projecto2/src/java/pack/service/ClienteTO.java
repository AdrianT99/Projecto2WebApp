
package pack.service;

import java.io.Serializable;


public class ClienteTO implements Serializable{
    
    private int idUser;
    private String user;
    private String password;
    private String confirmPassword;
    private int telefono;
    private int edad;
    private int tipoUsuario;
    private String nombreUsuario;

    public ClienteTO() {
    }

    public ClienteTO(int idUser, String user, String password, String confirmPassword, int telefono, int edad, int tipoUsuario, String nombreUsuario) {
        this.idUser = idUser;
        this.user = user;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.telefono = telefono;
        this.edad = edad;
        this.tipoUsuario = tipoUsuario;
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
