package PehAMoniDeh;

import PehAMoniDeh.Datastructure.OneEdgeQueue;
import PehAMoniDeh.Datastructure.QueObject;
import PehAMoniDeh.Datastructure.TestQueue;
import PehAMoniDeh.entity.Abanies;
import PehAMoniDeh.Datastructure.Queue;

import java.util.Arrays;

public class Graph {
    private final int MAX_PERSONS = 15;
    private int INFINITY = 100;
    private Vertex vertexArray[]; //array of vertices
    private Vertex vertexList[]; // list of vertices
    private int adjMatrix[][]; //adjacency matrix
    private int nVertex; //current number of vertices
    private int nTree; //number of verts in tree
    private DistanceParent disPar[];
    private int currentVert;
    private int startToCurrent;

    private Queue bfsQueue;

    public Graph() { //adjacency matrix setup to 0
        vertexArray = new Vertex[MAX_PERSONS];
        adjMatrix = new int[MAX_PERSONS][MAX_PERSONS];

        nVertex = 0;
        nTree = 0;
        for(int a=0; a<MAX_PERSONS; a++)
            for(int b=0; b<MAX_PERSONS; b++)
                adjMatrix[a][b] = INFINITY;
        disPar = new DistanceParent[MAX_PERSONS]; // shortest paths
        bfsQueue = new Queue();

        oneEdgeQueue = new OneEdgeQueue();

    }

    public void addVertex(Abanies abanie){
        vertexArray[nVertex++] = new Vertex(abanie);

    }
    // deprecated
//    public void addEdge(int start, int end, int weight){
//        adjMatrix[start][end] = weight;
//    }

    public void displayVertex(int vertex){
        System.out.println(vertexArray[vertex].abanies);
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

    public int findIndexOfItem(Abanies searchItem) {
        for (Vertex vertex : vertexArray) {
            if (vertex.abanies == searchItem) {
                return Arrays.asList(vertexArray).indexOf(vertex);
            }
        }
        return -1;
    }

    public void displayVertexAbaniesName(int index) {
        System.out.println(vertexArray[index].abanies.name);
    }

    public void visualizeAdjMatrix() {
        System.out.print("             ");
        int spaceToFill;
        for (Vertex vertex : vertexArray) {
            System.out.print(vertex.abanies.name);
            System.out.print(" ");
        }
        System.out.println();
        int adjMatrixRow = 0;
        for (Vertex vertex : vertexArray) {
            String settlementName = vertex.abanies.name;
            System.out.print(settlementName);
            spaceToFill = 13 - settlementName.length();
            for(int i = 0; i < spaceToFill; i++) {
                System.out.print(" ");
            }
            for (int column = 0; column < MAX_PERSONS; column++) {
                System.out.print(adjMatrix[adjMatrixRow][column]);
                spaceToFill = (vertexArray[column].abanies.name.length() -
                        String.valueOf(adjMatrix[adjMatrixRow][column]).length()) + 1;
                for(int i = 0; i < spaceToFill; i++) {
                    System.out.print(" ");
                }
            }
            adjMatrixRow++;
            System.out.println();
        }
    }

    /*public void bfs(){
        vertexArray[0].wasVisited = true;
        displayVertex(0);
        bfsQueue.insert(0);
        int vertexMarked;

        while (!bfsQueue.isEmpty()){
            int vertexFirst = bfsQueue.remove();

            while ((vertexMarked = getAdjUnvisitedVertex(vertexFirst)) != -1){
                vertexArray[vertexMarked].wasVisited = true;
                displayVertex(vertexMarked);
                bfsQueue.insert(vertexMarked);
            }
        }
        for(int j=0; j<nVertex; j++)
            vertexArray[j].wasVisited = false;
    }*/

    // gets vertices 1 edge separated from starting vertex
    public void bfsVertex1EdgeSeparated(Abanies startingVertex) {
        int startingVertexIndex = findIndexOfItem(startingVertex);
        TestQueue testQueue = new TestQueue();
        OneEdgeQueue oneEdgeQueue = new OneEdgeQueue();
        vertexArray[startingVertexIndex].wasVisited = true;
        //displayVertexAbaniesName(startingVertexIndex);
        testQueue.insert(new QueObject(startingVertexIndex, 0));

        int destinationVertex;

        while (!testQueue.isEmpty()) {
            QueObject sourceVertexObject = testQueue.remove();
            int sourceVertex = sourceVertexObject.vertexIndex;
            int edgeNumber = sourceVertexObject.edgeNumber;

            while ((destinationVertex = getAdjUnvisitedVertex(sourceVertex)) != -1) {
                vertexArray[destinationVertex].wasVisited = true;
                displayVertexAbaniesName(destinationVertex);
                System.out.println("This abanie is " + (edgeNumber + 1) + " edge(s) separated from start");
                if (edgeNumber == 0) {
                    oneEdgeQueue.insert(destinationVertex);
                }
                testQueue.insert(new QueObject(destinationVertex, edgeNumber + 1));
                testQueue.showQueue();
                oneEdgeQueue.showQueue();
            }
        }
        testQueue.makeEmpty();
        for(int j = 0; j < nVertex; j++) {
            vertexArray[j].wasVisited = false;
        }
    }

    public int getAdjUnvisitedVertex(int vertexFirst) {
        for(int j=0; j<nVertex; j++)
            if(adjMatrix[vertexFirst][j]==1 && vertexList[j].wasVisited==false)
                return j;
            return -1;
    }

    public void displayAllVertices(){
        for (Vertex vertex:vertexArray){
            if(vertex==null){
                break;
            }
            System.out.println(vertex.abanies.toString());
        }
    }


}