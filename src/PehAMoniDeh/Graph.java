package PehAMoniDeh;

import PehAMoniDeh.Datastructure.*;
import PehAMoniDeh.entity.Abanies;

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

    public OneEdgeQueue oneEdgeQueue;
    public Stack stack;
    public PathStack pathStack;
    //public Queue bfsQueue;


    public Graph() { //adjacency matrix setup to 0
        vertexArray = new Vertex[MAX_PERSONS];
        adjMatrix = new int[MAX_PERSONS][MAX_PERSONS];

        nVertex = 0;
        nTree = 0;
        for(int a=0; a<MAX_PERSONS; a++)
            for(int b=0; b<MAX_PERSONS; b++)
                adjMatrix[a][b] = INFINITY;
        disPar = new DistanceParent[MAX_PERSONS]; // shortest paths

        //bfsQueue = new Queue();
        oneEdgeQueue = new OneEdgeQueue();
        stack = new Stack();
        pathStack = new PathStack();

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
        int indexOfSourceAbanie = findIndexOfItem(sourceAbanie);
        int indexOfDestinationAbanie = findIndexOfItem(destinationAbanie);
//        for (Vertex vertex : vertexArray) {
//            if (vertex.abanies == sourceAbanie) {
//                indexOfSourceAbanie = Arrays.asList(vertexArray).indexOf(vertex);
//                break;
//            }
//        }
//        for (Vertex vertex : vertexArray) {
//            if (vertex.abanies == destinationAbanie) {
//                indexOfDestinationAbanie = Arrays.asList(vertexArray).indexOf(vertex);
//                break;
//            }
//        }
        adjMatrix[indexOfSourceAbanie][indexOfDestinationAbanie] = moneyFlow;
//        adjMatrix[indexOfDestinationAbanie][indexOfSourceAbanie] = moneyFlow;
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

    public void dfsEdgeStepByVertex(Abanies startingAbanie, Abanies destinationAbanie) {
        int startingVertexIndex = findIndexOfItem(startingAbanie);
        int destinationVertexIndex = findIndexOfItem(destinationAbanie);
        vertexArray[startingVertexIndex].wasVisited = true;
        displayVertex(startingVertexIndex);
        stack.push(startingVertexIndex);
        pathStack.push(startingVertexIndex);
        while(!stack.isEmpty()) {
            int stackPeek = stack.peek();
            int unvisitedVertex = getAdjUnvisitedVertex(stackPeek);
            if(unvisitedVertex == -1) { //if row has no weights anymore(wasVisited all true) only 1000
                stack.pop();
                pathStack.pop();
            } else {
                vertexArray[unvisitedVertex].wasVisited = true;
                displayVertex(unvisitedVertex);
                stack.push(unvisitedVertex);
                pathStack.push(unvisitedVertex);
                if (unvisitedVertex == destinationVertexIndex) {
                    break;
                }
            }
        }
        for(int index = 0; index < nVertex; index++) {
            vertexArray[index].wasVisited = false;
        }
        pathStack.showStack();
    }

//    public void bfs(){
//        vertexArray[0].wasVisited = true;
//        displayVertex(0);
//        bfsQueue.insert(0);
//        int vertexMarked;
//
//        while (!bfsQueue.isEmpty()){
//            int vertexFirst = bfsQueue.remove();
//
//            while ((vertexMarked = getAdjUnvisitedVertex(vertexFirst)) != -1){
//                vertexArray[vertexMarked].wasVisited = true;
//                displayVertex(vertexMarked);
//                bfsQueue.insert(vertexMarked);
//            }
//        }
//        for(int j=0; j<nVertex; j++)
//            vertexArray[j].wasVisited = false;
//    }

    // gets vertices 1 edge separated from starting vertex
    public void bfsVertex1EdgeSeparated(Abanies startingVertex) {
        int startingVertexIndex = findIndexOfItem(startingVertex);
        TestQueue testQueue = new TestQueue();
//        OneEdgeQueue oneEdgeQueue = new OneEdgeQueue();
        vertexArray[startingVertexIndex].wasVisited = true;
        displayVertexAbaniesName(startingVertexIndex);
        testQueue.insert(new QueObject(startingVertexIndex, 0));

        int destinationVertex;

        while (!testQueue.isEmpty()) {
            QueObject sourceVertexObject = testQueue.remove();
            int sourceVertex = sourceVertexObject.vertexIndex;
            int edgeNumber = sourceVertexObject.edgeNumber;

            while ((destinationVertex = getAdjUnvisitedVertex(sourceVertex)) != -1) {
                vertexArray[destinationVertex].wasVisited = true;
                displayVertex(destinationVertex);
                System.out.println("This abanie is " + (edgeNumber + 1) + " edge(s) separated from start");
                if (edgeNumber == 0) {
                    oneEdgeQueue.insert(destinationVertex);
                }
                testQueue.insert(new QueObject(destinationVertex, edgeNumber + 1));
//                testQueue.showQueue();
//                oneEdgeQueue.showQueue();
            }
        }
        testQueue.makeEmpty();
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
        int startingVertexIndex = findIndexOfItem(startingVertex);
        adjustVertexListAdjMatrix(startingVertexIndex, true);

        vertexArray[0].isInTree = true;
        nTree = 1;
        for(int index = 0; index < nVertex; index++) {
            int tempDistance = adjMatrix[0][index];
            disPar[index] = new DistanceParent(0, tempDistance);
        }
        // hulpcode
//        System.out.println(Arrays.toString(shortestPath));
//        System.out.println("");
        while(nTree < nVertex) {
            int indexOfVertexWithMinDistance = getIndexOfVertexWithMinDistance();
            int minDistance = disPar[indexOfVertexWithMinDistance].distance;
            if (minDistance == INFINITY) {
                System.out.println("There are unreachable vertices");
                break;
            } else {
                currentVert = indexOfVertexWithMinDistance;
                startToCurrent = disPar[indexOfVertexWithMinDistance].distance;
            }
            vertexArray[currentVert].isInTree = true;
            nTree++;
            adjustShortestPath();
        }
//        System.out.println(Arrays.toString(shortestPath));
//        System.out.println("");
        displayPaths();
        nTree = 0;
        for(int index = 0; index< nVertex; index++)
            vertexArray[index].isInTree = false;
        adjustVertexListAdjMatrix(startingVertexIndex, false);
    }

    public int getIndexOfVertexWithMinDistance() {
        int minDistance = INFINITY;
        int vertexIndexWithMinDistance = 0;
        for(int column = 1; column < nVertex; column++) {
            if(!vertexArray[column].isInTree && (disPar[column].distance < minDistance)) {
                minDistance = disPar[column].distance;
                vertexIndexWithMinDistance = column;
            }
        }
        return vertexIndexWithMinDistance;
    }

    public void adjustShortestPath() {
        int column = 1;
        while(column < nVertex) {
            if( vertexArray[column].isInTree) {
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

    public void adjustVertexListAdjMatrix(int vertexToSwap, boolean adjust) {
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
            visualizeAdjMatrix();
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
            visualizeAdjMatrix();
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

//    public void displayPaths2(Abanies startingVertex, Abanies destinationVertex) {  // voor andere starting vertex moet nog aangepast worden
//        for(int index = 0; index < nVertex; index++) {
//            if (index == findIndexOfItem(destinationVertex)) {
//                System.out.print("To travel from " + startingVertex + " to ");
//                stack.push(vertexArray[index].abanies);
//                System.out.print(vertexArray[index].abanies);
//                System.out.print(" with a cost of ");
//                if(disPar[index].distance == INFINITY) {
//                    System.out.print("inf");
//                }
//                else {
//                    System.out.print(disPar[index].distance + " ");
//                }
////            System.out.print(" travel via ");
//                int parentVert = disPar[index].parentVert;
//                Abanies parent = vertexArray[parentVert].abanies;
//                stack.push(parent);
////            System.out.print(parent);
//                int parentOf = parentVert;
//                boolean notA = true;
//                while (notA) {
////                System.out.print(" after traveling via ");
//                    int temp = disPar[parentOf].parentVert;
//                    Abanies parentOfParent = vertexArray[disPar[parentOf].parentVert].abanies;
//                    stack.push(parentOfParent);
////                System.out.print(parentOfParent + " ");
//                    parentOf = temp;
//                    if (parentOfParent == startingVertex)
//                        notA = false;
//                }
//                System.out.print("| ");
//                while (!stack.isEmpty()) {
//                    System.out.print((char)stack.pop() + " => ");
//                }
//                System.out.println("");
//            }
//        }
//    }


}