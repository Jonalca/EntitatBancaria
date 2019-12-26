package entitatbancaria.model.logica;


public class Client {
    private String nif;
    private String nom;

    public Client(String nif, String nom) {
        this.nif = nif;
        this.nom = nom;
    }

    public String getNif() {
        return nif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Client{" + "nif=" + nif + ", nom=" + nom + '}'+"\n";
    }


}
