import java.util.List;
import java.util.Stack;

/**
 * Created by marne on 2/15/2017.
 */
public class DepthFirstSearch {

    public void depthFirstSearch(GraphG graphG, VertexV start){

        Stack<VertexV> stack = new Stack<>();
        stack.push(start);
        System.out.print(stack.peek().getVertex()+" ");
        boolean[] visited = new boolean[graphG.size()];
        visited[stack.peek().getVertex()] = true;

        while (!stack.isEmpty()){

            VertexV vertex = stack.pop();
            List<VertexV> adjList = graphG.getAdjVertices(vertex.getVertex());

                for (VertexV ver :
                        adjList) {
                    if (!visited[ver.getVertex()]) {
                        stack.push(ver);
                        visited[ver.getVertex()] = true;
                        System.out.print(ver.getVertex()+" ");
                    }
                }

        }

    }

    public void depthFirstSearchRecursive(GraphG graph,VertexV vertex){

        boolean[] visited = new boolean[graph.size()];
        for(int i=0;i<visited.length;i++) {
            if(!visited[i])
            depthFirstSearchRec(graph, vertex, visited);
        }

    }

    public void depthFirstSearchRec(GraphG graph, VertexV vertex,boolean[] visited){

//        if(visited[vertex.getVertex()])
//            return;

        visited[vertex.getVertex()] = true;
        System.out.print(vertex.getVertex()+" ");
        for (VertexV ver :
                graph.getAdjVertices(vertex.getVertex())) {
            if(!visited[ver.getVertex()])
                depthFirstSearchRec(graph,ver,visited);
        }

//        for(int ver=0;ver<graph.size();ver++){
//            if(!visited[ver])
//                depthFirstSearchRec(graph,new VertexV(ver),visited);
//        }

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


        DepthFirstSearch dfs = new DepthFirstSearch();

        System.out.println();
        dfs.depthFirstSearchRecursive(graph, new VertexV(0));
        System.out.println();
        System.out.println("Depth first search using stack");
        dfs.depthFirstSearch(graph,new VertexV(0));
        System.out.println();
        System.out.println("DFS in undirected"); // edges will be repeated in the adjacency List

        GraphG graphUn = new GraphG(6);
        graphUn.addEdge(0,1);
        graphUn.addEdge(0,2);
        graphUn.addEdge(1,2);
        graphUn.addEdge(1,3);
        graphUn.addEdge(2,4);
        graphUn.addEdge(4,5);
        graphUn.addEdge(5,3);
        graphUn.addEdge(1,0);
        graphUn.addEdge(2,0);
        graphUn.addEdge(2,1);
        graphUn.addEdge(3,1);
        graphUn.addEdge(4,2);
        graphUn.addEdge(5,4);
        graphUn.addEdge(3,5);

        dfs.depthFirstSearchRecursive(graphUn,new VertexV(0));
        System.out.println();
        System.out.println("using stack");
        dfs.depthFirstSearch(graphUn,new VertexV(0));
    }


}
