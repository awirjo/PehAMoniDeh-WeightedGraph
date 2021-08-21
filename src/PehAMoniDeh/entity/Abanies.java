package PehAMoniDeh.entity;

public class Abanies {
    public String name;
    public String title;
    public int tjoekoe;

    public Abanies(String name, String title, int tjoekoe) {
        this.name = name;
        this.title = title;
        this.tjoekoe = tjoekoe;
    }

    @Override
    public String toString() {
        return "Abanies{" +
                "name='" + name + '\'' +
                ", functie='" + title + '\'' +
                ", tjoekoe=" + tjoekoe +
                '}';
    }
}
