package PehAMoniDeh.Graph;

import PehAMoniDeh.Datastructure.*;
import PehAMoniDeh.Entity.Abanies;

import java.util.Arrays;

public class Graph {
    private final int MAX_PERSONS = 15;
    private int INFINITY = 100;
    private Vertex vertexArray[]; //array of vertices
    private int adjMatrix[][]; //adjacency matrix
    private int nVertex; //current number of vertices
    private int nTree; //number of verts in tree
    private DistanceParent disPar[];
    private int currentVert;
    private int startToCurrent;

    public DfsStack dfsStack;
    public StackDisplayPath stackDisplayPath;
    public BfsQueue bfsQueue;


    public Graph() {
        vertexArray = new Vertex[MAX_PERSONS];
        adjMatrix = new int[MAX_PERSONS][MAX_PERSONS];

        nVertex = 0;
        nVertexGraph = 0;
        for(int index=0; index<MAX_PERSONS; index++)
            for(int b=0; b<MAX_PERSONS; b++)
                adjMatrix[a][b] = INFINITY;
        disPar = new DistanceParent[MAX_PERSONS]; // shortest paths

        bfsQueue = new BfsQueue();
        dfsStack = new DfsStack();
        stackDisplayPath = new StackDisplayPath();
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

    public void addEdge(Abanies sourceAbanie, Abanies destinationAbanie, int moneyFlow) {
        int indexOfSourceAbanie = findIndexItem(sourceAbanie);
        int indexOfDestinationAbanie = findIndexItem(destinationAbanie);

        adjMatrix[indexOfSourceAbanie][indexOfDestinationAbanie] = moneyFlow;
    }

    public int findIndexItem(Abanies searchItem) {
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

    public void displayVertexAbaniesTjoekoe(int index) {
        System.out.print(vertexArray[index].abanies.tjoekoe);
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

    public void bfsSearch(Abanies startingVertex) {
        int startingVertexIndex = findIndexItem(startingVertex);
        BfsQueue bfsQueue = new BfsQueue();
        vertexArray[startingVertexIndex].wasVisited = true;
        displayVertexAbaniesName(startingVertexIndex);
        testQueue.insert(new QueObject(startingVertexIndex, 0));

        int destinationVertex;

        while (!bfsQueue.isEmpty()) {
            QueObject sourceVertexObject = bfsQueue.remove();
            int sourceVertex = sourceVertexObject.vertexIndex;
            int edgeNumber = sourceVertexObject.edgeNumber;

            while ((destinationVertex = getAdjUnvisitedVertex(sourceVertex)) != -1) {
                vertexArray[destinationVertex].wasVisited = true;
                displayVertex(destinationVertex);
                System.out.println("This abanie is " + (edgeNumber + 1) + " edge(s) separated from start");
                if (edgeNumber == 0) {
                    oneEdgeQueue.insert(destinationVertex);
                }
                bfsQueue.insert(new QueObject(destinationVertex, edgeNumber + 1));
//                bfsQueue.showQueue();
                System.out.println();
            }
        }
        bfsQueue.makeEmpty();
        for(int j = 0; j < nVertex; j++) {
            vertexArray[j].wasVisited = false;
        }
    }

    public int getAdjUnvisitedVertex(int vertexFirst) {
        for(int j=0; j<nVertex; j++)
            if(adjMatrix[vertexFirst][j] != INFINITY && vertexArray[j].wasVisited==false)
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

    public void shortestPath(Abanies startingVertex, Abanies destinationVertex) {
        int startingVertexIndex = findIndexItem(startingVertex);
        swapVertex(startingVertexIndex, true);

        vertexArray[0].isInGraph = true;
        nVertexGraph = 1;
        for(int index = 0; index < nVertex; index++) {
            int tempDistance = adjMatrix[0][index];
            disPar[index] = new DistanceParent(0, tempDistance);
        }
        // hulpcode
//        System.out.println(Arrays.toString(shortestPath));
//        System.out.println("");
        while(nVertexGraph < nVertex) {
            int vertexIndexMinMoneyflow = getIndexMinMoneyflow();
            int minDistance = disPar[vertexIndexMinMoneyflow].distance;
            if (minDistance == INFINITY) {
                System.out.println("Vertices are not reachable");
                break;
            } else {
                currentVert = vertexIndexMinMoneyflow;
                startToCurrent = disPar[vertexIndexMinMoneyflow].distance;
            }
            vertexArray[currentVert].isInGraph = true;
            nVertexGraph++;
            adjustShortestPath();
        }
//        System.out.println(Arrays.toString(shortestPath));
//        System.out.println("");
//        displayPaths();
        showMoneyFlow(startingVertex, destinationVertex);
        nVertexGraph = 0;
        for(int index = 0; index< nVertex; index++)
            vertexArray[index].isInGraph = false;
        swapVertex(startingVertexIndex, false);
    }

    public int getIndexMinMoneyflow() {
        int minDistance = INFINITY;
        int vertInMinDist = 0;
        for(int column = 1; column < nVertex; column++) {
            if(!vertexArray[column].isInGraph && (disPar[column].distance < minDistance)) {
                minDistance = disPar[column].distance;
                vertInMinDist = column;
            }
        }
        return vertInMinDist;
    }

    public void adjustShortestPath() {
        int column = 1;
        while(column < nVertex) {
            if( vertexArray[column].isInGraph) {
                column++;
                continue;
            }
            int currentToFringeDistance = adjMatrix[currentVert][column];
            int startToFringeDistance = startToCurrent + currentToFringeDistance;
            int shortestPathDist = disPar[column].distance;

            if(startToFringeDistance < shortestPathDist) {
                disPar[column].parentVert = currentVert;
                disPar[column].distance = startToFringeDistance;
            }
            column++;
        }
        // hulpcode
//        System.out.println(Arrays.toString(shortestPath));
//        System.out.println("");
    }

    public void swapVertex(int vertexToSwap, boolean adjust) {
        if (adjust) {
            Vertex tempHolderVertex = vertexArray[vertexToSwap];
            vertexArray[vertexToSwap] = vertexArray[0];
            vertexArray[0] = tempHolderVertex;
//        System.out.println(Arrays.toString(vertexList));

            for (int columnCount = 0; columnCount < nVertex; columnCount++) {
                int tempHolderWeight = adjMatrix[vertexToSwap][columnCount];
                adjMatrix[vertexToSwap][columnCount] = adjMatrix[0][columnCount];
                adjMatrix[0][columnCount] = tempHolderWeight;
            }
            for (int rowCount = 0; rowCount < nVertex; rowCount++) {
                int tempHolderWeight = adjMatrix[rowCount][vertexToSwap];
                adjMatrix[rowCount][vertexToSwap] = adjMatrix[rowCount][0];
                adjMatrix[rowCount][0] = tempHolderWeight;
            }
//            visualizeAdjMatrix();
        } else {
            Vertex tempHolderVertex = vertexArray[vertexToSwap];
            vertexArray[vertexToSwap] = vertexArray[0];
            vertexArray[0] = tempHolderVertex;
//        System.out.println(Arrays.toString(vertexList));

            for (int columnCount = 0; columnCount < nVertex; columnCount++) {
                int tempHolderWeight = adjMatrix[0][columnCount];
                adjMatrix[0][columnCount] = adjMatrix[vertexToSwap][columnCount];
                adjMatrix[vertexToSwap][columnCount] = tempHolderWeight;
            }
            for (int rowCount = 0; rowCount < nVertex; rowCount++) {
                int tempHolderWeight = adjMatrix[rowCount][0];
                adjMatrix[rowCount][0] = adjMatrix[rowCount][vertexToSwap];
                adjMatrix[rowCount][vertexToSwap] = tempHolderWeight;
            }
//            visualizeAdjMatrix();
        }
    }

    public void displayPaths() {
        for(int j = 0; j < nVertex; j++) {
            System.out.print(vertexArray[j].abanies.getName() + "=");
            if(disPar[j].distance == INFINITY) {
                System.out.print("inf");
            }
            else {
                System.out.print(disPar[j].distance);
            }
            String parent = vertexArray[disPar[j].parentVert].abanies.getName();
            System.out.print("(" + parent + ") ");
        }
        System.out.println("");
    }

    public void showMoneyFlow(Abanies startingVertex, Abanies destinationVertex) {
        for(int index = 0; index < nVertex; index++) {
            if (index == findIndexItem(destinationVertex)) {
                System.out.print("The money flow from " + startingVertex.getName() + " to ");
                stackDisplayPath.push(vertexArray[index].abanies);
                System.out.print(vertexArray[index].abanies.getName());
                System.out.print(" has a minimum cost of ");
                if(disPar[index].distance == INFINITY) {
                    System.out.print("inf");
                }
                else {
                    System.out.print(disPar[index].distance + " ");
                }

                int vertParent = disPar[index].parentVert;
                Abanies parent = vertexArray[vertParent].abanies;
                stackDisplayPath.push(parent);
//            System.out.print(parent);
                int parentOf = vertParent;
                boolean notA = true;
                while (notA) {
//                System.out.print(" after stealing via ");
                    int temp = disPar[parentOf].parentVert;
                    Abanies parentOfParent = vertexArray[disPar[parentOf].parentVert].abanies;
                    stackDisplayPath.push(parentOfParent);
//                System.out.print(parentOfParent + " ");
                    parentOf = temp;
                    if (parentOfParent == startingVertex)
                        notA = false;
                }
//                System.out.print("and it starts from: ");
                while (!stackDisplayPath.isEmpty()) {
                    System.out.print(stackDisplayPath.pop().getName());
                    if (!stackDisplayPath.isEmpty()) {
                        System.out.print(" to ");
                    }
                }
                System.out.println(" ");
            }
        }
    }

    public void longestPath(Abanies startingVertex, Abanies destinationVertex) {
        int startingVertexIndex = findIndexItem(startingVertex);
        swapParameters(true);
        swapVertex(startingVertexIndex, true);

        vertexArray[0].isInGraph = true;
        nVertexGraph = 1;

        for(int index = 0; index < nVertex; index++) {
            int tempDistance = adjMatrix[0][index];
            disPar[index] = new DistanceParent(0, tempDistance);
        }

//        System.out.println(Arrays.toString(disPar));
//        System.out.println("");

        while(nVertexGraph < nVertex) {
            int vertexIndexMaxMoneyflow = getIndexMaxMoneyflow();
            int maxDistance = disPar[vertexIndexMaxMoneyflow].distance;
            if (maxDistance == 0) {
                System.out.println("Vertices are unreachable");
                break;
            } else {
                currentVert = vertexIndexMaxMoneyflow;
                startToCurrent = disPar[vertexIndexMaxMoneyflow].distance;
            }
            vertexArray[currentVert].isInGraph = true;
            nVertexGraph++;
            adjustLongestPath();
        }
//        displaylongestPaths();
        showMaxMoneyflow(startingVertex, destinationVertex);
        nVertexGraph = 0;
        for(int index = 0; index< nVertex; index++) {
            vertexArray[index].isInGraph = false;
        }
        swapParameters(false);
        swapVertex(startingVertexIndex, false);
    }

    public int getIndexMaxMoneyflow() {
        int maxDistance = 0;
        int vertInMaxDist = 0;
        for(int column = 1; column < nVertex; column++) {
            if(!vertexArray[column].isInGraph && (disPar[column].distance > maxDistance)) {
                maxDistance = disPar[column].distance;
                vertInMaxDist = column;
            }
        }
        return vertInMaxDist;
    }

    public void adjustLongestPath() {
        int column = 1;
        while(column < nVertex) {
            if(vertexArray[column].isInGraph) {
                column++;
                continue;
            }
            int currentToFringeDistance = adjMatrix[currentVert][column];
            int startToFringeDistance = startToCurrent + currentToFringeDistance;
            int longestPathDist = disPar[column].distance;

            if(startToFringeDistance > longestPathDist && currentToFringeDistance != 0) {
                disPar[column].parentVert = currentVert;
                disPar[column].distance = startToFringeDistance;
            }
            column++;
        }
//        System.out.println(Arrays.toString(longestPath));
//        System.out.println("");
    }

    public void displaylongestPaths() {
        for(int j = 0; j < nVertex; j++) {
            System.out.print(vertexArray[j].abanies.getName() + "=");
            if(disPar[j].distance == 0) {
                System.out.print("inf");
            }
            else {
                System.out.print(disPar[j].distance);
            }
            String parent = vertexArray[disPar[j].parentVert].abanies.getName();
            System.out.print("(" + parent + ") ");
        }
        System.out.println("");
    }

    public void swapParameters(boolean adjust) {
        if (adjust) {
            for(int row = 0; row < MAX_PERSONS; row++) {
                for(int column = 0; column < MAX_PERSONS; column++) {
                    if (adjMatrix[row][column] == INFINITY) {
                        adjMatrix[row][column] = 0;
                    }
                }
            }
        } else {
            for(int row = 0; row < MAX_PERSONS; row++) {
                for(int column = 0; column < MAX_PERSONS; column++) {
                    if (adjMatrix[row][column] == 0) {
                        adjMatrix[row][column] = INFINITY;
                    }
                }
            }
        }
    }

    public void showMaxMoneyflow(Abanies startingVertex, Abanies destinationVertex) {
        for(int index = 0; index < nVertex; index++) {
            if (index == findIndexItem(destinationVertex)) {
                System.out.print("The money flow from " + startingVertex.getName() + " to ");
                stackDisplayPath.push(vertexArray[index].abanies);
                System.out.print(vertexArray[index].abanies.getName());
                System.out.print(" has a maximum cost of ");
                if(disPar[index].distance == INFINITY) {
                    System.out.print("inf");
                }
                else {
                    System.out.print(disPar[index].distance + ", ");
                }
//            System.out.print(" travel via ");
                int parentVert = disPar[index].parentVert;
                Abanies parent = vertexArray[parentVert].abanies;
                stackDisplayPath.push(parent);
//            System.out.print(parent);
                int parentOf = parentVert;
                boolean notA = true;
                while (notA) {
//                System.out.print(" after traveling via ");
                    int temp = disPar[parentOf].parentVert;
                    Abanies parentOfParent = vertexArray[disPar[parentOf].parentVert].abanies;
                    stackDisplayPath.push(parentOfParent);
//                System.out.print(parentOfParent + " ");
                    parentOf = temp;
                    if (parentOfParent == startingVertex)
                        notA = false;
                }
                System.out.print(" and it starts from ");
                while (!stackDisplayPath.isEmpty()) {
                    System.out.print(stackDisplayPath.pop().getName());
                    if (!stackDisplayPath.isEmpty()) {
                        System.out.print(" to ");
                    }
                }
                System.out.println("");
            }
        }
    }
}