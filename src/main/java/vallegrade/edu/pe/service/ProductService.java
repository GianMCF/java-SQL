package vallegrade.edu.pe.service;

import vallegrade.edu.pe.model.ProductDAO;
import vallegrade.edu.pe.model.ProductEntity;

import java.util.List;

public class ProductService {

    private ProductDAO dao = new ProductDAO();

    // Obtener la lista de productos
    public List<ProductEntity> obtenerProductos() {
        return dao.listarProductos();
    }

    // Insertar un producto
    public void insertarProducto(ProductEntity producto) {
        dao.insertarProducto(producto);
    }

    // Modificar un producto
    public void modificarProducto(ProductEntity product) {
        dao.actualizarProducto(product);}

    public void eliminarLogicamente(int studentId) {
        dao.eliminarLogicamente(studentId);
    }
    public List<ProductEntity> obtenerEliminados() {
        return dao.listarEliminados();
    }
}


