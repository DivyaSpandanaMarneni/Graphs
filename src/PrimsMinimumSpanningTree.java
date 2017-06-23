/**
 * Created by marne on 2/17/2017.
 */
public class PrimsMinimumSpanningTree {

    class Edge{
        public int src;
        public int dest;
        public int weight;

        public Edge(int src,int dest,int weight){
            this.src = src;
            this.dest= dest;
            this.weight = weight;
        }
    }

    class PriorityQueue{ //priority queue implemented as array
        private final int SIZE =20;
        private Edge[] edges;
        private int size;

        public PriorityQueue(){
            edges = new Edge[SIZE];
            size = 0;
        }

        public void insertEdge(Edge item){
            //find the place to insert and then add item
            int i;
            for(i=0;i<size;i++){
                if(edges[i].weight >= item.weight)
                    break;
            }

            for(int j=size-1;j>=i;j-- ){
                edges[j+1] = edges[j];
            }
            edges[i] = item;
            size++;



        }

        public Edge extractMin(){
            Edge edgeExtracted = edges[0];

            for(int i=1;i<size;i++){
                edges[i-1] = edges[i];
            }
            edges[size-1] = null;
            size--;

            return edgeExtracted;
        }

        public Edge removeAtN(int n){
            Edge edgeRemoved = edges[n];
            for(int j=n;j<size;j++){
                edges[j] = edges[j+1];
            }
            edges[size-1] = null;
            size--;
            return edgeRemoved;
        }

        public Edge peekMin(){
            return edges[0];
        }

        public int size(){
            return size;
        }

        public Edge peekN(int n){
            return edges[n];
        }

        public int findPositionGivenDest(int dest){
            for(int i=0;i<size;i++){
                if(edges[i].dest == dest)
                    return i;
            }
            return -1;
        }

    }//end of priority queue class

    class Vertex{
        public int label;
        public boolean isInTree;
        public Vertex(int label){
            this.label = label;
            this.isInTree = false;
        }
    }

    class Graph{
        private final int MAX_VERTICES = 20;
        private final int INFINITY = Integer.MAX_VALUE;
        private Vertex[] vertices ;
        private int[][] adjMatrix;
        private int nVerts;
        private PriorityQueue priorityQueue;
        private int nTree ; //number of vertices in the tree

        public Graph(){
            vertices = new Vertex[MAX_VERTICES];
            adjMatrix = new int[MAX_VERTICES][MAX_VERTICES];

            for(int i=0;i<MAX_VERTICES;i++){
                for(int j=0;j<MAX_VERTICES;j++){
                    adjMatrix[i][j] = 0;
                }

            }

            nVerts = 0;
            nTree=0;
            priorityQueue = new PriorityQueue();
        }

        public void addVertex(int label){
            vertices[nVerts++] = new Vertex(label);
        }

        public void addEdge(int start,int end,int weight){
            adjMatrix[start][end] = weight;
            adjMatrix[end][start] = weight;
        }

        public void displayVertex(Vertex vertex){
            System.out.print(vertex.label+" ");
        }


        public void minSpanningTree(Graph graph){

            int currentVertex = 0;
            while (nTree < nVerts-1){
                vertices[currentVertex].isInTree = true;
                nTree++;

                //insert the edges of the current vertex into priority queue
                for(int i=0;i<nVerts;i++){

                    if(i==currentVertex)
                        continue;
                    if(vertices[i].isInTree)
                        continue;
                    if(adjMatrix[currentVertex][i] ==0){
                        continue;
                    }

                    //Edge getEdge = new Edge(currentVertex,i,adjMatrix[currentVertex][i]);
                    putInPQ(currentVertex,i,adjMatrix[currentVertex][i]);
                }

                if(priorityQueue.size == 0){
                    System.out.println("no connections in the graph");
                    return;
                }

                //extract min and update the map accordingly
                Edge edge = priorityQueue.extractMin();
                int srcV = edge.src;
                int destV = edge.dest;
                int getWeight = edge.weight;

                currentVertex = destV;
                System.out.print(vertices[srcV].label+"->"+vertices[destV].label+", ");






            }//end of while when all vertices are in tree

        }

        public void putInPQ(int current,int vertex,int weight){

            //fetch for that vertex
            //update its source and weight
            //if the existing weight is > the new given weight

            int queueIndex = priorityQueue.findPositionGivenDest(vertex);
            if(queueIndex!=-1) {
                Edge getEdge = priorityQueue.peekN(queueIndex);//get that edge
                if (getEdge.weight > weight) {
                    priorityQueue.removeAtN(queueIndex);
                    Edge newEdge = new Edge(current, vertex, weight);
                    priorityQueue.insertEdge(newEdge);
                }
            }
            else{//no such edge
                priorityQueue.insertEdge(new Edge(current,vertex,weight));



            }
        }//end of put in the priority queue method


    }




    public static void main(String[] args){
        PrimsMinimumSpanningTree primsMST = new PrimsMinimumSpanningTree();
        Graph graph = primsMST.new Graph();
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);


        graph.addEdge(0,1,3);
        graph.addEdge(0,3,1);
        graph.addEdge(1,3,3);
        graph.addEdge(1,2,1);
        graph.addEdge(3,4,6);
        graph.addEdge(2,3,1);
        graph.addEdge(2,4,5);
        graph.addEdge(2,5,4);
        graph.addEdge(4,5,2);




        graph.minSpanningTree(graph);
    }
}
