import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class RetoEDA {

    private Baraja baraja;

    public static void main(String[] args) {

        Baraja baraja = new Baraja();
        Carta[] cartas = new Carta[52];
        int index = 0;

        while (!baraja.vacia()) {
            cartas[index] = baraja.sacar();
            index++;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el tipo de ordenamiento:");
        System.out.println("1. Ordenar por número y luego por palo");
        System.out.println("2. Ordenar por palo y luego por número");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                Arrays.sort(cartas, 0, index, new Comparator<Carta>() {
                    @Override
                    public int compare(Carta c1, Carta c2) {
                        int numero1 = getNumero(c1);
                        int numero2 = getNumero(c2);
        
                        if (numero1 != numero2) {
                            return Integer.compare(numero1, numero2);
                        } else {
                            int palo1 = getOrdenPalo(c1);
                            int palo2 = getOrdenPalo(c2);
                            return Integer.compare(palo1, palo2);
                        }
                    }
        
                    private int getOrdenPalo(Carta carta) {
                        if (carta.igualPalo(new Carta(2, 0))) {
                            return 0;
                        } else if (carta.igualPalo(new Carta(1, 0))) {
                            return 1;
                        } else if (carta.igualPalo(new Carta(0, 0))) {
                            return 2;
                        } else {
                            return 3;
                        }
                    }
        
                    private int getNumero(Carta carta) {
                        for (int i = 0; i < 13; i++) {
                            if (carta.siguiente(new Carta(0, i))) {
                                return i;
                            }
                        }
                        return 12;
                    }
                });
            break;
                
            case 2:
                Arrays.sort(cartas, 0, index, new Comparator<Carta>() {
                    @Override
                    public int compare(Carta c1, Carta c2) {
                        int palo1 = getOrdenPalo(c1);
                        int palo2 = getOrdenPalo(c2);

                        if (palo1 != palo2) {
                            return Integer.compare(palo1, palo2);
                        } else {
                            return Integer.compare(getNumero(c2), getNumero(c1));
                        }
                    }

                    private int getOrdenPalo(Carta carta) {
                        if (carta.igualPalo(new Carta(2, 0))) {
                            return 0;
                        } else if (carta.igualPalo(new Carta(1, 0))) {
                            return 1;
                        } else if (carta.igualPalo(new Carta(0, 0))) {
                            return 2;
                        } else {
                            return 3;
                        }
                    }

                    private int getNumero(Carta carta) {
                        for (int i = 0; i < 13; i++) {
                            if (carta.siguiente(new Carta(0, i))) {
                                return i;
                            }
                        }
                        return 12;
                    }
                });
            break;

            default:
            System.out.println("Opcion no valida :( ");
            break;
        }
        for (int i = 0; i < index; i++) {
            cartas[i].voltear();
            cartas[i].mostrar();
        }
    }
}
