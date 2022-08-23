/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author XPC
 */
public class ServicioDetalleVenta extends Servicio implements Serializable{
    
    public void insertarDetalleVenta(DetalleVentaTO detalleVentaTO) {

        PreparedStatement ps = null;

        try {
            Connection conn = super.getConexion();
            ps = conn.prepareStatement("INSERT INTO DETALLEVENTA(IDVENTA,NOMBREPRODUCTO,DESCRIPCION,PRECIO) VALUES(?,?,?,?)");
            ps.setInt(1, detalleVentaTO.getIdventa());
            ps.setString(2, detalleVentaTO.getNombre_Producto());
            ps.setString(3, detalleVentaTO.getDescripcion());
            ps.setDouble(4, detalleVentaTO.getPrecio());
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
