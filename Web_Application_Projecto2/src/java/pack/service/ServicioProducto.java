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

@ManagedBean(name = "productService")
@ApplicationScoped
public class ServicioProducto extends Servicio implements Serializable {

    List<ProductoTO> products = demeProductos();

    public ServicioProducto() {
    }

    public void insertarProducto(ProductoTO productoTO) {

        PreparedStatement ps = null;

        try {
            Connection conn = super.getConexion();
            ps = conn.prepareStatement("INSERT INTO PRODUCTOS(NOMBRE, PRECIO) VALUES(?,?)");
            ps.setString(1, productoTO.getNombreProducto());
            ps.setInt(2, productoTO.getPrecio());
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
        demeProductos();
    }

    public List<ProductoTO> demeProductos() {
        List<ProductoTO> listaRetorno = new ArrayList<>();
        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Paso 3 (Preparar)
            //super.conectar();
            ps = conn.prepareStatement("SELECT IDPRODUCTOS,DESCRIPCION,PRECIO,IMAGEN FROM PRODUCTOS");
            rs = ps.executeQuery();

            //Paso 4 (Ejectuar)
            while (rs.next()) {

                int idProducto = rs.getInt("IDPRODUCTOS");
                String descripcion = rs.getString("DESCRIPCION");
                int precio = rs.getInt("PRECIO");
                String imagen = rs.getString("IMAGEN");

                ProductoTO productoTO = new ProductoTO();
                productoTO.setIdProducto(idProducto);
                productoTO.setNombreProducto(descripcion);
                productoTO.setPrecio(precio);
                productoTO.setImagen(imagen);

                listaRetorno.add(productoTO);

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

    public int demeIdProducto() {

        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int maxProducto = 0;

        try {

            ps = conn.prepareStatement("SELECT MAX(idProducto) as max_producto FROM chiquitinas.productos");
            rs = ps.executeQuery();

            if (rs.next()) {

                maxProducto = rs.getInt("max_producto");

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
        return maxProducto;
    }

    public void eliminarProducto(int numeroParam) {

        Connection conn = super.getConexion();
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement("DELETE FROM PRODUCTOS WHERE ID = ?");
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

    public List<ProductoTO> getProducts() {
        return products;
    }
    
    

}
