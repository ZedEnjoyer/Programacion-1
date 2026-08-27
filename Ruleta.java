package casino;

import java.util.Random;


public class Ruleta {

    private int beneficio;

    public Ruleta() {
        beneficio = 0;
    }

    public int girarRuleta() {
        Random random = new Random();
        return random.nextInt(37);
    }

    public void jugarAlColor(Jugador jugador, int color, int apuesta) {

        if (apuesta <= 0 || jugador.getDinero() < apuesta) {
            System.out.println("Apuesta no válida.");
            return;
        }

        int numero = girarRuleta();

        /*
         * color:
         * 0 = par
         * 1 = impar
         */

        boolean jugadorGana = false;

        if (numero != 0) {

            if (color == 0 && numero % 2 == 0) {
                jugadorGana = true;
            }

            if (color == 1 && numero % 2 != 0) {
                jugadorGana = true;
            }
        }

        System.out.println("\n--- RULETA ---");
        System.out.println("Número obtenido: " + numero);

        distribuirApuesta(jugador, apuesta, jugadorGana);

        if (jugadorGana) {
            jugador.sumarDinero(apuesta);
            System.out.println("¡Ganaste la apuesta al color!");
        } else {
            System.out.println("Perdiste la apuesta.");
        }

        System.out.println("Dinero del jugador: $" + jugador.getDinero());
    }

    public void jugarAlNumero(Jugador jugador, int numero,
                              int apuesta) {

        if (apuesta <= 0 || jugador.getDinero() < apuesta) {
            System.out.println("Apuesta no válida.");
            return;
        }

        if (numero < 0 || numero > 36) {
            System.out.println("El número debe estar entre 0 y 36.");
            return;
        }

        int resultado = girarRuleta();

        System.out.println("\n--- RULETA ---");
        System.out.println("Número obtenido: " + resultado);

        boolean jugadorGana = resultado == numero;

        distribuirApuesta(jugador, apuesta, jugadorGana);

        if (jugadorGana) {

            int premio = apuesta * 35;

            jugador.sumarDinero(premio);

            beneficio -= (premio - apuesta);

            System.out.println("¡Ganaste!");
            System.out.println("Premio: $" + premio);

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