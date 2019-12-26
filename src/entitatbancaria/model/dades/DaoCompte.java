package entitatbancaria.model.dades;

import entitatbancaria.model.logica.Client;
import entitatbancaria.model.logica.Compte;
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

public class DaoCompte {

    private File fitxer;
    public final String SEPARADOR;
    private DaoClient daoClient;

    public DaoCompte(String rutaFitxer, String separador, DaoClient daoclient) {
        this.fitxer = new File(rutaFitxer);
        this.SEPARADOR = separador;
        this.daoClient = daoclient;

    }

    public File getFitxer() {
        return fitxer;
    }

    public void setFitxer(File fitxer) {
        this.fitxer = fitxer;
    }

    /**
     * Es comprova que el client del compte rebut existeix i que no existeix un
     * compte amb el mateix número que el de c. Aleshores s'afegeix el compte al
     * fitxer.
     *
     * @param c, compte a afegir al fitxer.
     * @return true si s'ha pogut afegir i false en cas contrari.
     */
    public boolean afegir(Compte c) {
        StringBuilder sb = new StringBuilder();
        boolean afegit = false;


        try {
            if (daoClient.cercarPerNif(c.getClient().getNif()) != null
                    && cercarPerNumero(c.getNumero()) == null) { //Comprovar si existeix el client i no el número
                try (BufferedWriter bw = new BufferedWriter(
                        new FileWriter(fitxer, true));) {
                    //Afegim el comtpe al fitxer
                    bw.write(sb.append(c.getNumero()).append(SEPARADOR).
                            append(c.getClient().getNif()).append(SEPARADOR).
                            append(c.getInteresAnual()).append(SEPARADOR).
                            append(c.getSaldo()).toString());
                    bw.newLine();//Es crea una nova línia
                    bw.flush(); //Forcem que s'escriguin les dades al fitxer.
                    afegit = true;
                } catch (IOException e) {
                    Logger.getLogger(DaoCompte.class.getName()).log(Level.SEVERE,
                            null, "Error a l'afegir un compte al fitxer: " + e);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DaoCompte.class.getName()).log(Level.SEVERE, null,
                    "Error al  llegir al fitxer: " + ex);
        }
        return afegit;
    }

    /**
     * Cerca al fitxer les dades del compte amb el número rebut.
     *
     * @param numero, número del compte
     * @return Compte amb les dades trobades o null si no s'ha trobat.
     * @throws IOException és llançada si no es pot llegir el fitxer.
     */
    public Compte cercarPerNumero(long numero) throws IOException {
        Compte c = null;
        if (fitxer.exists() && fitxer.length() > 0) {
            try (BufferedReader br = new BufferedReader(
                    new FileReader(fitxer));) {
                String linia;
                Compte aux = null;
                while ((linia = br.readLine()) != null) {
                    aux = obtenirCompte(linia);
                    if (aux.getNumero() == numero) {
                        c = aux;
                        break;
                    }
                }
            }
        }
        return c;
    }

    /**
     * Cerca al fitxer les dades del compte amb el número rebut.
     *
     * @param numero, número del compte
     * @return Compte amb les dades trobades o null si no s'ha trobat.
     * @throws IOException és llançada si no es pot llegir el fitxer.
     */
    public List<Compte> cercaPerClient(Client c) throws IOException {
        List<Compte> cerca = new ArrayList();
        if (fitxer.exists()&&fitxer.length() > 0) {
            try (BufferedReader br = new BufferedReader(
                    new FileReader(fitxer));) {
                String linia;
                Compte aux = null;
                while ((linia = br.readLine()) != null) {
                    aux = obtenirCompte(linia);
                    if (aux.getClient().getNif().equalsIgnoreCase(c.getNif())) {
                        cerca.add(aux);
                    }
                }
            }
        }
        return cerca;
    }

    /**
     * Torna un List<Compte> amb tots els comptes existents al fitxer.
     *
     * @return torna un List<Compte> amb els clientsn
     * @throws IOException és llançada si no es pot llegir el fitxer.
     */
    public List<Compte> cercarTots() throws IOException {
        List<Compte> comptes = new ArrayList<>();
        if (fitxer.exists() &&fitxer.length() > 0) {
            try (BufferedReader br = new BufferedReader(new FileReader(fitxer));) {
                String linia;

                while ((linia = br.readLine()) != null) {
                    comptes.add(obtenirCompte(linia));
                }
            }
        }
        return comptes;
    }

    /**
     * Crea una instància de Compte amb els valors dels atributs rebuts a
     * dadesCompte
     *
     * @param dadesCompte
     * @return Compte
     * @throws IOException és llançada si no es pot llegir el fitxer.
     */
    private Compte obtenirCompte(String dadesCompte) throws IOException {
        String[] atributs = dadesCompte.split(SEPARADOR);
        Compte aux = new Compte(Long.valueOf(atributs[0]), daoClient.cercarPerNif(atributs[1]),
                Float.valueOf(atributs[2]),Float.valueOf(atributs[3]));
        return aux;
    }
}
