package casino;

public class Casino {

    private final int APUESTA_MAXIMA = 100000;

    private Ruleta ruleta;
    private Adivinanza adivinanza;
    private Tragaperras tragaperras;

    public Casino() {
        ruleta = new Ruleta();
        adivinanza = new Adivinanza();
        tragaperras = new Tragaperras();
    }

    public void jugarRuleta(Jugador jugador, int apuesta,
                            int numero, boolean jugarAlColor) {

        if (!validarApuesta(jugador, apuesta)) {
            return;
        }

        if (jugarAlColor) {
            ruleta.jugarAlColor(jugador, numero, apuesta);
        } else {
            ruleta.jugarAlNumero(jugador, numero, apuesta);
        }

        revisarJugador(jugador);
    }

    public void jugarTragaperras(Jugador jugador, int apuesta) {

        if (!validarApuesta(jugador, apuesta)) {
            return;
        }

        tragaperras.jugar(jugador, apuesta);

        revisarJugador(jugador);
    }

    public void jugarAdivinanza(Jugador jugador,
                                int apuesta,
                                int intervalo) {

        if (!validarApuesta(jugador, apuesta)) {
            return;
        }

        adivinanza.jugar(jugador, apuesta, intervalo);

        revisarJugador(jugador);
    }

    private boolean validarApuesta(Jugador jugador, int apuesta) {

        if (apuesta <= 0) {
            System.out.println("La apuesta debe ser mayor que 0.");
            return false;
        }

        if (apuesta > APUESTA_MAXIMA) {
            System.out.println(
                    "La apuesta supera el máximo permitido: $"
                    + APUESTA_MAXIMA
            );
            return false;
        }

        if (apuesta > jugador.getDinero()) {
            System.out.println("No tienes suficiente dinero.");
            alertaMoroso(jugador);
            return false;
        }

        return true;
    }

    public void alertaMoroso(Jugador jugador) {

        if (jugador.getDinero() <= 0) {
            System.out.println(
                    "ALERTA: El jugador "
                    + jugador.getNombre()
                    + " se ha quedado sin dinero."
            );
        }
    }

    public void alertaTemerario(Jugador jugador) {

        if (jugador.getDinero() < 10000) {
            System.out.println(
                    "ALERTA: El jugador "
                    + jugador.getNombre()
                    + " está realizando apuestas de forma temeraria."
            );
        }
    }

    private void revisarJugador(Jugador jugador) {

        alertaMoroso(jugador);
        alertaTemerario(jugador);
    }

    @Override
    public String toString() {

        return "\n===== ESTADO DEL CASINO ====="
                + "\nBeneficio Ruleta: $"
                + ruleta.getBeneficio()
                + "\nBeneficio Tragaperras: $"
                + tragaperras.getBeneficio()
                + "\nBeneficio Adivinanza: $"
                + adivinanza.getBeneficio()
                + "\n=============================";
    }
}