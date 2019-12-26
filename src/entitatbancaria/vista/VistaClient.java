package entitatbancaria.vista;

import entitatbancaria.model.logica.Client;
import java.util.List;
import java.util.Scanner;


public class VistaClient extends Vista{
    private  Scanner lector = new Scanner(System.in);

    @Override
    public String mostrarMenu() {

        System.out.println("\nIntroduir una opció: "
                + "\n1. Afegir nou client"
                + "\n2. Cercar per nif"
                + "\n3. Cercar per nom"
                + "\n4. Cercar tots"
                + "\n5. Tornar");
        return lector.nextLine();
    }


    public void imprimir(Client client) {
        System.out.println("DADES DEL CLIENT:");
        System.out.println(client);
    }

    public Client demanarClient() {
        System.out.println("Introduir les dades del client:");
        return new Client(demanarText("NIF: "),demanarText("Nom: "));
    }

    public void imprimir(List<Client> clients) {
        System.out.println("LLISTAT DE CLIENTS:");
        for (Client client : clients) {
            System.out.println(client);
        }
    }


}


