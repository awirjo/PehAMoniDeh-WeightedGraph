package PehAMoniDeh;

public class Abanies {
    String name;
    String functie;
    int saldo; // testen op double later.. sab fa tuh
    int salaris;
    int tjoekoe;

    public String getName() {
        return name;
    }

    public String getFunctie() {
        return functie;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getSalaris() {
        return salaris;
    }

    public int getTjoekoe() {
        return tjoekoe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setSalaris(int salaris) {
        this.salaris = salaris;
    }

    public void setTjoekoe(int tjoekoe) {
        this.tjoekoe = tjoekoe;
    }

    @Override
    public String toString() {
        return "Abanies{" +
                "name='" + name + '\'' +
                ", functie='" + functie + '\'' +
                ", saldo=" + saldo +
                ", salaris=" + salaris +
                ", tjoekoe=" + tjoekoe +
                '}';
    }
}
