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
import java.util.ArrayList;
import java.util.List;

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
                ventasTO.setTotalPagar(monto);
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
    
    

     public void InsertarDetalle(List<DetalleVentaTO> ventasTO) {
        PreparedStatement ps = null;
        Connection conn = super.getConexion();
                ResultSet rs = null;

        try {
            ps = conn.prepareStatement("select max(idventa) from ventas");
            rs = ps.executeQuery();
            
            int num= 0 ;
            if(rs.next()){
                num = rs.getInt("max(idventa)");
            }
            if(num==0){
                num=1;
            }
            num++;
            for(DetalleVentaTO x:ventasTO){
            ps = conn.prepareStatement("INSERT INTO detalleventa(idVenta,NombreProducto,Precio) VALUES(?,?,?)");
            ps.setInt(1, num);
            ps.setDouble(3, x.getPrecio());
            ps.setString(2, x.getNombre_Producto());
            ps.execute();
            }
//            ps.setInt(1, ventasTO.getIdVenta());
//            ps.setString(1, ventasTO.getFecha());
//            ps.setString(3, String.valueOf(ventasTO.getNum_tarjeta()));
//            ps.setString(4, ventasTO.getVencimiento());
//            ps.setInt(5, ventasTO.getCvv());

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
     
     public void Generar_Monto(){
         
     }
    

    public void insertarventa(VentasTO ventasTO) {
        PreparedStatement ps = null;
        Connection conn = super.getConexion();

        try {
            ps = conn.prepareStatement("INSERT INTO ventas(fecha,monto,user) VALUES(NOW(),?,?)");
            ps.setDouble(1, ventasTO.getTotalPagar());
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
