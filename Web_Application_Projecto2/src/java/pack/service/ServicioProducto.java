package pack.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.primefaces.model.StreamedContent;
import static sun.security.krb5.Confounder.bytes;

@ManagedBean(name = "productService")
@ApplicationScoped
public class ServicioProducto extends Servicio implements Serializable {

    List<ProductoTO> products = demeProductos();

    List<ProductoTO> cart = new ArrayList();

    private double costoTotal = 0;

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

    public void insertarCarrito(ProductoTO productoTO) {

        productoTO.setImagen(productoTO.getImagen());
        productoTO.setNombreProducto(productoTO.getNombreProducto());
        productoTO.setDescripcion(productoTO.getDescripcion());
        productoTO.setPrecioVenta(productoTO.getPrecioVenta());
        cart.add(productoTO);

    }

    public List<ProductoTO> insertarCarrito2(ProductoTO productoTO) {

        productoTO.setImagen(productoTO.getImagen());
        productoTO.setNombreProducto(productoTO.getNombreProducto());
        productoTO.setDescripcion(productoTO.getDescripcion());
        productoTO.setPrecioVenta(productoTO.getPrecioVenta());
        cart.add(productoTO);
        return cart;

    }

    public void deleteCarrito(ProductoTO productoTO) {

        productoTO.setImagen(productoTO.getImagen());
        productoTO.setNombreProducto(productoTO.getNombreProducto());
        productoTO.setDescripcion(productoTO.getDescripcion());
        productoTO.setPrecioVenta(productoTO.getPrecioVenta());
        cart.remove(productoTO);

    }

    public double sumaCostoTotal() {
        this.costoTotal = cart.stream().mapToDouble(ProductoTO::getPrecioVenta).sum();
        return this.costoTotal;
    }

    public List<ProductoTO> demeProductos() {

        List<ProductoTO> listaRetorno = new ArrayList<>();

        Connection conn = super.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
  

        try {
            //Paso 3 (Preparar)
            //super.conectar();
            ps = conn.prepareStatement("SELECT IDPRODUCTO,NOMBRE,PRECIOVENTA,IMAGEN, COSTOTOTAL, TIPO, DESCRIPCION FROM PRODUCTO");
            rs = ps.executeQuery();

            //Paso 4 (Ejectuar)
            while (rs.next()) {

                int idProducto = rs.getInt("IDPRODUCTO");
                String nombreProducto = rs.getString("NOMBRE");
                double precioVenta = rs.getInt("PRECIOVENTA");
                byte[] imagen = rs.getBytes("IMAGEN");
                double costoTotal = rs.getInt("COSTOTOTAL");
                String tipo = rs.getString("TIPO");
                String descripcion = rs.getString("DESCRIPCION");

               
          
                ProductoTO productoTO = new ProductoTO();

                productoTO.setIdProducto(idProducto);
                productoTO.setNombreProducto(nombreProducto);
                productoTO.setPrecioVenta(precioVenta);
                productoTO.setImagen(imagen);
                productoTO.setTipo(tipo);
                productoTO.setDescripcion(descripcion);

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

    public List<ProductoTO> getCart() {
        return cart;
    }

    public void setCart(List<ProductoTO> cart) {
        this.cart = cart;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

}
