import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Created by marne on 2/16/2017.
 */
public class TopologicalSorting { // O(V+E)

    public void topologicalSort(GraphG graph){
        //get a vertex with indegree as 0;
        Set<Integer> incoming = new HashSet<>();
        Set<Integer> outgoingTo = new HashSet<>();

        for(int i=0;i<graph.size();i++){
             incoming.add(i);
        }
        for(int i=0;i<graph.size();i++){
            for (VertexV vert :
                    graph.getAdjVertices(i)) {
                outgoingTo.add(vert.getVertex());
            }
        }
        boolean[] visited = new boolean[graph.size()];
        incoming.removeAll(outgoingTo);
        System.out.println("Can pick any of these "+incoming.size());
        Stack<Integer> stack = new Stack<>();
        for (Integer vertexid  :
                incoming) {
            topologicalSortUtil(graph,vertexid,stack,visited);

        }

        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
        System.out.println();


    }

    public void topologicalSortUtil(GraphG graph,Integer vertexId, Stack<Integer> stack, boolean[] visited){

        visited[vertexId] = true;
        List<VertexV> adj = graph.getAdjVertices(vertexId);
        for (VertexV vert:
              adj) {
            if(!visited[vert.getVertex()])
            topologicalSortUtil(graph,vert.getVertex(),stack,visited);
        }

        stack.push(vertexId);
    }

    public static void main(String[] args){
        GraphG graph = new GraphG(6);

        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(2,4);
        graph.addEdge(4,5);
        graph.addEdge(5,3);

        System.out.println("Topological sorting for graph1");

        TopologicalSorting topSort = new TopologicalSorting();

        topSort.topologicalSort(graph);

        System.out.println("Topological sorting for graph2");
        GraphG graph2 = new GraphG(6);
        graph2.addEdge(5,2);
        graph2.addEdge(5,0);
        graph2.addEdge(4,0);
        graph2.addEdge(4,1);
        graph2.addEdge(2,3);
        graph2.addEdge(3,1);

        topSort.topologicalSort(graph2);



    }
}
