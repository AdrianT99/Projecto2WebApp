
package pack.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author adria
 */
public class ServicioDetalle extends Servicio implements Serializable{
    
    public void insertarDetalle(DetalleProductoTO detalleProductoTO) {

        PreparedStatement ps = null;

        try {
            Connection conn = super.getConexion();
            ps = conn.prepareStatement("INSERT INTO DETALLEPRODUCTO(CANTIDAD,DESCRIPCION,COSTOUNITARIO,TOTAL) VALUES(?,?,?,?)");
            ps.setInt(1, detalleProductoTO.getCantidad());
            ps.setString(2, detalleProductoTO.getDescripcion());
            ps.setInt(3, detalleProductoTO.getCostoUnitario());
            ps.setInt(4, detalleProductoTO.getTotal());
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
    
    
}
