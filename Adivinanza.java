package casino;

import java.util.Random;

public class Adivinanza {

    private int beneficio;

    public Adivinanza() {
        beneficio = 0;
    }

    public int obtenerNumero(int min, int max) {

        Random random = new Random();

        return random.nextInt(max - min + 1) + min;
    }

    public void jugar(Jugador jugador, int apuesta, int intervalo) {

        if (apuesta <= 0 || jugador.getDinero() < apuesta) {
            System.out.println("Apuesta no válida.");
            return;
        }

        if (intervalo <= 0) {
            System.out.println("El intervalo debe ser mayor que 0.");
            return;
        }

        int min = 1;
        int max = intervalo;

        int numeroObjetivo = obtenerNumero(min, max);

        int tercio = intervalo / 3;

        if (tercio == 0) {
            tercio = 1;
        }

        int inicioTercio = obtenerNumero(1, 3);

        int limiteInferior;
        int limiteSuperior;

        switch (inicioTercio) {

            case 1:
                limiteInferior = min;
                limiteSuperior = tercio;
                break;

            case 2:
                limiteInferior = tercio + 1;
                limiteSuperior = tercio * 2;
                break;

            default:
                limiteInferior = tercio * 2 + 1;
                limiteSuperior = max;
                break;
        }

        boolean jugadorGana =
                numeroObjetivo >= limiteInferior &&
                numeroObjetivo <= limiteSuperior;

        System.out.println("\n--- ADIVINANZA ---");

        System.out.println("Intervalo: " + min + " - " + max);

        System.out.println("Tercio seleccionado: "
                + limiteInferior + " - " + limiteSuperior);

        System.out.println("Número generado: " + numeroObjetivo);

        distribuirApuesta(jugador, apuesta, jugadorGana);

        if (jugadorGana) {

            jugador.sumarDinero(apuesta);

            System.out.println("¡Ganaste la adivinanza!");

        } else {

            System.out.println("Perdiste la apuesta.");
        }

        System.out.println("Dinero del jugador: $" + jugador.getDinero());
    }

    public void distribuirApuesta(Jugador jugador, int apuesta,
                                   boolean jugadorGana) {

        jugador.restarDinero(apuesta);

        if (!jugadorGana) {
            beneficio += apuesta;
        }
    }

    public int getBeneficio() {
        return beneficio;
    }
}