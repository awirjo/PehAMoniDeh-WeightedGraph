package PehAMoniDeh;

import java.util.Arrays;

public class Graph {
    private final int MAX_PERSONS = 16;
    private int INFINITY = 100;
    private Vertex vertexArray[]; //array of vertices
    private int adjMatrix[][]; //adjacency matrix
    private int nVertex; //current number of vertices
    private int nTree; //number of verts in tree
    private DistanceParent sPath[];
    private int currentVert;
    private int startToCurrent;

    public Graph() { //adjacency matrix setup to 0
        vertexArray = new Vertex[MAX_PERSONS];
        adjMatrix = new int[MAX_PERSONS][MAX_PERSONS];

        nVertex = 0;
        nTree = 0;
        for(int a=0; a<MAX_PERSONS; a++)
            for(int b=0; b<MAX_PERSONS; b++)
                adjMatrix[a][b] = INFINITY;
        sPath = new DistanceParent[MAX_PERSONS]; // shortest paths
    }

    public void addVertex(Abanies abanie){
        vertexArray[nVertex++] = new Vertex(abanie);
    }
    public void addEdge(int start, int end, int weight){
        adjMatrix[start][end] = weight;
    }
    public void displayVertex(int v){
        System.out.println(vertexArray[v].abanies);
    }
    public void addEdgeByName(Abanies sourceAbanie, Abanies destinationAbanie, int distance) {
        int indexOfSourceAbanie = 0;
        int indexOfDestinationAbanie = 0;
        for (Vertex vertex : vertexArray) {
            if (vertex.abanies == sourceAbanie) {
                indexOfSourceAbanie = Arrays.asList(vertexArray).indexOf(vertex);
                break;
            }
        }
        for (Vertex vertex : vertexArray) {
            if (vertex.abanies == destinationAbanie) {
                indexOfDestinationAbanie = Arrays.asList(vertexArray).indexOf(vertex);
                break;
            }
        }
        adjMatrix[indexOfSourceAbanie][indexOfDestinationAbanie] = distance;
        adjMatrix[indexOfDestinationAbanie][indexOfSourceAbanie] = distance;
    }
}
