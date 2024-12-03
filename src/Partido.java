import java.time.LocalDateTime;
// La clase Partido es la que va a almacenar los datos más importantes del partido
public class Partido {
    private LocalDateTime fechaHora;
    private String equipoAnfitrion;
    private String equipoVisitante;
    //private String categoria;
    //private double precio;
    //private int asientosDisponibles;
    //private Estadio estadio;


    public Partido(LocalDateTime fechaHora, String equipoAnfitrion, String equipoVisitante) {
        this.fechaHora = fechaHora;
        this.equipoAnfitrion = equipoAnfitrion;
        this.equipoVisitante = equipoVisitante;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEquipoAnfitrion() {
        return equipoAnfitrion;
    }

    public void setEquipoAnfitrion(String equipoAnfitrion) {
        this.equipoAnfitrion = equipoAnfitrion;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }
}