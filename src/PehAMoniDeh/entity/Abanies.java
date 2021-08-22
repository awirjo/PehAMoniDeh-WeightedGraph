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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTjoekoe() {
        return tjoekoe;
    }

    public void setTjoekoe(int tjoekoe) {
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
