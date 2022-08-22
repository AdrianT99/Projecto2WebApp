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
            ps = conn.prepareStatement("INSERT INTO PRODUCTO(NOMBRE, PRECIOVENTA, COSTOTOTAL, TIPO, DESCRIPCION) VALUES(?,?,?,?,?)");
            ps.setString(1, productoTO.getNombreProducto());
            ps.setDouble(2, productoTO.getPrecioVenta());
            ps.setDouble(3, productoTO.getCostoTotal());
            ps.setString(4, productoTO.getTipo());
            ps.setString(5, productoTO.getDescripcion());
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
            ps = conn.prepareStatement("SELECT IDPRODUCTO,NOMBRE,PRECIOVENTA,IMAGEN, COSTOTOTAL, TIPO FROM PRODUCTO");
            rs = ps.executeQuery();

            //Paso 4 (Ejectuar)
            while (rs.next()) {

                int idProducto = rs.getInt("IDPRODUCTO");
                String nombreProducto = rs.getString("NOMBRE");
                double precioVenta = rs.getInt("PRECIOVENTA");
                String imagen = rs.getString("IMAGEN");
                double costoTotal = rs.getInt("COSTOTOTAL");
                String tipo = rs.getString("TIPO");

                ProductoTO productoTO = new ProductoTO();
                productoTO.setIdProducto(idProducto);
                productoTO.setNombreProducto(nombreProducto);
                productoTO.setPrecioVenta(precioVenta);
                productoTO.setImagen(imagen);
                productoTO.setTipo(tipo);

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

            ps = conn.prepareStatement("SELECT MAX(idProducto) as max_producto FROM producto");
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

            ps = conn.prepareStatement("DELETE FROM PRODUCTOS WHERE IDPRODUCTO = ?");
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
