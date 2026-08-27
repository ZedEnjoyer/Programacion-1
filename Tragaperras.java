package casino;


import java.util.Random;

public class Tragaperras {

    private int beneficio;

    public Tragaperras() {
        beneficio = 0;
    }

    private int obtenerNumero() {
        Random random = new Random();
        return random.nextInt(10);
    }

    public void jugar(Jugador jugador, int apuesta) {

        if (apuesta <= 0 || jugador.getDinero() < apuesta) {
            System.out.println("Apuesta no válida.");
            return;
        }

        int numero1 = obtenerNumero();
        int numero2 = obtenerNumero();
        int numero3 = obtenerNumero();

        System.out.println("\n--- TRAGAPERRAS ---");
        System.out.println("[" + numero1 + "] [" + numero2 + "] [" + numero3 + "]");

        boolean jugadorGana = false;
        int premio = 0;

        // Tres números iguales
        if (numero1 == numero2 && numero2 == numero3) {
            jugadorGana = true;
            premio = apuesta * 10;
            System.out.println("¡JACKPOT! Los tres números son iguales.");
        }

        // Secuencia consecutiva
        else if ((numero1 + 1 == numero2 && numero2 + 1 == numero3) ||
                 (numero3 + 1 == numero2 && numero2 + 1 == numero1)) {

            jugadorGana = true;
            premio = apuesta * 5;
            System.out.println("¡Ganaste! Formaste una secuencia.");
        }

        else {
            System.out.println("Perdiste la apuesta.");
        }

        distribuirApuesta(jugador, apuesta, jugadorGana);

        if (jugadorGana) {
            // La apuesta ya fue descontada, por lo que entregamos el premio
            jugador.sumarDinero(premio);

            // Si el premio supera la apuesta, el casino pierde esa diferencia
            beneficio -= (premio - apuesta);

            System.out.println("Premio: $" + premio);
        }

        System.out.println("Dinero del jugador: $" + jugador.getDinero());
    }

    public void distribuirApuesta(Jugador jugador, int apuesta,
                                   boolean jugadorGana) {

        if (jugadorGana) {
            jugador.restarDinero(apuesta);
        } else {
            jugador.restarDinero(apuesta);
            beneficio += apuesta;
        }
    }

    public int getBeneficio() {
        return beneficio;
    }
}