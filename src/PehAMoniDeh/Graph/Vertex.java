package PehAMoniDeh.Graph;

import PehAMoniDeh.Entity.Abanies;

public class Vertex {
    public boolean wasVisited;
    public boolean isInGraph;

    public Abanies abanies;
    public Vertex(Abanies abanies) {
        this.abanies = abanies;
        wasVisited= false;
        isInGraph = false;
    }
}
