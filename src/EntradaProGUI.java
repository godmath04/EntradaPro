import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
/* Este GUI hace referencia al usuario administrador el cual podra gestionar los partidos y los estadios
* */

public class EntradaProGUI {
    private JPanel pGeneral;
    private JTextArea textArea1;
    private JButton verPartidosButton;
    private JButton agregarPartidoButton;
    private JButton eliminarPartidosButton;
    private JButton modificarPartidoButton;
    private JButton crearEstadioButton;
    private JTextArea textArea2;
    private JButton mostrarEstadioButton;
    private JButton modificarCapacidadButton;
    private JButton eliminarEstadioButton;
    private LinkedList<Partido> listaPartidos;
    private LinkedList<Estadio> listaEstadios;

    public EntradaProGUI() {
        // Dar inicio a la  lista de Partidos y estadios
        listaPartidos = new LinkedList<>();
        listaEstadios = new LinkedList<>();

        verPartidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                for (Partido partido : listaPartidos) {
                    sb.append("Partido: ")
                            .append(partido.getEquipoAnfitrion())
                            .append(" vs ")
                            .append(partido.getEquipoVisitante())
                            .append(" - Fecha: ")
                            .append(partido.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                            .append("\n");
                }
                textArea1.setText(sb.toString());
            }
        });

        agregarPartidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String equipoAnfitrion = JOptionPane.showInputDialog("Ingrese el nombre del equipo anfitrión:");
                if (equipoAnfitrion == null || equipoAnfitrion.trim().isEmpty()) return;

                String equipoVisitante = JOptionPane.showInputDialog("Ingrese el nombre del equipo visitante:");
                if (equipoVisitante == null || equipoVisitante.trim().isEmpty()) return;

                // Crear un JSpinner que usaremos como calendario para la fecha y hora
                JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
                dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd HH:mm"));

                int result = JOptionPane.showConfirmDialog(
                        null,
                        dateSpinner,
                        "Seleccione la fecha y hora del partido",
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (result == JOptionPane.OK_OPTION) {
                    Date selectedDate = (Date) dateSpinner.getValue();
                    LocalDateTime fechaHora = selectedDate.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime();

                    Partido partido = new Partido(fechaHora, equipoAnfitrion, equipoVisitante);
                    listaPartidos.add(partido);
                    JOptionPane.showMessageDialog(null, "Partido agregado de forma correcta");
                }
            }
        });

        eliminarPartidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaPartidos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay partidos para eliminar.");
                    return;
                }

                StringBuilder sb = new StringBuilder("Partidos disponibles:\n");
                for (int i = 0; i < listaPartidos.size(); i++) {
                    Partido partido = listaPartidos.get(i);
                    sb.append(i + 1)
                            .append(". ")
                            .append(partido.getEquipoAnfitrion())
                            .append(" vs ")
                            .append(partido.getEquipoVisitante())
                            .append(" - Fecha: ")
                            .append(partido.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                            .append("\n");
                }

                String partidoAEliminar = JOptionPane.showInputDialog(
                        sb.append("\nIngrese el número del partido que desea eliminar:").toString()
                );

                try {
                    int indice = Integer.parseInt(partidoAEliminar) - 1; // Convertir indices a 0
                    if (indice >= 0 && indice < listaPartidos.size()) {
                        listaPartidos.remove(indice);
                        JOptionPane.showMessageDialog(null, "Partido eliminado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Número de partido inválido. Intente de nuevo.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, ingrese un número válido.");
                }
            }
        });
        modificarPartidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaPartidos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay partidos disponibles para modificar.");
                    return;
                }

                StringBuilder sb = new StringBuilder("Partidos disponibles:\n");
                for (int i = 0; i < listaPartidos.size(); i++) {
                    Partido partido = listaPartidos.get(i);
                    sb.append(i + 1)
                            .append(". ")
                            .append(partido.getEquipoAnfitrion())
                            .append(" vs ")
                            .append(partido.getEquipoVisitante())
                            .append(" - Fecha: ")
                            .append(partido.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                            .append("\n");
                }

                String partidoAModificar = JOptionPane.showInputDialog(
                        sb.append("\nIngrese el número del partido que desea modificar:").toString()
                );

                try {
                    int indice = Integer.parseInt(partidoAModificar) - 1; // Convertir a índice basado en 0
                    if (indice >= 0 && indice < listaPartidos.size()) {
                        Partido partido = listaPartidos.get(indice);

                        // Crear un JSpinner para la fecha y hora
                        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
                        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd HH:mm"));

                        // Inicializar el JSpinner con la fecha y hora actual del partido
                        Date currentDate = Date.from(
                                partido.getFechaHora().atZone(ZoneId.systemDefault()).toInstant()
                        );
                        dateSpinner.setValue(currentDate);

                        int result = JOptionPane.showConfirmDialog(
                                null,
                                dateSpinner,
                                "Modifique la fecha y hora del partido",
                                JOptionPane.OK_CANCEL_OPTION
                        );

                        if (result == JOptionPane.OK_OPTION) {
                            Date selectedDate = (Date) dateSpinner.getValue();
                            LocalDateTime nuevaFechaHora = selectedDate.toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDateTime();

                            // Actualizar la fecha y hora del partido
                            partido.setFechaHora(nuevaFechaHora);
                            JOptionPane.showMessageDialog(null, "Fecha y hora del partido modificadas exitosamente.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Número de partido inválido. Intente de nuevo.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, ingrese un número válido.");
                }
            }
        });

        crearEstadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estadio:");
                String ubicacion = JOptionPane.showInputDialog("Ingrese la ubicación del estadio:");
                String capacidadStr = JOptionPane.showInputDialog("Ingrese la capacidad del estadio:");
                String equipoLocalNombre = JOptionPane.showInputDialog("Ingrese el nombre del equipo local:");

                try {
                    int capacidad = Integer.parseInt(capacidadStr);

                    // Crear el equipo local
                    Equipo equipoLocal = new Equipo(equipoLocalNombre, null);

                    // Crear el estadio
                    Estadio estadio = new Estadio(nombre, ubicacion, capacidad, equipoLocal);

                    // Asignar el estadio al equipo local
                    equipoLocal.setEstadioLocal(estadio);

                    // Añadir el estadio a una lista (asegúrate de tener una lista de estadios definida)
                    listaEstadios.add(estadio);

                    JOptionPane.showMessageDialog(null, "Estadio creado exitosamente.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: La capacidad debe ser un número válido.");
                }

            }
        });
        mostrarEstadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaEstadios.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay estadios disponibles para mostrar.");
                    return;
                }

                StringBuilder sb = new StringBuilder("Estadios disponibles:\n");
                for (int i = 0; i < listaEstadios.size(); i++) {
                    Estadio estadio = listaEstadios.get(i);
                    sb.append(i + 1)
                            .append(". ")
                            .append("Nombre: ").append(estadio.getNombre())
                            .append(", Ubicación: ").append(estadio.getUbicacion())
                            .append(", Capacidad: ").append(estadio.getCapacidad())
                            .append(", Equipo Local: ").append(estadio.getEquipoLocal().getClass())
                            .append("\n");
                }
                textArea2.setText(sb.toString());
            }
        });
        modificarCapacidadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaEstadios.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay estadios disponibles para modificar.");
                    return;
                }

                StringBuilder sb = new StringBuilder("Estadios disponibles:\n");
                for (int i = 0; i < listaEstadios.size(); i++) {
                    Estadio estadio = listaEstadios.get(i);
                    sb.append(i + 1)
                            .append(". Nombre: ").append(estadio.getNombre())
                            .append(", Capacidad: ").append(estadio.getCapacidad())
                            .append("\n");
                }

                String opcionStr = JOptionPane.showInputDialog(sb.append("\nIngrese el número del estadio que desea modificar:").toString());

                try {
                    int indice = Integer.parseInt(opcionStr) - 1; // Convertir a índice basado en 0
                    if (indice >= 0 && indice < listaEstadios.size()) {
                        Estadio estadioSeleccionado = listaEstadios.get(indice);

                        int confirmacion = JOptionPane.showConfirmDialog(
                                null,
                                "¿Hay algún evento de remodelación en el estadio " + estadioSeleccionado.getNombre() + "?",
                                "Confirmar Remodelación",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (confirmacion == JOptionPane.YES_OPTION) {
                            String nuevaCapacidadStr = JOptionPane.showInputDialog(
                                    "Ingrese la nueva capacidad para el estadio " + estadioSeleccionado.getNombre() + ":"
                            );

                            try {
                                int nuevaCapacidad = Integer.parseInt(nuevaCapacidadStr);
                                if (nuevaCapacidad > 0) {
                                    estadioSeleccionado.setCapacidad(nuevaCapacidad);
                                    JOptionPane.showMessageDialog(null, "Capacidad modificada exitosamente.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error: La capacidad debe ser un número positivo.");
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Error: Entrada inválida. La capacidad debe ser un número.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Modificación cancelada.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Número de estadio inválido. Intente de nuevo.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, ingrese un número válido.");
                }

            }
        });
        eliminarEstadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaEstadios.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay estadios disponibles para eliminar.");
                    return;
                }

                StringBuilder sb = new StringBuilder("Estadios disponibles:\n");
                for (int i = 0; i < listaEstadios.size(); i++) {
                    Estadio estadio = listaEstadios.get(i);
                    sb.append(i + 1)
                            .append(". Nombre: ").append(estadio.getNombre())
                            .append(", Capacidad: ").append(estadio.getCapacidad())
                            .append("\n");
                }

                String opcionStr = JOptionPane.showInputDialog(sb.append("\nIngrese el número del estadio que desea eliminar:").toString());

                try {
                    int indice = Integer.parseInt(opcionStr) - 1; // Convertir a índice basado en 0
                    if (indice >= 0 && indice < listaEstadios.size()) {
                        Estadio estadioSeleccionado = listaEstadios.get(indice);

                        int confirmacion = JOptionPane.showConfirmDialog(
                                null,
                                "¿Está seguro de que desea eliminar el estadio " + estadioSeleccionado.getNombre() + "?",
                                "Confirmar Eliminación",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (confirmacion == JOptionPane.YES_OPTION) {
                            listaEstadios.remove(indice);
                            JOptionPane.showMessageDialog(null, "Estadio eliminado exitosamente.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Eliminación cancelada.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Número de estadio inválido. Intente de nuevo.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, ingrese un número válido.");
                }

            }
        });
    }

    public JPanel getPanelGeneral() {
        return pGeneral;
    }


}
