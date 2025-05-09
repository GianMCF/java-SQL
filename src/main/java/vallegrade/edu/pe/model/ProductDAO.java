package vallegrade.edu.pe.model;

import vallegrade.edu.pe.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProductDAO {

    // Listar productos
    public List<ProductEntity> listarProductos() {
        List<ProductEntity> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";  // Asegúrate de que la consulta esté correcta

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductEntity estudiantes = new ProductEntity();
                estudiantes.setProductId(rs.getInt("id"));
                estudiantes.setName(rs.getString("nombre"));
                estudiantes.setSurname(rs.getString("apellido"));
                estudiantes.setEmail(rs.getString("correo"));
                estudiantes.setEstate(rs.getBoolean("estado"));

                lista.add(estudiantes);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Insertar un producto
    public void insertarProducto(ProductEntity estudiantes) {
        String sql = "INSERT INTO estudiantes (nombre, apellido, correo, estado) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estudiantes.getName());
            ps.setString(2, estudiantes.getSurname());
            ps.setString(3, estudiantes.getEmail());
            ps.setBoolean(4, estudiantes.isEstate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarLogicamente(int studentId) {
        String sql = "UPDATE estudiantes SET is_deleted = 1 WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId); // Set the student ID to delete
            ps.executeUpdate(); // Execute the update statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProductEntity> listarEliminados() {
        List<ProductEntity> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes WHERE is_deleted = 1";  // Fetch only deleted students

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductEntity estudiantes = new ProductEntity();
                estudiantes.setProductId(rs.getInt("id"));
                estudiantes.setName(rs.getString("nombre"));
                estudiantes.setSurname(rs.getString("apellido"));
                estudiantes.setEmail(rs.getString("correo"));
                estudiantes.setEstate(rs.getBoolean("estado"));
                estudiantes.setDeleted(rs.getBoolean("is_deleted"));

                lista.add(estudiantes);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Actualizar un producto
    public void actualizarProducto(ProductEntity estudiantes) {
        String sql = "UPDATE estudiantes SET nombre=?, apellido=?, correo=?, estado=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estudiantes.getName());
            pstmt.setString(2, estudiantes.getSurname());
            pstmt.setString(3, estudiantes.getEmail());
            pstmt.setBoolean(4, estudiantes.isEstate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}