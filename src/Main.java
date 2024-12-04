import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String[] opciones = {"Administrador", "Crear Usuario", "Ingresar como Usuario"};
        int seleccion = JOptionPane.showOptionDialog(
                null,
                "Seleccione cómo desea ingresar:",
                "Bienvenido",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == 0) { // Administrador
            JFrame frame = new JFrame("Administrador");
            frame.setContentPane(new EntradaProGUI().getPanelGeneral());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        } else if (seleccion == 1) { // Crear Usuario
            registrarUsuario();
        } else if (seleccion == 2) { // Ingresar como Usuario
            iniciarSesion();
        }
    }

    private static void registrarUsuario() {
        try {
            String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
            String apellido = JOptionPane.showInputDialog("Ingrese su apellido:");
            String cedula = JOptionPane.showInputDialog("Ingrese su cédula:");
            String equipoFavorito = JOptionPane.showInputDialog("Ingrese su equipo favorito:");
            String fechaNacimiento = JOptionPane.showInputDialog("Ingrese su fecha de nacimiento (yyyy-MM-dd):");
            String email = JOptionPane.showInputDialog("Ingrese su correo electrónico:");

            Usuario nuevoUsuario = new Usuario(
                    nombre,
                    apellido,
                    cedula,
                    equipoFavorito,
                    java.time.LocalDate.parse(fechaNacimiento),
                    email
            );

            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente: " + nuevoUsuario.getNombre());

            // Mostrar la ventana VistaUsuarioGUI para este usuario
            mostrarVistaUsuario(nuevoUsuario);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar usuario. Verifique los datos ingresados.");
        }
    }

    private static void iniciarSesion() {
        String cedula = JOptionPane.showInputDialog("Ingrese su cédula para iniciar sesión:");
        // Asumimos que se inicia sesion de forma correcta
        JOptionPane.showMessageDialog(null, "Sesión iniciada para el usuario con cédula: " + cedula);

        JFrame frame = new JFrame("Vista Usuario");
        frame.setContentPane(new VistaUsuarioGUI().getPanelUsuario());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static void mostrarVistaUsuario(Usuario usuario) {
        JFrame frame = new JFrame("Vista Usuario");
        VistaUsuarioGUI vistaUsuarioGUI = new VistaUsuarioGUI();
        vistaUsuarioGUI.setUsuario(usuario); // Pasar información del usuario registrado
        frame.setContentPane(vistaUsuarioGUI.getPanelUsuario());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
