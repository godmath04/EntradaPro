import java.time.LocalDateTime;
// La clase Partido es la que va a almacenar los datos más importantes del partido
public class Partido {
    private String id;
    private LocalDateTime fechaHora;
    private String equipoAnfitrion;
    private String equipoVisitante;
    private String categoria;
    private double precio;
    private int asientosDisponibles;
    private Estadio estadio;

    //Los atributos iniciales que van a recibir los partidos
    public Partido(String id, LocalDateTime fechaHora, String equipoAnfitrion, String equipoVisitante, String categoria, double precio, int asientosDisponibles, Estadio estadio) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.equipoAnfitrion = equipoAnfitrion;
        this.equipoVisitante = equipoVisitante;
        this.categoria = categoria;
        this.precio = precio;
        this.asientosDisponibles = asientosDisponibles;
        this.estadio = estadio;
    }

    // Metodos de get y set
    public Estadio getEstadio(){
        return estadio;
    }
    public void setEstadio(){
        this.estadio = estadio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }


    //Metodo para que se reduzcan la cantidad de asientos
    public void reducirAsientosDisponibles(int cantidad) throws IllegalArgumentException {
        if (cantidad > asientosDisponibles) {
            throw new IllegalArgumentException("No hay suficientes asientos disponibles.");
        }
        asientosDisponibles -= cantidad;
    }
    //Metodo para verificar que haya asientos disponibles
    public boolean hayAsientosDisponibles(int cantidad) {
        return cantidad <= asientosDisponibles;
    }
}