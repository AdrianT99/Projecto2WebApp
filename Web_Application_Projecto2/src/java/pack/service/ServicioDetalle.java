
package pack.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "detalleService")
@ApplicationScoped
public class ServicioDetalle extends Servicio implements Serializable{
    

    
    public void insertarDetalle(DetalleProductoTO detalleProductoTO) {

        PreparedStatement ps = null;

        try {
            Connection conn = super.getConexion();
            ps = conn.prepareStatement("INSERT INTO DETALLEPRODUCTO(IDPRODUCTO,CANTIDAD,DESCRIPCION,COSTOUNITARIO,TOTAL) VALUES(?,?,?,?,?)");
            ps.setInt(1, detalleProductoTO.getIdProducto());
            ps.setInt(2, detalleProductoTO.getCantidad());
            ps.setString(3, detalleProductoTO.getDescripcion());
            ps.setDouble(4, detalleProductoTO.getCostoUnitario());
            ps.setDouble(5, detalleProductoTO.getTotal());
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
    
    public List<DetalleProductoTO> listaDetalleProducto(int idProducto) {

        List<DetalleProductoTO> listaRetorno = new ArrayList<>();

        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Paso 3 (Preparar)
            //super.conectar();
            ps = conn.prepareStatement("SELECT IDPRODUCTO,CANTIDAD,DESCRIPCION,COSTOUNITARIO,TOTAL FROM DETALLEPRODUCTO WHERE IDPRODUCTO = ?");
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();

            //Paso 4 (Ejectuar)
            while (rs.next()) {
                
                int IDPRODUCTO = rs.getInt("IDPRODUCTO");
                int CANTIDAD = rs.getInt("CANTIDAD");
                String DESCRIPCION = rs.getString("DESCRIPCION");
                double COSTOUNITARIO = rs.getDouble("COSTOUNITARIO");
                double TOTAL = rs.getDouble("TOTAL");

                 DetalleProductoTO detalleProductoTO = new DetalleProductoTO();
                detalleProductoTO.setIdProducto(IDPRODUCTO);
                detalleProductoTO.setCantidad(CANTIDAD);
                detalleProductoTO.setDescripcion(DESCRIPCION);
                detalleProductoTO.setCostoUnitario(COSTOUNITARIO);
                detalleProductoTO.setTotal(TOTAL);

                listaRetorno.add(detalleProductoTO);

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
    
}
