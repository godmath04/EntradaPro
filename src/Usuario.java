import java.time.LocalDate;

public class Usuario {
    private String nombre;
    private String apellido;
    private String cedula;
    private String equipoFavorito;
    private LocalDate fechaNacimiento;
    private String email;

    // Constructor paraa cada Usiario
    public Usuario(String nombre, String apellido, String cedula, String equipoFavorito, LocalDate fechaNacimiento, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.equipoFavorito = equipoFavorito;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
    }

    //Metodos get y set para llamar el private
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEquipoFavorito() {
        return equipoFavorito;
    }

    public void setEquipoFavorito(String equipoFavorito) {
        this.equipoFavorito = equipoFavorito;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}