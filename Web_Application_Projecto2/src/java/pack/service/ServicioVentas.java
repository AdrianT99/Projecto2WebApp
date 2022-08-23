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

@ManagedBean(name = "ventasService")
@ApplicationScoped
public class ServicioVentas extends Servicio implements Serializable {

    List<VentasTO> ventas = demeVentas();

    public ServicioVentas() {
    }
    
    public void insertarVenta(VentasTO ventasTO) {

        PreparedStatement ps = null;

        try {
            Connection conn = super.getConexion();
            ps = conn.prepareStatement("INSERT INTO VENTAS(IDUSER, FECHA, TOTALPAGAR) VALUES(?,?,?)");
            ps.setInt(1, ventasTO.getUser());
            ps.setString(2, ventasTO.getFecha());
            ps.setDouble(3, ventasTO.getTotalPagar());
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
    public void construirProductos() {
        demeVentas();
    }

    public List<VentasTO> demeVentas() {
        
        List<VentasTO> listaRetorno = new ArrayList<>();
        
        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Paso 3 (Preparar)
            //super.conectar();
            ps = conn.prepareStatement("SELECT IDVENTA,IDUSER,FECHA,TOTALPAGAR FROM VENTAS");
            rs = ps.executeQuery();

            //Paso 4 (Ejectuar)
            while (rs.next()) {

                int IDVENTA = rs.getInt("IDVENTA");
                int IDUSER = rs.getInt("IDUSER");
                String FECHA = rs.getString("FECHA");
                double TOTALPAGAR = rs.getInt("TOTALPAGAR");

                VentasTO ventasTO = new VentasTO();
                ventasTO.setIdVenta(IDVENTA);
                ventasTO.setUser(IDUSER);
                ventasTO.setFecha(FECHA);
                ventasTO.setTotalPagar(TOTALPAGAR);

                listaRetorno.add(ventasTO);

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

    public int demeIdVenta() {

        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int max_Venta = 0;

        try {

            ps = conn.prepareStatement("SELECT MAX(idVenta) as max_Venta FROM ventas");
            rs = ps.executeQuery();

            if (rs.next()) {

                max_Venta = rs.getInt("max_Venta");

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
        return max_Venta;
    }

    public void eliminarVenta(int idVenta) {

        Connection conn = super.getConexion();
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement("DELETE FROM VENTAS WHERE IDVENTA = ?");
            ps.setInt(1, idVenta);
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

    public List<VentasTO> getProducts() {
        return ventas;
    }

}
