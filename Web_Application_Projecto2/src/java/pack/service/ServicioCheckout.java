/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import pack.controller.VentasTO;

/**
 *
 * @author njoji
 */
public class ServicioCheckout extends Servicio {

    public List<VentasTO> demeVentas() {
        List<VentasTO> listaRetorno = new ArrayList<>();
        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Paso 3 (Preparar)
            //super.conectar();
            ps = conn.prepareStatement("SELECT IDVENTA, FECHA, MONTO, USER FROM VENTAS");
            rs = ps.executeQuery();

            //Paso 4 (Ejectuar)
            while (rs.next()) {
                int idVenta = rs.getInt("IDVENTA");
                String fecha = rs.getString("FECHA");
                double monto = rs.getDouble("MONTO");
                String user = rs.getString("USER");

                VentasTO ventasTO = new VentasTO();
                ventasTO.setIdVenta(idVenta);
                ventasTO.setFecha(fecha);
                ventasTO.setMonto(monto);
                ventasTO.setUser(idVenta);

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

    

    public void insertarCliente(VentasTO ventasTO) {
        PreparedStatement ps = null;
        Connection conn = super.getConexion();

        try {
            ps = conn.prepareStatement("INSERT INTO ventas(fecha,monto,user) VALUES(NOW(),?,?)");
            ps.setDouble(1, ventasTO.getMonto());
            ps.setInt(2, ventasTO.getUser());

//            ps.setInt(1, ventasTO.getIdVenta());
//            ps.setString(1, ventasTO.getFecha());
//            ps.setString(3, String.valueOf(ventasTO.getNum_tarjeta()));
//            ps.setString(4, ventasTO.getVencimiento());
//            ps.setInt(5, ventasTO.getCvv());
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
