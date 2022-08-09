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

/**
 *
 * @author User1
 */
public class ServicioDashboard extends Servicio {

    public int CantUsuarios() {
        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int CantUsuarios = 0;
        try {
            //Paso 3 (Preparar)
            //super.conectar();
            ps = conn.prepareStatement("select count(iduser) from `web-project02`.user;");
            rs = ps.executeQuery();

            //Paso 4 (Ejectuar)
            while (rs.next()) {
                CantUsuarios = rs.getInt("count(iduser)");

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

        return CantUsuarios;

    }
    
    public int CantUsuariosAdmin() {
        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int CantUsuarios = 0;
        try {
            //Paso 3 (Preparar)
            //super.conectar();
            ps = conn.prepareStatement("select count(TipoUsuario) as admins from `web-project02`.user where TipoUsuario = 0;");
            rs = ps.executeQuery();

            //Paso 4 (Ejectuar)
            while (rs.next()) {
                CantUsuarios = rs.getInt("admins");

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

        return CantUsuarios;

    }
    
    
}
