
package pack.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ServicioOrden extends Servicio {
    public List<OrdenTO> demeOrdenes() {
        List<OrdenTO> listaRetorno = new ArrayList<>();
        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Paso 3 (Preparar)
            //super.conectar();
            ps = conn.prepareStatement("SELECT IDORDEN,FECHA, MONTO, IDCLIENTE FROM ORDENES");
            rs = ps.executeQuery();

            //Paso 4 (Ejectuar)
            while (rs.next()) {

                int idOrden = rs.getInt("IDORDEN");
                String fecha = rs.getString("FECHA");
                int monto = rs.getInt("MONTO");
                int idCliente = rs.getInt("IDCLIENTE");
             

                OrdenTO ordenTO = new OrdenTO();
                ordenTO.setIdOrden(idOrden);
                ordenTO.setFechaOrden(fecha);
                ordenTO.setMontoTotal(monto);
                ordenTO.setIdCliente(idCliente);
               
                listaRetorno.add(ordenTO);
            
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
    
    public List<OrdenTO> demeOrdenesCliente(int idCliente) {
        List<OrdenTO> listaRetorno = new ArrayList<>();
        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Paso 3 (Preparar)
            //super.conectar();
            ps = conn.prepareStatement("SELECT IDORDEN,FECHA, MONTO, IDCLIENTE FROM ORDENES WHERE IDCLIENTE = ?");
            ps.setInt(1,idCliente);
            rs = ps.executeQuery();

            //Paso 4 (Ejectuar)
            while (rs.next()) {

                int idOrden = rs.getInt("IDORDEN");
                String fecha = rs.getString("FECHA");
                int monto = rs.getInt("MONTO");
                int idClienteDato = rs.getInt("IDCLIENTE");
             

                OrdenTO ordenTO = new OrdenTO();
                ordenTO.setIdOrden(idOrden);
                ordenTO.setFechaOrden(fecha);
                ordenTO.setMontoTotal(monto);
                ordenTO.setIdCliente(idClienteDato);
               
                listaRetorno.add(ordenTO);
            
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
    
    public int demeIdOrden() {
    
        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;       
        int maxOrden = 0;
        
          try {
           
            ps = conn.prepareStatement("SELECT MAX(idOrden) as max_orden FROM chiquitinas");
            rs = ps.executeQuery();

     
            if (rs.next()) {

            maxOrden = rs.getInt("max_orden");
                
                      
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
        return maxOrden;       
}
    

    public void insertarOrden(OrdenTO ordenTO) {

        PreparedStatement ps = null;

        try {
            Connection conn = super.getConexion();
            ps = conn.prepareStatement("INSERT INTO ORDENES(FECHA, MONTO, IDCLIENTE) VALUES(?,?,?)");
            ps.setString(1, ordenTO.getFechaOrden());
            ps.setInt(2, ordenTO.getMontoTotal());
            ps.setInt(3, ordenTO.getIdCliente());
            
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

    public OrdenTO buscarOrden(int numeroParam) {
        
        OrdenTO retorno = new OrdenTO();
        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            
            ps = conn.prepareStatement("SELECT FECHA, MONTO FROM ORDENES WHERE NUMERO = ?");
            ps.setInt(1, numeroParam);
            rs = ps.executeQuery();

            
            if (rs.next()) {

             
                String fecha = rs.getString("FECHA");
                int monto = rs.getInt("MONTO");
               

                OrdenTO ordenTO = new OrdenTO();
               // ordenTO.setIdOrden(numero);
                ordenTO.setFechaOrden(fecha);
                ordenTO.setMontoTotal(monto);
              
                
                retorno = ordenTO;     
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
        return retorno;
    }
    
      public void eliminarOrden(int numeroParam) {

        Connection conn = super.getConexion();
        PreparedStatement ps = null;

        try {
            
            ps = conn.prepareStatement("DELETE FROM ORDENES WHERE NUMERO = ?");
            ps.setInt(1, numeroParam);
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
      
     public void actualizarOrden(OrdenTO ordenTO) {

     
        PreparedStatement ps = null;

        try {
            Connection conn = super.getConexion();
            ps = conn.prepareStatement("UPDATE ORDENES SET MONTO = ?  WHERE IDORDEN = ?");
            ps.setInt(1, ordenTO.getMontoTotal());
            ps.setInt(2,ordenTO.getIdOrden()); 
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
