package entitatbancaria.vista;

import entitatbancaria.model.logica.Client;
import entitatbancaria.model.logica.Compte;
import java.util.List;
import java.util.Scanner;

public class VistaCompte extends Vista {

    @Override
    public String mostrarMenu() {
        Scanner lector = new Scanner(System.in);
        System.out.println("\nIntroduir una opció: "
                + "\n1. Afegir nou compte"
                + "\n2. Cercar per número"
                + "\n3. Cercar per client"
                + "\n4. Cercar tots"
                + "\n5. Tornar");
        return new Scanner(System.in).nextLine();
    }

    public Compte demanarCompte() {

        System.out.println("Introduir les dades del compte:");
        return new Compte(demanarLongPositiu("Número de compte: "),
                new Client(demanarText("NIF: "), null),
                demanarFloatPositiu("Interès anual: "), 0);
    }

    public void imprimir(Compte compte) {
        System.out.println("DADES DEL COMPTE:");
        System.out.println(compte);
    }

    public void imprimir(List<Compte> comptes) {
        System.out.println("LLISTAT DE COMPTES:");
        for (Compte compte : comptes) {
            System.out.println(compte);
        }
    }

    public float demanarFloatPositiu(String info) {
        Scanner lector = new Scanner(System.in);
        System.out.print(info);
        float valor = -1;
        boolean continuar = true;
        while (continuar) {
            while (!lector.hasNextFloat()) {
                System.out.println("Dada incorrecta, torneu-la a introduir:");
                lector.nextLine();
            }
            if ((valor = lector.nextFloat()) > 0) {
                continuar = false;

            } else {
                System.out.println("Dada incorrecta, torneu-la a introduir:");
            }
        }
        return valor;
    }

    public long demanarLongPositiu(String info) {
        Scanner lector = new Scanner(System.in);
        System.out.print(info);
        long valor = -1;
        boolean continuar = true;
        while (continuar) {
            while (!lector.hasNextLong()) {
                System.out.println("Dada incorrecta, torneu-la a introduir:");
                lector.nextLine();
            }
            if ((valor = Long.parseLong(lector.nextLine())) > 0) {
                continuar = false;
            } else {
                System.out.println("Dada incorrecta, torneu-la a introduir:");
            }
        }
        return valor;
    }
}
