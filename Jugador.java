package casino;

public class Jugador {

    private String nombre;
    private int dinero;

    public Jugador(String nombre, int dinero) {
        this.nombre = nombre;
        this.dinero = dinero;
    }

    public void sumarDinero(int dinero) {
        if (dinero > 0) {
            this.dinero += dinero;
        }
    }

    public void restarDinero(int dinero) {
        if (dinero > 0 && this.dinero >= dinero) {
            this.dinero -= dinero;
        }
    }

    public int getDinero() {
        return dinero;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Jugador: " + nombre +
               " | Dinero: $" + dinero;
    }
}