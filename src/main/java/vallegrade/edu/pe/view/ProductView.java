package vallegrade.edu.pe.view;

import vallegrade.edu.pe.controller.ProductController;
import vallegrade.edu.pe.model.ProductEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.sql.Timestamp;

public class ProductView extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private ProductController controller = new ProductController();

    public ProductView() {
        setTitle("Gestor de Estudiantes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // MODELO Y TABLA
        modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellidos", "Correo-e", "Estado"}, 0);

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // BOTONES
        JButton btnAgregar = new JButton("Agregar nuevo estudiante");
        btnAgregar.addActionListener(e -> mostrarFormularioNuevoProducto());

        JButton btnEditar = new JButton("Editar estudiante seleccionado");
        btnEditar.addActionListener(e -> editarProducto());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar estudiante seleccionado");
        btnEliminar.addActionListener(e -> eliminarEstudianteSeleccionado());

        JButton btnVerEliminados = new JButton("Ver estudiantes eliminados");
        btnVerEliminados.addActionListener(e -> mostrarEstudiantesEliminados());

        panelBotones.add(btnVerEliminados);

        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);

        cargarDatos();

        setVisible(true);
    }

    private void eliminarEstudianteSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            int id = (int) modelo.getValueAt(fila, 0);

            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres eliminar este estudiante?",
                    "Eliminar Estudiante", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                controller.eliminarLogicamente(id);
                cargarDatos();  // Refresh the table
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un estudiante para eliminar.");
        }
    }

    private void mostrarEstudiantesEliminados() {
        modelo.setRowCount(0);  // Clear the table
        List<ProductEntity> listaEliminados = controller.listarEliminados();  // Add method to list deleted students
        for (ProductEntity p : listaEliminados) {
            modelo.addRow(new Object[]{
                    p.getProductId(),
                    p.getName(),
                    p.getSurname(),
                    p.getEmail(),
                    p.isEstate() ? "Activo" : "Inactivo",
            });
        }
    }
    private void cargarDatos() {
        modelo.setRowCount(0);  // Clear the table
        List<ProductEntity> lista = controller.listar();
        for (ProductEntity p : lista) {
            if (!p.isDeleted()) {  // Only show students that are not deleted
                modelo.addRow(new Object[]{
                        p.getProductId(),
                        p.getName(),
                        p.getSurname(),
                        p.getEmail(),
                        p.isEstate() ? "Activo" : "Inactivo",
                });
            }
        }
    }

    private void mostrarFormularioNuevoProducto() {
        JDialog dialog = new JDialog(this, "Nuevo Estudiante", true);
        dialog.setLayout(new GridLayout(6, 2, 10, 10));

        JTextField txtName = new JTextField();
        JTextField txtSurname = new JTextField();
        JTextField txtEmail = new JTextField();
        JCheckBox chkEstado = new JCheckBox("Activo", true);

        dialog.add(new JLabel("Nombre:"));
        dialog.add(txtName);
        dialog.add(new JLabel("Apellidos:"));
        dialog.add(txtSurname);
        dialog.add(new JLabel("Correo-e:"));
        dialog.add(txtEmail);
        dialog.add(new JLabel("Estado:"));
        dialog.add(chkEstado);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            try {
                String name = txtName.getText();
                String surname = txtSurname.getText();
                String email = txtEmail.getText();
                boolean estado = chkEstado.isSelected();

                ProductEntity nuevo = new ProductEntity();
                nuevo.setName(name);
                nuevo.setSurname(surname);
                nuevo.setEmail(surname);
                nuevo.setEstate(estado);

                controller.insertar(nuevo);
                cargarDatos();
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Datos inválidos. Verificar, por favor");
            }
        });

        dialog.add(new JLabel());
        dialog.add(btnGuardar);

        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void editarProducto() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            int id = (int) modelo.getValueAt(fila, 0);
            String name = JOptionPane.showInputDialog("Nuevo nombre:", modelo.getValueAt(fila, 1));
            String surname = JOptionPane.showInputDialog("Nuevos apellidos:", modelo.getValueAt(fila, 2));
            String correoStr = JOptionPane.showInputDialog("Nuevo correo:", modelo.getValueAt(fila, 3));
            int opcion = JOptionPane.showConfirmDialog(this, "¿Está activo?", "Estado", JOptionPane.YES_NO_OPTION);
            boolean estado = (opcion == JOptionPane.YES_OPTION);

try {
                ProductEntity producto = new ProductEntity();
                producto.setProductId(id);
                producto.setName(name);
                producto.setSurname(surname);
                producto.setEmail(correoStr);
                producto.setEstate(estado);

                controller.actualizar(producto);
                cargarDatos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio o stock no válidos.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para editar.");
        }
    }



    public static void main(String[] args) {
        new ProductView(); // Lanza la vista
    }
}
