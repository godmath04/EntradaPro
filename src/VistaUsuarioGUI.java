import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaUsuarioGUI {
    private JPanel pGeneral2;
    private JButton verEntradasButton;
    private JButton comprarEntradasButton;
    private JButton editarPerfilButton;
    private JLabel lblUsuarioInfo;
    private Usuario usuario;

    public VistaUsuarioGUI() {
        verEntradasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Mostrando entradas compradas por: " + usuario.getNombre());

            }
        });
        comprarEntradasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Iniciando compra de entradas para: " + usuario.getNombre());

            }
        });
        editarPerfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Mostrando opciones para editar el perfil de: " + usuario.getNombre());

            }
        });
    }


    //Mostrar ususuario en la vista
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        lblUsuarioInfo.setText("Bienvenido, " + usuario.getNombre() + " (" + usuario.getEquipoFavorito() + ")");
    }

    //Obtener el panel principal
    public JPanel getPanelUsuario() {
        return pGeneral2;
    }
}
