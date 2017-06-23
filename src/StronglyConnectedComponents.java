import java.util.*;

/**
 * Created by marne on 2/16/2017.
 */
public class StronglyConnectedComponents {

    Stack<Integer> stack = new Stack<>();
    public void printStronglyConnected(GraphG graphG){
        Set<Integer> visited = new HashSet<>();
       for(int i=0;i<graphG.size();i++){
           if(!visited.contains(i))
               getConnectedP1(graphG,visited,i);

       }

        //reverse all the edges

        List<VertexV>[] adjListRev = graphG.reverseEdges(graphG);

        GraphG graphRev = new GraphG(graphG.size());

        for(int i=0;i<adjListRev.length;i++){
            for (VertexV vertex :
                    adjListRev[i]) {
                graphRev.addEdge(i,vertex.getVertex());

            }
        }
        visited.clear();
        while (!stack.isEmpty()){
            Integer popped = stack.pop();
            if(visited.contains(popped.intValue())) {
                //System.out.println(popped);
                continue;
            }
            getConnectedP2(graphRev,visited,popped);
            System.out.println();
        }


    }

    public void getConnectedP2(GraphG graph, Set<Integer> visited, Integer vertexId){

        visited.add(vertexId);
        System.out.print(vertexId+" ");
        List<VertexV> adjList = graph.getAdjVertices(vertexId);
        //List<Integer> connList = new LinkedList<>();
        //connList.add(vertexId);
        for (VertexV vert :
             adjList) {
            if(!visited.contains(vert.getVertex())) {
                //visited.add(vert.getVertex());
                getConnectedP2(graph,visited,vert.getVertex());
            }
        }



    }

    public void getConnectedP1(GraphG graphG,Set<Integer> visited,Integer vertexId){

        //Set<Integer> visitedSet = new HashSet<>();
        //visited[vertexId] = true;

        visited.add(vertexId);
        List<VertexV> adjVertices = graphG.getAdjVertices(vertexId);
        for (VertexV vertex :
                adjVertices) {
            if (!visited.contains(vertex.getVertex()))
                getConnectedP1(graphG,visited,vertex.getVertex());
        }

        stack.add(vertexId); // add to stack by finish time - i.e will be added to stack when no unvisited vertices are left

    }


    public static void main(String[] args){
        GraphG graph = new GraphG(11);

        graph.addEdge(2,0);
        graph.addEdge(1,2);
        graph.addEdge(0,1);
        graph.addEdge(3,4);
        graph.addEdge(1,3);
        graph.addEdge(5,3);
        graph.addEdge(4,5);
        graph.addEdge(6,5);
        graph.addEdge(9,6);
        graph.addEdge(6,7);
        graph.addEdge(7,8);
        graph.addEdge(8,9);
        graph.addEdge(9,10);

        System.out.println("Getting the strongly connected components");

        new StronglyConnectedComponents().printStronglyConnected(graph);


//        graph.addEdge(0,2);
//        graph.addEdge(2,1);
//        graph.addEdge(1,1);
//        graph.addEdge(4,3);
//        graph.addEdge(3,1);
//        graph.addEdge(3,5);
//        graph.addEdge(5,4);
//        graph.addEdge(5,6);
//        graph.addEdge(6,9);
//        graph.addEdge(7,6);
//        graph.addEdge(8,7);
//        graph.addEdge(9,8);
//        graph.addEdge(10,9);
//        graph.addEdge();
    }
}
