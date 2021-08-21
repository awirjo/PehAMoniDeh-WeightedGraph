package PehAMoniDeh.Datastructure;

public class QueObject {

    public int vertexIndex;
    public int edgeNumber;

    public QueObject(int vertexIndex, int edgeNumber) {
        this.vertexIndex = vertexIndex;
        this.edgeNumber = edgeNumber;
    }

    @Override
    public String toString() {
        return "QueObject{" +
                "vertexIndex=" + vertexIndex +
                '}';
    }
}
