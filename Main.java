package casino;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("       CASINO JAVA");
        System.out.println("================================");

        System.out.print("Ingrese el nombre del jugador: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el dinero inicial: ");
        int dinero = scanner.nextInt();

        Jugador jugador = new Jugador(nombre, dinero);
        Casino casino = new Casino();

        int opcion;

        do {

            System.out.println("\n----------------------------");
            System.out.println("Jugador: " + jugador.getNombre());
            System.out.println("Dinero: $" + jugador.getDinero());
            System.out.println("----------------------------");

            System.out.println("1. Jugar Ruleta");
            System.out.println("2. Jugar Tragaperras");
            System.out.println("3. Jugar Adivinanza");
            System.out.println("4. Ver estado del casino");
            System.out.println("5. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {

                case 1:

                    System.out.println("\n--- RULETA ---");
                    System.out.println("1. Apostar a par");
                    System.out.println("2. Apostar a impar");
                    System.out.println("3. Apostar a número");

                    System.out.print("Seleccione: ");
                    int tipo = scanner.nextInt();

                    System.out.print("Ingrese la apuesta: ");
                    int apuestaRuleta = scanner.nextInt();

                    if (tipo == 1) {

                        casino.jugarRuleta(
                                jugador,
                                apuestaRuleta,
                                0,
                                true
                        );

                    } else if (tipo == 2) {

                        casino.jugarRuleta(
                                jugador,
                                apuestaRuleta,
                                1,
                                true
                        );

                    } else if (tipo == 3) {

                        System.out.print(
                                "Ingrese un número entre 0 y 36: "
                        );

                        int numero = scanner.nextInt();

                        casino.jugarRuleta(
                                jugador,
                                apuestaRuleta,
                                numero,
                                false
                        );

                    } else {

                        System.out.println("Opción inválida.");
                    }

                    break;

                case 2:

                    System.out.print(
                            "\nIngrese la apuesta: "
                    );

                    int apuestaTragaperras = scanner.nextInt();

                    casino.jugarTragaperras(
                            jugador,
                            apuestaTragaperras
                    );

                    break;

                case 3:

                    System.out.print(
                            "\nIngrese la apuesta: "
                    );

                    int apuestaAdivinanza = scanner.nextInt();

                    System.out.print(
                            "Ingrese el tamaño del intervalo: "
                    );

                    int intervalo = scanner.nextInt();

                    casino.jugarAdivinanza(
                            jugador,
                            apuestaAdivinanza,
                            intervalo
                    );

                    break;

                case 4:

                    System.out.println(casino);

                    break;

                case 5:

                    System.out.println(
                            "\nGracias por jugar."
                    );

                    break;

                default:

                    System.out.println(
                            "Opción inválida."
                    );
            }

        } while (opcion != 5);

        scanner.close();
    }
}