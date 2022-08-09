package pack.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "clienteService")
@ApplicationScoped
public class ServicioCliente extends Servicio implements Serializable {

    List<ClienteTO> clientes = demeClientes();

    public ServicioCliente() {
    }
    
    public void insertarCliente(ClienteTO clienteTO) {

        //TIPO DE USUARIO: EL NUMERO 0 ES PARA CLIENTES ADMINISTRADOR Y EL NUMERO 1 PARA USUARIOS COMUNES, COMPRADORES.
        int tipoCliente = 1;

        PreparedStatement ps = null;

        try {
            Connection conn = super.getConexion();
            ps = conn.prepareStatement("INSERT INTO USER(USER, PASSWORD, TELEFONO, EDAD, TIPOUSUARIO, NOMBREUSUARIO) VALUES(?,?,?,?,?,?)");
            ps.setString(1, clienteTO.getUser());
            ps.setString(2, clienteTO.getPassword());
            ps.setInt(3, clienteTO.getTelefono());
            ps.setInt(4, clienteTO.getEdad());
            ps.setInt(5, tipoCliente);
            ps.setString(6, clienteTO.getNombreUsuario());
            ps.execute();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @PostConstruct
    public void construirClientes() {
        demeClientes();
    }
    
    public List<ClienteTO> demeClientes() {

        List<ClienteTO> listaRetorno = new ArrayList<>();

        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Paso 3 (Preparar)
            //super.conectar();
            ps = conn.prepareStatement("SELECT IDUSER, USER, PASSWORD, TELEFONO, EDAD, TIPOUSUARIO, NOMBREUSUARIO FROM USER");
            rs = ps.executeQuery();

            //Paso 4 (Ejectuar)
            while (rs.next()) {
                
                int idCliente = rs.getInt("IDUSER");
                String user = rs.getString("USER");
                String password = rs.getString("PASSWORD");
                int telefono = rs.getInt("TELEFONO");
                int edad = rs.getInt("EDAD");
                int TIPOUSUARIO = rs.getInt("TIPOUSUARIO");
                String nombre = rs.getString("NOMBREUSUARIO");

                ClienteTO clienteTO = new ClienteTO();
                clienteTO.setIdUser(idCliente);
                clienteTO.setUser(user);
                clienteTO.setPassword(password);
                clienteTO.setTelefono(telefono);
                clienteTO.setEdad(edad);
                clienteTO.setTipoUsuario(TIPOUSUARIO);
                clienteTO.setNombreUsuario(nombre);

                listaRetorno.add(clienteTO);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //Paso 5 (Cerrar)  
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //super.desconectar();

        }
        return listaRetorno;

    }

    public ClienteTO demeCliente(String user, String pass) {

        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String usuario = null;
        ClienteTO clienteTO = null;
        try {

            ps = conn.prepareStatement("SELECT IDUSER,USER,PASSWORD,TELEFONO,EDAD,TIPOUSUARIO FROM user WHERE USER=? AND PASSWORD=?");
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();

            if (rs.next()) {

                clienteTO = new ClienteTO();

                clienteTO.setIdUser(rs.getInt("IDUSER"));
                clienteTO.setUser(rs.getString("USER"));
                clienteTO.setPassword(rs.getString("PASSWORD"));
                clienteTO.setTelefono(rs.getInt("TELEFONO"));
                clienteTO.setEdad(rs.getInt("EDAD"));
                clienteTO.setTipoUsuario(rs.getInt("TIPOUSUARIO"));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //Paso 5 (Cerrar)  
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return clienteTO;
    }

    public int demeContrasena() {

        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int contrasena = 0;

        try {

            ps = conn.prepareStatement("SELECT CEDULA FROM chiquitinas.cliente");
            rs = ps.executeQuery();

            if (rs.next()) {

                contrasena = rs.getInt("CEDULA");

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //Paso 5 (Cerrar)  
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return contrasena;
    }

    public int demeIdCliente() {

        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int maxCliente = 0;

        try {

            ps = conn.prepareStatement("SELECT MAX(idCliente) as max_cliente FROM chiquitinas.cliente");
            rs = ps.executeQuery();

            if (rs.next()) {

                maxCliente = rs.getInt("max_cliente");

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //Paso 5 (Cerrar)  
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return maxCliente;
    }

//    public ClienteTO buscarCliente(int cedulaParam) {
//
//        ClienteTO retorno = new ClienteTO();
//        Connection conn = super.getConexion();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//
//            ps = conn.prepareStatement("SELECT CEDULA, NOMBRE, DIRECCION FROM CLIENTE WHERE CEDULA = ?");
//            ps.setInt(1, cedulaParam);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//
//                int cedula = rs.getInt("CEDULA");
//                String nombre = rs.getString("NOMBRE");
//                String direccion = rs.getString("DIRECCION");
//                int telefono = rs.getInt("TELEFONO");
//
//                ClienteTO clienteTO = new ClienteTO();
//                clienteTO.setCedulaCliente(cedula);
//                clienteTO.setNombreCliente(nombre);
//                clienteTO.setDireccion(direccion);
//                clienteTO.setTelefono(telefono);
//
//                retorno = clienteTO;
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (ps != null && !ps.isClosed()) {
//                    ps.close();
//                }
//                if (rs != null && !rs.isClosed()) {
//                    rs.close();
//                }
//
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return retorno;
//    }
    public void eliminarCliente(int cedulaParam) {

        Connection conn = super.getConexion();
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement("DELETE FROM CLIENTE WHERE CEDULA = ?");
            ps.setInt(1, cedulaParam);
            ps.execute();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public List<ClienteTO> getClientes() {
        return clientes;
    }

}
