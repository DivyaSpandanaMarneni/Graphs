/**
 * Created by marne on 2/17/2017.
 */
//single source shortest path
public class DijkstrasShortestPath {

    class Vertex{
        public int label;
        public boolean isInTree;
        public Vertex(int label){
            this.label = label;
            this.isInTree = false;
        }
    }



    class Graph{
        public final int MAX_VERTICES = 20;
        public int nVerts;
        public Vertex[] vertices;
        public int[][] adjMatrix;

    }
}
