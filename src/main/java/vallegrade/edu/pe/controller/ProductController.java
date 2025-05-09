package vallegrade.edu.pe.controller;

import vallegrade.edu.pe.model.ProductEntity;
import vallegrade.edu.pe.service.ProductService;

import java.util.List;

public class ProductController {

    private ProductService service = new ProductService();

    // Obtener la lista de productos
    public List<ProductEntity> listar() {
        return service.obtenerProductos();
    }

    public void insertar(ProductEntity producto) {
        service.insertarProducto(producto);
    }

    // Insertar un nuevo producto
    public void insertarProducto(ProductEntity product) {
        service.insertarProducto(product);
    }

    // Actualizar un producto
    public void actualizar(ProductEntity product) { service.modificarProducto(product); }

    public void eliminarLogicamente(int studentId) { service.eliminarLogicamente(studentId); }

    public List<ProductEntity> listarEliminados() { return service.obtenerEliminados();
    }
}