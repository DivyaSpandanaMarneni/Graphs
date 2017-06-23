import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by marne on 2/16/2017.
 */
public class DetectCycle {

    static class Graph{
        public int vertices;
        public LinkedList<Integer>[] adjList;

        Graph(int vertices){
            this.vertices = vertices;
            this.adjList = new LinkedList[vertices];
            for(int i=0;i<vertices;i++){
                adjList[i] = new LinkedList<>();
            }
        }

        void addEdge(int start, int end, boolean directed){//directed

            adjList[start].add(end);
            if(!directed)
                adjList[end].add(start);

        }


    }

    public boolean isCyclicUtil(Graph graph,int vertexId, boolean[] visited, int parent){
        visited[vertexId] = true;

        Iterator<Integer> iterator = graph.adjList[vertexId].iterator();
        Integer neighbour;
        while (iterator.hasNext()){
            neighbour = iterator.next();

            if(!visited[neighbour]){
                if(isCyclicUtil(graph,neighbour,visited,vertexId))
                    return true;
            }
            else if(neighbour != parent)
                return true;
        }
        return false;

    }

    public boolean isCyclic(Graph graph){
        boolean[] visited = new boolean[graph.vertices];

        for(int i=0;i<graph.vertices;i++){
            if(!visited[i]){
                if(isCyclicUtil(graph,i,visited,-1))
                    return true;
            }

        }

        return false;
    }



    public static void main(String[] args){
        Graph graph = new Graph(5);
        graph.addEdge(1, 0,true);
        graph.addEdge(0, 2,true);
        graph.addEdge(2, 0,true);
        graph.addEdge(0, 3,true);
        graph.addEdge(3, 4,true);

        DetectCycle detectCycle = new DetectCycle();
        System.out.println("Cycle exits: "+detectCycle.isCyclic(graph));



    }
}
