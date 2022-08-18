
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
            ps = conn.prepareStatement("INSERT INTO DETALLEPRODUCTO(IDPRODUCTO,CANTIDAD,DESCRIPCION,COSTOUNITARIO,TOTAL) VALUES(?,?,?,?,?)");
            ps.setInt(1, detalleProductoTO.getIdProducto());
            ps.setInt(2, detalleProductoTO.getCantidad());
            ps.setString(3, detalleProductoTO.getDescripcion());
            ps.setInt(4, detalleProductoTO.getCostoUnitario());
            ps.setInt(5, detalleProductoTO.getTotal());
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
