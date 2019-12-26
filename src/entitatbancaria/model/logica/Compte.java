package entitatbancaria.model.logica;

public class Compte {

    private long numero;
    private Client client;
    private float interesAnual;
    private float saldo = 0.0F;

    public Compte(long numero, Client client, float interesAnual) {
        this.numero = numero;
        this.client = client;
        this.interesAnual = interesAnual;
    }

    public Compte(long numero, Client client, float interesAnual, float saldo) {
        this.numero = numero;
        this.client = client;
        this.interesAnual = interesAnual;
        this.saldo = saldo;
    }

    public long getNumero() {
        return numero;
    }

    public Client getClient() {
        return client;
    }

    public float getSaldo() {
        return saldo;
    }

    public float getInteresAnual() {
        return interesAnual;
    }

    public void setInteresAnual(float interesAnual) {
        this.interesAnual = interesAnual;
    }

    @Override
    public String toString() {
        return "Compte{" + "numero=" + numero + ", client=" + client
                + ", saldo=" + saldo + ", interesAnual=" + interesAnual + '}';
    }

    /**
     * Suma a saldo la quantitat rebuda.
     *
     * @param quantitat
     */
    public void ingres(float quantitat) {
        this.saldo += quantitat;
    }

    /**
     * Si hi ha prou saldo li resta quantitat.
     *
     * @param quantitat
     * @throws Exception és llançada si no hi ha prou saldo.
     */
    public void reintegre(float quantitat) throws Exception {
        if (this.saldo - quantitat >= 0) {
            this.saldo -= quantitat;
        } else {
            throw new Exception("Saldo negatiu, reintegre anul·lat");
        }
    }

}
