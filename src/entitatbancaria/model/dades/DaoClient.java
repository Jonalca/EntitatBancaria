package entitatbancaria.model.dades;

import entitatbancaria.model.logica.Client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoClient {

    private File fitxer;
    public final String SEPARADOR;

    public DaoClient(String rutaFitxer, String separador) {
        this.fitxer = new File(rutaFitxer);
        this.SEPARADOR = separador;
    }

    public File getFitxer() {
        return fitxer;
    }

    public void setFitxer(File fitxer) {
        this.fitxer = fitxer;
    }

    /**
     * S'afegeixen les dades del client c al fitxer, comprovant primer que no
     * existeix ja un altre client amb el mateix nif.
     *
     * @param c, Client nou
     * @return true si les dades han estat afegides i false en cas contrari.
     */
    public boolean afegir(Client c) {
        StringBuilder sb = new StringBuilder();
        boolean afegit = false;

        try {

            if (cercarPerNif(c.getNif()) == null) {
                try (BufferedWriter bw = new BufferedWriter(
                        new FileWriter(fitxer, true));) {
                    //Afegim el client al fitxer
                    bw.write(sb.append(c.getNif()).append(SEPARADOR).
                            append(c.getNom()).toString());
                    bw.newLine();//Es crea una nova línia
                    bw.flush(); //Forcem que s'escriguin les dades al fitxer.
                    afegit = true;

                } catch (IOException e) {
                    Logger.getLogger(DaoClient.class.getName()).log(Level.SEVERE,
                            null, "Error a l'afegir un client al fitxer" + e);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DaoClient.class.getName()).log(Level.SEVERE, null,
                    "Error a l'afegir un client al fitxer" + ex);
        }
        return afegit;
    }

    /**
     * Cerca al fitxer les dades del client amb el nif rebut.
     *
     * @param nif
     * @return Client amb les dades trobades o null si no s'ha trobat.
     * @throws IOException és llançada si no es pot llegir el fitxer.
     */
    public Client cercarPerNif(String nif) throws IOException {
        Client c = null;
        if (fitxer.length() > 0) {
            try (BufferedReader br = new BufferedReader(
                    new FileReader(fitxer));) {
                String linia;
                Client aux = null;
                while ((linia = br.readLine()) != null) {
                    aux = obtenirClient(linia);
                    if (aux.getNif().equalsIgnoreCase(nif)) {
                        c = aux;
                        break;
                    }
                }
            }
        }
        return c;
    }

    /**
     * Cerca al fitxer els clients amb el nom igual al rebut.
     *
     * @param nom
     * @return torna un List<Client> amb els clients trobats que tenen el mateix
     * nom que el rebut al paràmetre nom.
     * @throws IOException és llançada si no es pot llegir el fitxer.
     */
    public List<Client> cercarPerNom(String nom) throws IOException {
        List<Client> clients = new ArrayList<>();
        if (fitxer.length() > 0) {
            try (BufferedReader br = new BufferedReader(
                    new FileReader(fitxer));) {
                String linia;
                Client aux = null;
                while ((linia = br.readLine()) != null) {
                    aux = obtenirClient(linia);
                    if (aux.getNom().equalsIgnoreCase(nom)) {
                        clients.add(aux);
                    }
                }
            }
        }
        return clients;
    }

    /**
     * Torna un List<Client> amb tots els clients existents al fitxer.
     *
     * @return torna un List<Client> amb els clientsn
     * @throws IOException és llançada si no es pot llegir el fitxer.
     */
    public List<Client> cercarTots() throws IOException {
        List<Client> clients = new ArrayList<>();
        if (fitxer.length() > 0) {
            try (BufferedReader br = new BufferedReader(new FileReader(fitxer));) {
                String linia;

                while ((linia = br.readLine()) != null) {
                    clients.add(obtenirClient(linia));
                }
            }
        }
        return clients;
    }

    /**
     * Crea una instància de Client amb els valors dels atributs rebuts a
     * dadesClient
     *
     * @param dadesCompte
     * @return Client
     * @throws IOException és llançada si no es pot llegir el fitxer.
     */
    private Client obtenirClient(String dadesClient) {
        String[] atributs = dadesClient.split(SEPARADOR);
        return new Client(atributs[0], atributs[1]);
    }

}
