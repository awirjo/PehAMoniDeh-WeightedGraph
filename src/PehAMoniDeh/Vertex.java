package PehAMoniDeh;

public class Vertex {
    public boolean wasVisited;
    public boolean isInTree;

    public Abanies abanies;
    public Vertex(Abanies abanies) {
        this.abanies = abanies;
        wasVisited= false;
        isInTree = false;
    }
}
