public class Equipo {
    private String nombre;
    Estadio estadioLocal;
    //Lista de jugadores


    public Equipo(String nombre, Estadio estadioLocal) {
        this.nombre = nombre;
        this.estadioLocal = estadioLocal;
    }
    public void setEstadioLocal(Estadio estadioLocal) {
        this.estadioLocal = estadioLocal;
    }

}
